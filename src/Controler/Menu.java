package Controler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controler.Tela;

public class Menu extends JFrame {

    private Image backgroundImage;

    public Menu() {

        // Carrega a imagem (ajuste o path se necessário)
        backgroundImage = new ImageIcon(getClass().getResource("/imagens/Menu.png")).getImage();

        setTitle("Menu Bomberman");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel que desenha o fundo
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        painel.setLayout(new GridBagLayout());

        // Painel de botões (transparente)
        JPanel painelBotoes = new JPanel();
        painelBotoes.setOpaque(false);
        painelBotoes.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnJogar = new JButton("Jogar");
        JButton btnSair = new JButton("Sair");

        btnJogar.setFont(new Font("Arial", Font.BOLD, 22));
        btnSair.setFont(new Font("Arial", Font.BOLD, 22));

        painelBotoes.add(btnJogar);
        painelBotoes.add(btnSair);

        painel.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(100, 0, 0, 200); // mover 50px para a direita
    gbc.gridx = 0;
    gbc.gridy = 0;

painel.add(painelBotoes, gbc);


        add(painel);

        // Ações
        btnJogar.addActionListener(e -> iniciarJogo());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void iniciarJogo() {
        dispose();
        Tela tTela = new Tela();
        tTela.setVisible(true);
        tTela.createBufferStrategy(2);
        tTela.go();
    }
}
