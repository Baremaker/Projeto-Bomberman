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
import Auxiliar.GerenciaDnD;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JButton;

public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener, DropTargetListener, Serializable {

    private Hero hero;
    private Fase faseAtual,faseUm,faseDois,faseTres,faseQuatro,faseCinco;
    private VictoryScreen tWin;
    private Timer gameTimer;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;
    private GerenciaDnD gerenciadorDrops = new GerenciaDnD();
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
        new DropTarget(this, this);
        /*teclado*/
        new DropTarget(this, this);
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

        g2.drawString("Vida: " + (hero.getNumeroPowerupsVida()+1), 16, 28);
        g2.drawString("Fase: " + faseAtual.getNumeroDaFase(), 16, 52);
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
            this.processarMovimentoContinua();
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
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                salvarEstadoJogo();
            } else if (e.getKeyCode() == KeyEvent.VK_L) {
                carregarEstadoJogo();
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

        this.hero.getpPosicao().setPosicao((y - 110) / Consts.CELL_SIDE, x / Consts.CELL_SIDE);

        repaint();
    }
    
    
    public void salvarEstadoJogo() {
    String nomeArquivo = "savegame.ser"; 
    
    try (
        
        FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
        // 2. Opcional: Adiciona compressão GZIP, pesquisei e dizia que diminuia o espaço ocupado
        GZIPOutputStream zipOut = new GZIPOutputStream(fileOut);
        // 3. Adiciona o ObjectOutputStream para serializar o objeto
        ObjectOutputStream objectOut = new ObjectOutputStream(zipOut)
    ) {
        // Escreve o objeto 'faseAtual' no arquivo.
        objectOut.writeObject(this.faseAtual); 
        System.out.println("Jogo salvo " + nomeArquivo);
        
    } catch (IOException i) {
        i.printStackTrace();
        System.err.println("Erro ao salvar : " + i.getMessage());
    }
    
    
    }
    
    
    public void carregarEstadoJogo() {
       String nomeArquivo = "savegame.ser"; 
    
    // Verifica se o arquivo existe antes de tentar carregar
    File saveFile = new File(nomeArquivo);
        if (!saveFile.exists()) {
            System.out.println("️ Arquivo de salve (" + nomeArquivo + ") não encontrado.");
            return;
        }
    
        try (
           
            FileInputStream fileIn = new FileInputStream(nomeArquivo);
 
            GZIPInputStream zipIn = new GZIPInputStream(fileIn);
            // 3. Adiciona o ObjectInputStream para desserializar o objeto
            ObjectInputStream objectIn = new ObjectInputStream(zipIn)
        ) {
        // Lê o objeto do arquivo e faz o cast para Fase
            this.faseAtual.getPersonagens().clear();
            Fase faseCarregada = (Fase) objectIn.readObject();
        
        // --- Atualiza o estado do jogo ---
        
        // 1. Define a fase atual com o objeto carregado
            this.setFaseAtual(faseCarregada);
            //faseCarregada.recarregarImagens();
           
            System.out.println(" Jogo carregado, Fase: " + faseCarregada.getNumeroDaFase());
        
        } catch (IOException | ClassNotFoundException i) {
        i.printStackTrace();
        System.err.println("Erro ao carregar o jogo: " + i.getMessage());
        } 
    }
    
    @Override
    public void drop(DropTargetDropEvent event) {
        event.acceptDrop(DnDConstants.ACTION_COPY);

        Point ponto = event.getLocation();
        System.out.println("Posicao do drop: x: " + ponto.x + " y: " + ponto.y);
        int tileX = ponto.y - 110, tileY = ponto.x;

        Transferable t = event.getTransferable();

        try {
            List<File> arquivos = new java.util.ArrayList<>();

            // TENTATIVA 1: Padrão (FileList)
            if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                try {
                    arquivos = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                } catch (Exception e) {
                    System.out.println("FileList falhou, tentando Plano B (URI)...");
                }
            }

            // TENTATIVA 2: Plano B (URI/Texto) - Caso a 1 falhe ou retorne vazio
            if (arquivos.isEmpty()) {
                System.out.println("Arquivo vazio");
                // Tenta pegar sabores de texto/uri que apareceram no seu log
                DataFlavor uriFlavor = null;
                
                // Procura por um sabor de texto compatível
                for (DataFlavor f : t.getTransferDataFlavors()) {
                    if (f.isRepresentationClassReader() || f.isRepresentationClassCharBuffer() || String.class.equals(f.getRepresentationClass())) {
                        // Prioriza uri-list se existir
                        if (f.getMimeType().contains("uri-list")) {
                            uriFlavor = f;
                            break;
                        }
                    }
                }

                if (uriFlavor != null) {
                    try {
                        // Lê os dados como String (ex: file:///C:/Users/Docs/arquivo.zip)
                        Object data = t.getTransferData(uriFlavor);
                        String rawUri = "";
                        
                        if (data instanceof String) rawUri = (String) data;
                        else if (data instanceof java.io.Reader) {
                            // Se vier como Reader (comum no Linux/Mac)
                            java.io.BufferedReader reader = new java.io.BufferedReader((java.io.Reader) data);
                            StringBuilder sb = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) sb.append(line).append("\n");
                            rawUri = sb.toString();
                        }

                        // Converte as URIs em Arquivos
                        String[] lines = rawUri.split("\\r?\\n");
                        for (String line : lines) {
                            if (line.startsWith("file:")) {
                                // Remove o protocolo "file://" e limpa espaços codificados (%20)
                                java.net.URI uri = new java.net.URI(line.trim());
                                arquivos.add(new File(uri));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // --- AGORA PROCESSA OS ARQUIVOS ENCONTRADOS ---
            if (!arquivos.isEmpty()) {
                File arquivoZip = arquivos.get(0);
                System.out.println("Arquivo capturado com sucesso: " + arquivoZip.getAbsolutePath());
                
                // CHAMA SEU MÉTODO DE LER O ZIP AQUI
                Personagem p = gerenciadorDrops.carregaPersonagemZIP(arquivoZip);
                p.setPosicao(tileX / Consts.CELL_SIDE, tileY / Consts.CELL_SIDE);
                Desenho.acessoATelaDoJogo().getFaseAtual().addPersonagem(p);
                
                event.dropComplete(true);
            } else {
                System.err.println("Nenhum arquivo pôde ser extraído do drop.");
                event.dropComplete(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            event.dropComplete(false);
        }
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }
    
    

         
    
    public void dragExit(DropTargetEvent dte) {
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }
    
    private void processarMovimentoContinua() {
    
        if (teclasPressionadas.contains(KeyEvent.VK_UP)) {
            hero.moveUp();
        } else if (teclasPressionadas.contains(KeyEvent.VK_DOWN)) {
            hero.moveDown();
        }

    
        if (teclasPressionadas.contains(KeyEvent.VK_LEFT)) {
            hero.moveLeft();
        } else if (teclasPressionadas.contains(KeyEvent.VK_RIGHT)) {
            hero.moveRight();
        }
    
    // Se o herói se moveu, atualizamos a câmera
        if (!teclasPressionadas.isEmpty()) {
            this.atualizaCamera();
            // O repaint() será chamado logo em seguida pelo loop principal (go())
        }
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

    public GerenciaDnD getGerenciadorDrops() {
        return gerenciadorDrops;
    }

}
