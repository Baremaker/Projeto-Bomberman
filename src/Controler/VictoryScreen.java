package Controler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Auxiliar.Fase;
import Modelo.Hero;
import java.io.Serializable;

public class VictoryScreen extends JFrame implements Serializable {

    private Hero hero;
    private static final int FASE_FINAL = 5;
    private String CAMINHO_IMAGEM;
    private Fase faseAtual;
    private Tela telaPrincipal;
    
    public VictoryScreen(Fase faseAtual, Tela tTela) {
        this.faseAtual = faseAtual;
        this.hero = faseAtual.getHero();
        this.telaPrincipal = tTela;
        initComponents();
    }
    
    public void avancarFase() {
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
    }
    
    
    public void initComponents() {
        
        //Configuração da Janela (JFrame)
        setTitle("Vitória!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao clicar no 'X'
        setResizable(false);

        //Criação dos Botões --> Lógica Condicional do Botão "Próxima Fase"
        // Os botões "Próxima Fase" e "Sair do Jogo" só aparecem se a fase ATUAL não for a final
        if (faseAtual.getNumeroDaFase() < FASE_FINAL) {
            CAMINHO_IMAGEM = "/imagens/victoryScreen.png";
            //Carregamento da Imagem de Fundo
            ImageIcon imagemDeFundo = new ImageIcon(getClass().getResource(CAMINHO_IMAGEM));
        
            // Cria um JLabel para guardar a imagem
            JLabel labelFundo = new JLabel(imagemDeFundo);
        
            // Define o tamanho da janela para ser exatamente o tamanho da imagem
            setSize(800, 700);
            // Apenas checa se a imagem foi encontrada para logar o erro
            if (imagemDeFundo.getIconWidth() <= 0) {
                System.err.println("Erro: Imagem de vitória não encontrada em: " + CAMINHO_IMAGEM);
            }
        
            // Define o layout como nulo (absolute layout) para posicionar os botões
            // manualmente sobre a imagem.
            labelFundo.setLayout(null);
            setContentPane(labelFundo); // Define o JLabel como o conteúdo principal da janela
        
            JButton btnSair = new JButton("Sair do Jogo");
            // Posição (x, y, largura, altura)
            btnSair.setBounds(450, 500, 150, 40); 
            labelFundo.add(btnSair); // Adiciona o botão ao label de fundo
            
            JButton btnProximaFase = new JButton("Próxima Fase");
            // Posição (x, y, largura, altura) 
            btnProximaFase.setBounds(200, 500, 150, 40); 
            labelFundo.add(btnProximaFase); // Adiciona o botão ao label de fundo
            
            // Ação do botão de proxima fase
            btnProximaFase.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    avancarFase(); // Isso atualiza 'this.faseAtual' para a próxima

                    // Devolve a fase nova para a tela principal
                    telaPrincipal.setFaseAtual(faseAtual); 

                    System.out.println("Iniciando próxima fase: " + faseAtual.getNumeroDaFase());

                    // Fecha esta janela de vitória
                    dispose(); 

                    // Mostra a tela do jogo e reinicia o loop
                    telaPrincipal.setVisible(true);
                    telaPrincipal.createBufferStrategy(2);
                    telaPrincipal.go();
                }
            });

