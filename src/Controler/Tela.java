package Controler;
import Modelo.BlocoVazio;
import Modelo.BombaExplosao.Bomba;
import Modelo.BombaExplosao.Explosao;
import Modelo.Model;
import Modelo.Blocos;
import Modelo.BlocoMetal;
import Modelo.Personagem;
import Modelo.Caveira;
import Modelo.Hero;
import Modelo.Chaser;
import Modelo.BichinhoVaiVemHorizontal;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.BichinhoVaiVemVertical;
import Modelo.Esfera;
import Modelo.ZigueZague;
import Auxiliar.Posicao;
import Auxiliar.Fase;
import Auxiliar.Fase_1;
import Auxiliar.Fase_2;
import Auxiliar.Fase_3;
import Auxiliar.Fase_4;
import Auxiliar.Fase_5;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JButton;

public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private Hero hero;
    private Fase faseAtual,faseUm,faseDois,faseTres,faseQuatro,faseCinco;
    private VictoryScreen tWin;
    private Timer gameTimer;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;
    private int cameraLinha = 0;
    private int cameraColuna = 0;
    private final Set<Integer> teclasPressionadas = new HashSet<>();
    private int flagTryAgain = 0;
    public Tela() {
        Desenho.setCenario(this);
        initComponents();
        this.addMouseListener(this);
        /*mouse*/
        this.addKeyListener(this);
        /*teclado*/
 /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        //this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                //Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);
      /*          
        faseAtual = new Fase();
        faseAtual.fase1();
        hero = faseAtual.getHero();*/
      faseUm = new Fase_1();
      faseDois = new  Fase_2();
      faseTres = new Fase_3();
      faseQuatro = new Fase_4();
      faseCinco = new Fase_5();
      faseUm.setProximaFase(faseDois);
      faseDois.setProximaFase(faseTres);
      faseTres.setProximaFase(faseQuatro);
      faseQuatro.setProximaFase(faseCinco);
      this.faseAtual = faseUm; // Começa na primeira fase
      this.faseAtual.constroiFase(); // Chama a construção da primeira fase
      this.hero = faseAtual.getHero();
    }

    public void mostrarGameOver() {
    if (this.gameTimer != null) {
        this.gameTimer.cancel(); // Para o loop do jogo (o repaint)
    }
    this.setVisible(false); // Esconde a janela do jogo

    // Agora sim, cria a tela de Game Over, passando ESTA tela (this)
    new GameOverScreen(this); 
    }
    
    public void resetGame() {
    // Lógica de reiniciar a fase (copiada da sua tecla 'T')
    this.faseAtual.getPersonagens().clear();
    //Fase novaFase = new Fase_1();
    //faseUm =novaFase;
    faseUm.constroiFase();
    faseAtual = faseUm;
    hero = faseAtual.getHero();
    this.atualizaCamera();
    }
    
    public int getCameraLinha() {
        return cameraLinha;
    }

    public int getCameraColuna() {
        return cameraColuna;
    }

    //public void ehPosicaoValida(Posicao p) {
        //return cj.ehPosicaoValida(this.faseAtual, p);
    //}

    public void adicionaModelo(Personagem umPersonagem) {
        faseAtual.addPersonagem(umPersonagem);
    }

    public void removePersonagem(Personagem umPersonagem) {
        faseAtual.removerPersonagem(umPersonagem);
    }

    public Graphics getGraphicsBuffer() {
        return g2;
    }

    @Override
