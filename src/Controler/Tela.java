package Controler;
import Modelo.BlocoVazio;
import Modelo.Bomba;
import Modelo.Explosao;
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
    private Fase faseAtual;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;
    private int cameraLinha = 0;
    private int cameraColuna = 0;
    private final Set<Integer> teclasPressionadas = new HashSet<>();
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

        faseAtual = new Fase();
        faseAtual.fase1();
        hero = faseAtual.getHero();
    }

    public int getCameraLinha() {
        return cameraLinha;
    }

    public int getCameraColuna() {
        return cameraColuna;
    }

    public boolean ehPosicaoValida(Posicao p) {
        return cj.ehPosicaoValida(this.faseAtual, p);
    }

    public void adicionaModelo(Personagem umPersonagem) {
        faseAtual.addPersonagem(umPersonagem);
    }

    public void removePersonagem(Personagem umPersonagem) {
        faseAtual.removerPersonagem(umPersonagem);
    }

    public Graphics getGraphicsBuffer() {
        return g2;
    }

    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
            
        if (!this.faseAtual.getPersonagens().isEmpty()) {
           /* float velocidade = Consts.HERO_SPEED_PIXELS;
    
    // Zera a velocidade X/Y para o Hero, pois apenas as teclas ativas irão redefini-la
            hero.pPosicao.velocidadeX = 0;
            hero.pPosicao.velocidadeY = 0;

    // APLICA VELOCIDADE se a tecla estiver no HashSet
            if (teclasPressionadas.contains(KeyEvent.VK_UP)) {
                hero.moveUp(); // moveUp agora define: hero.pPosicao.velocidadeY = -velocidade;
            }
            if (teclasPressionadas.contains(KeyEvent.VK_DOWN)) {
                hero.moveDown();
            }
            if (teclasPressionadas.contains(KeyEvent.VK_LEFT)) {
                hero.moveLeft();
            }
            if (teclasPressionadas.contains(KeyEvent.VK_RIGHT)) {
                hero.moveRight();
            }
    
            // 2. Colisão AABB e Atualização da Posição
            float nextX = hero.pPosicao.getX() + hero.pPosicao.velocidadeX;
            float nextY = hero.pPosicao.getY() + hero.pPosicao.velocidadeY;
    
            if (cj.checarColisaoAABB(this.getFaseAtual(), hero, nextX, nextY)) {
            // Se for válido, move para a nova posição de pixel e sincroniza a grade
                hero.pPosicao.setX(nextX); 
                hero.pPosicao.setY(nextY);
                hero.pPosicao.updatePixelPosition(); 
            } else {
                // Se colidir, zera a velocidade para parar imediatamente
                hero.pPosicao.velocidadeX = 0;
                hero.pPosicao.velocidadeY = 0;
            }
            */
            this.cj.desenhaTudo(faseAtual);
            this.cj.processaTudo(faseAtual);
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
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.PERIOD);
    }
    
    public void keyPressed(KeyEvent e) {
        try {
            if (teclasPressionadas.contains(e.getKeyCode()))
                    return;

            teclasPressionadas.add(e.getKeyCode());
            
            if (e.getKeyCode() == KeyEvent.VK_T) {//cria nova fase
                this.faseAtual.getPersonagens().clear();
                //ArrayList<Personagem> novaFase = new ArrayList<Personagem>();
                Fase novaFase = new Fase();
                novaFase.fase1();
                /*Cria faseAtual adiciona personagens*/
               /*hero = new Hero("Robbo.png", 10, 10);
                hero.setPosicao(10, 10);
                novaFase.add(hero);
                this.atualizaCamera();

                ZigueZague zz = new ZigueZague("bomba.png", 0, 0);
                novaFase.add(zz);

                Esfera es = new Esfera("esfera.png", 4, 4);
                novaFase.add(es);*/
                
                faseAtual = novaFase;
                hero = faseAtual.getHero();
                this.atualizaCamera();
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
        setMaximumSize(new java.awt.Dimension(800, 800));
        setMinimumSize(new java.awt.Dimension(800, 800));
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