            // Ação do botão de sair
            btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ação padrão: Fecha toda a aplicação
                    System.exit(0);
                }
            });
        }
        
        
        if (faseAtual.getNumeroDaFase() == FASE_FINAL){
            CAMINHO_IMAGEM = "/imagens/victoryScreenFinal.png";
            //Carregamento da Imagem de Fundo
            ImageIcon imagemDeFundo = new ImageIcon(getClass().getResource(CAMINHO_IMAGEM));
        
            // Cria um JLabel para guardar a imagem
            JLabel labelFundo = new JLabel(imagemDeFundo);
        
            /// Define o tamanho da janela para ser exatamente o tamanho da imagem
            setSize(800, 700);
            // Apenas checa se a imagem foi encontrada para logar o erro
            if (imagemDeFundo.getIconWidth() <= 0) {
                System.err.println("Erro: Imagem de vitória não encontrada em: " + CAMINHO_IMAGEM);
            }
        
            // Define o layout como nulo (absolute layout) para posicionar os botões
            // manualmente sobre a imagem.
            labelFundo.setLayout(null);
            setContentPane(labelFundo); // Define o JLabel como o conteúdo principal da janela
             // Texto dos créditos
            String creditosTexto =
                "<html><center>" +
                "<h1>CRÉDITOS</h1>" +
                "<p>Bomberman Game</p>" +
                "<p>Desenvolvido por:</p>" +
                "<p>Alexandre Coura Serravite</p>" +
                "<p>Bruno Baremaker Moraes</p>" +
                "<p>Matheus Watanabe de Vilhena</p>" +
                "<p>Rafael O. Oliveira</p>" +
                "<p>Arte:</p>" +
                "<p>Alexandre Coura Serravite</p>" +
                "<p>Bruno Baremaker Moraes</p>" +
                "<p>Matheus Watanabe de Vilhena</p>" +
                "<p>Rafael O. Oliveira</p>" +
                "<p>Código:</p>" +
                "<p>Alexandre Coura Serravite</p>" +
                "<p>Bruno Baremaker Moraes</p>" +
                "<p>Matheus Watanabe de Vilhena</p>" +
                "<p>Rafael O. Oliveira</p>" +
                "<p>Design de fases:</p>" +
                "<p>Alexandre Coura Serravite</p>" +
                "<p>Bruno Baremaker Moraes</p>" +
                "<p>Matheus Watanabe de Vilhena</p>" +
                "<p>Rafael O. Oliveira</p>" +
                "<p>Agradecimentos especiais:</p>" +
                "<P>Prof. José Fernando Rodrigues Júnior </P>" +
                "<p>- Comunidade Java</p>" +
                "<br><br><p>Obrigado por jogar!</p>" +
                "</center></html>";

            JLabel creditos = new JLabel(creditosTexto);
            creditos.setHorizontalAlignment(SwingConstants.CENTER);
            creditos.setForeground(Color.WHITE); // O texto continua BRANCO
            creditos.setFont(new Font("Arial", Font.BOLD, 22));

            // --- NOSSA CORREÇÃO COMEÇA AQUI ---
            
            // 1. Crie um painel com um método de "paint" customizado
            JPanel painelCreditos = new JPanel(new java.awt.BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    // Primeiro, pinta o fundo semitransparente manualmente
                    g.setColor(new Color(0, 0, 0, 180)); // Preto, 70% transparente
                    g.fillRect(0, 0, getWidth(), getHeight());
                    
                    // DEPOIS, chama o 'super' para desenhar os filhos (o texto) por cima
                    super.paintComponent(g);
                }
            };

            // 2. MUITO IMPORTANTE: Diga ao Swing que este painel NÃO é opaco
            //    Isso avisa que a imagem de fundo (labelFundo) precisa ser desenhada
            painelCreditos.setOpaque(false); 
            
            // 3. Adicione o label de texto DENTRO do painel
            painelCreditos.add(creditos, java.awt.BorderLayout.CENTER);

            // 4. Defina o tamanho e posição inicial do PAINEL (não mais do label)
            painelCreditos.setBounds(0, getHeight(), getWidth(), 800);
            
            // 5. Adicione o PAINEL (e não o label) ao fundo
            labelFundo.add(painelCreditos);

            // Timer para animação de subida
            Timer timer = new Timer(20, null);
            timer.addActionListener(new ActionListener() {
            int posY = getHeight();

            @Override
            public void actionPerformed(ActionEvent e) {
                posY -= 1; // velocidade da subida
                
                // 6. Anime a posição do PAINEL
                painelCreditos.setLocation(0, posY);

                // Quando o texto sair completamente da tela, finalize o jogo
                if (posY + 800 < 0) {
                    timer.stop();
                    //System.exit(0);
                    dispose();      // fecha a tela de vitoria
                    java.awt.EventQueue.invokeLater(() -> {
                    Menu menu = new Menu();
                    menu.setVisible(true);
                    });
                }
            }
        });

    timer.start();
} 
        // Finalização
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true); // Torna a janela visível
    }
}