public void paint(Graphics gOld) {
    // obtém o Graphics do BufferStrategy
    Graphics g = this.getBufferStrategy().getDrawGraphics();

    // cria um contexto considerando os insets (bordas) da janela
    g2 = g.create(
        getInsets().left,
        getInsets().top,
        getWidth() - getInsets().left - getInsets().right,
        getHeight() - getInsets().top - getInsets().bottom
    );

    final int HUD_ALTURA = 80; // altura da barra superior

    /* -------------------- DESENHA HUD -------------------- */
    try {
        
        g2.setColor(new java.awt.Color(20, 20, 30));
        g2.fillRect(0, 0, getWidth(), HUD_ALTURA);

        g2.setColor(java.awt.Color.CYAN);
        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        g2.drawString("Vida: " + hero.getVidas(), 16, 28);
        g2.drawString("Nro de Bombas: " + hero.getNumeroBombas(), 16, 52);
        g2.drawString("Bomba: " + hero.getNomeTipoBomba(), 16, 76);

    } catch (Exception e) {
        e.printStackTrace();
    }

    // ÁREA DO JOGO
    Graphics gGame = g2.create(0, HUD_ALTURA, getWidth(), getHeight() - HUD_ALTURA);

    // passa o graphics do jogo para o Desenho
    Desenho.setGraphics(gGame);

    try {
        if (!this.faseAtual.getPersonagens().isEmpty()) {
            this.cj.desenhaTudo(faseAtual);
            this.cj.processaTudo(faseAtual);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (gGame != null)
            gGame.dispose();

        Desenho.clearGraphics();
    }

    g.dispose();
    g2.dispose();

    if (!getBufferStrategy().contentsLost()) {
        getBufferStrategy().show();
    }
}


    private void atualizaCamera() {
        int linha = hero.getpPosicao().getLinha();
        int coluna = hero.getpPosicao().getColuna();

        cameraLinha = Math.max(0, Math.min(linha - Consts.RES / 2, Consts.MUNDO_ALTURA - Consts.RES));
        cameraColuna = Math.max(0, Math.min(coluna - Consts.RES / 2, Consts.MUNDO_LARGURA - Consts.RES));
    }

    public void go() {
        TimerTask task = new TimerTask() {
            public void run() {
                repaint();
            }
        };
        if(flagTryAgain == 0) {
            Timer timer = new Timer();
            timer.schedule(task, 0, Consts.PERIOD);
            flagTryAgain = 1;
        }
        
    }
    /*public void avancarFase() {
        this.faseAtual.getPersonagens().clear();
        Fase proxima = this.faseAtual.proximaFase();
        if (proxima != null) {
            this.faseAtual = proxima;
            this.hero = faseAtual.getHero();
            // A lógica de construção e reset já está no iniciarProximaFase()
            System.out.println("Fase avançada para: " + this.faseAtual.getNumeroDaFase());
        } else {
            System.out.println("Fim do Jogo! Todas as fases completadas.");
            // Lógica de Game Win
        }
    }*/
   
    public void setFaseAtual(Fase novaFase) {
    this.faseAtual = novaFase;
    this.hero = novaFase.getHero();
    this.atualizaCamera(); // Garante que a câmera foque o herói na nova fase
}
    
    // Função que printa a tela de vitoria e avança de fase
    public void vitoria() {
    if (this.gameTimer != null) {
        this.gameTimer.cancel(); // Para o loop do jogo (o repaint)
    }
    this.setVisible(false); // Esconde a janela do jogo

    // Agora, cria a tela de Vitoria, passando a fase ATUAL e ESTA tela (this)
    tWin = new VictoryScreen(this.faseAtual, this); 
}

    public void keyPressed(KeyEvent e) {
        try {
            if (teclasPressionadas.contains(e.getKeyCode()))
                    return;

            teclasPressionadas.add(e.getKeyCode());
            
            if (e.getKeyCode() == KeyEvent.VK_T) {//cria nova fase
                vitoria();
                //avancarFase();
                
                /*this.faseAtual.getPersonagens().clear();
                Fase novaFase = new Fase();
                novaFase.fase1();
                faseAtual = novaFase;
                hero = faseAtual.getHero();*/
                //this.atualizaCamera();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                hero.moveUp();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                hero.moveDown();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                hero.moveLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                hero.moveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                File tanque = new File("POO.dat");
                tanque.createNewFile();
                FileOutputStream canoOut = new FileOutputStream(tanque);
                ObjectOutputStream serializador = new ObjectOutputStream(canoOut);
                serializador.writeObject(faseAtual);
            } else if (e.getKeyCode() == KeyEvent.VK_L) {
                File tanque = new File("POO.dat");
                FileInputStream canoOut = new FileInputStream(tanque);
                ObjectInputStream serializador = new ObjectInputStream(canoOut);
                faseAtual = (Fase)serializador.readObject();
                hero = (Hero) faseAtual.getHero();
            } else if (e.getKeyCode() == KeyEvent.VK_B) {
                System.out.println("Colocando bomba...");
                hero.colocaBomba();
            }


            this.atualizaCamera();
            this.setTitle("-> Cell: " + (hero.getpPosicao().getLinha()) + ", " + (hero.getpPosicao().getColuna()));

            //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
        } catch (Exception ee) {

        }
    }
    public void keyReleased(KeyEvent e) {
        teclasPressionadas.remove(e.getKeyCode());        
    }    

    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado*/
        int x = e.getX();
        int y = e.getY();

        this.setTitle("X: " + x + ", Y: " + y
                + " -> Cell: " + (y / Consts.CELL_SIDE) + ", " + (x / Consts.CELL_SIDE));

        this.hero.getpPosicao().setPosicao(y / Consts.CELL_SIDE, x / Consts.CELL_SIDE);

        repaint();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2023-1 - Bomber man");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setMaximumSize(new java.awt.Dimension(800, 780));
        setMinimumSize(new java.awt.Dimension(800, 780));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public Fase getFaseAtual() {
        return faseAtual;
    }
}
