package Controler;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VictoryScreen extends JFrame {

    private static final int FASE_FINAL = 5;

    private String CAMINHO_IMAGEM;

    private int faseAtual;
    
    public VictoryScreen(int faseAtual) {
        this.faseAtual = faseAtual;
        initComponents();
    }
    
    private void initComponents() {
        
        //Configuração da Janela (JFrame)
        setTitle("Vitória!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao clicar no 'X'
        setResizable(false);

        //Criação dos Botões --> Lógica Condicional do Botão "Próxima Fase"
        // Os botões "Próxima Fase" e "Sair do Jogo" só aparecem se a fase ATUAL não for a final
        if (faseAtual < FASE_FINAL) {
            CAMINHO_IMAGEM = "/imagens/victoryScreen.jpeg";
            //Carregamento da Imagem de Fundo
            ImageIcon imagemDeFundo = new ImageIcon(CAMINHO_IMAGEM);
        
            // Cria um JLabel para guardar a imagem
            JLabel labelFundo = new JLabel(imagemDeFundo);
        
            // Define o tamanho da janela para ser exatamente o tamanho da imagem
            if (imagemDeFundo.getIconWidth() > 0) {
                setSize(imagemDeFundo.getIconWidth(), imagemDeFundo.getIconHeight());
            } else {
                // Fallback caso a imagem não seja encontrada
                System.err.println("Erro: Imagem de vitória não encontrada em: " + CAMINHO_IMAGEM);
                setSize(800, 600); // Tamanho padrão
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
                    System.out.println("Iniciando próxima fase: " + (faseAtual + 1));
                    // Chame aqui o método do SEU JOGO para iniciar a próxima fase
                    // Exemplo: 
                    // seuControladorDeJogo.iniciarFase(faseAtual + 1);
                    
                    // ----- FIM DA ÁREA DE INTEGRAÇÃO -----
                    
                    // Fecha esta janela de vitória
                    dispose(); 
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
        //-------------------------------------------------------------------
        if (faseAtual == FASE_FINAL){
            CAMINHO_IMAGEM = "/imagens/victoryScreenFinal.jpeg";
            //Carregamento da Imagem de Fundo
            ImageIcon imagemDeFundo = new ImageIcon(CAMINHO_IMAGEM);
        
            // Cria um JLabel para guardar a imagem
            JLabel labelFundo = new JLabel(imagemDeFundo);
        
            // Define o tamanho da janela para ser exatamente o tamanho da imagem
            if (imagemDeFundo.getIconWidth() > 0) {
                setSize(imagemDeFundo.getIconWidth(), imagemDeFundo.getIconHeight());
            } else {
                // Fallback caso a imagem não seja encontrada
                System.err.println("Erro: Imagem de vitória não encontrada em: " + CAMINHO_IMAGEM);
                setSize(800, 600); // Tamanho padrão
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
                "<p>Arte: Bruno Moraes</p>" +
                "<p>Código: Bruno Moraes</p>" +
                "<p>Design de fases: Bruno Moraes</p>" +
                "<p>Agradecimentos especiais:</p>" +
                "<p>- Família</p>" +
                "<p>- Amigos</p>" +
                "<p>- Comunidade Java</p>" +
                "<br><br><p>Obrigado por jogar!</p>" +
                "</center></html>";

            JLabel creditos = new JLabel(creditosTexto);
            creditos.setHorizontalAlignment(SwingConstants.CENTER);
            creditos.setForeground(Color.WHITE);
            creditos.setFont(new Font("Arial", Font.BOLD, 22));

            // Define o tamanho e posição inicial (abaixo da tela)
            creditos.setBounds(0, getHeight(), getWidth(), 600);
            labelFundo.add(creditos);

            // Timer para animação de subida
            Timer timer = new Timer(20, null);
            timer.addActionListener(new ActionListener() {
            int posY = getHeight();

            @Override
            public void actionPerformed(ActionEvent e) {
                posY -= 1; // velocidade da subida
                creditos.setLocation(0, posY);

                // Quando o texto sair completamente da tela, finalize o jogo
                if (posY + 600 < 0) {
                    timer.stop();
                    System.exit(0);
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
