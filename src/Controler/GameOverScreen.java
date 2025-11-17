//Sempre que o jogador morrer, basta chamar: new GameOverScreen(() -> iniciarNovoJogo());
//Onde iniciarNovoJogo() é o método que você já usa para reiniciar o mapa/estado do Bomberman.
package Controler; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOverScreen extends JFrame {

    public GameOverScreen(Runnable onRetry) {
        setTitle("Game Over");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- IMAGEM GAME OVER ----------
        ImageIcon img = new ImageIcon(getClass().getResource("/imagens/gameOver.jpg"));
        JLabel lblImagem = new JLabel(img);
        lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblImagem, BorderLayout.CENTER);

        // ---------- PAINEL DE BOTÕES ----------
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        JButton btnRetry = new JButton("Tentar novamente");
        JButton btnExit = new JButton("Sair");

        painelBotoes.add(btnRetry);
        painelBotoes.add(btnExit);

        add(painelBotoes, BorderLayout.SOUTH);

        // ---------- AÇÕES DOS BOTÕES ----------
        btnRetry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();      // fecha a tela de game over
                onRetry.run();  // chama a função que reinicia o jogo
            }
        });

        btnExit.addActionListener(e -> System.exit(0));

        pack();
        setLocationRelativeTo(null); // centraliza a janela
        setVisible(true);
    }
}
