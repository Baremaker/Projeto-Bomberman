package Auxiliar;

import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ImageIcon;
import Controler.Tela;

public class Desenho implements Serializable {

    static Tela jCenario;

    // gráfico usado para desenhar o jogo (com offset aplicado)
    private static Graphics gGame = null;

    public static void setCenario(Tela umJCenario) {
        jCenario = umJCenario;
    }

    public static Tela acessoATelaDoJogo() {
        return jCenario;
    }

    public static Graphics getGraphicsDaTela() {
        if (gGame != null) return gGame;
        return jCenario.getGraphicsBuffer();
    }

    public static void setGraphics(Graphics g) {
        gGame = g;
    }

    public static void clearGraphics() {
        gGame = null;
    }

    public static void desenhar(ImageIcon iImage, int iColuna, int iLinha) {
        
        int telaX = (iColuna - jCenario.getCameraColuna()) * Consts.CELL_SIDE;
        int telaY = (iLinha - jCenario.getCameraLinha()) * Consts.CELL_SIDE;

        Graphics g = getGraphicsDaTela();
        if (g == null) return;

        if (telaX >= 0 && telaX < Consts.RES * Consts.CELL_SIDE
                && telaY >= 0 && telaY < Consts.RES * Consts.CELL_SIDE) {
            iImage.paintIcon(jCenario, g, telaX, telaY);
        }
    }
    public static void desenharHero(ImageIcon iImage, int iColuna, int iLinha, int stage, int paraHorizontal, int paraVertical) {
        
        int telaX = (iColuna - jCenario.getCameraColuna()) * Consts.CELL_SIDE + (int)((stage/3.0f)*Consts.CELL_SIDE*paraHorizontal);
        int telaY = (iLinha - jCenario.getCameraLinha()) * Consts.CELL_SIDE + (int)((stage/3.0f)*Consts.CELL_SIDE*paraVertical);

        Graphics g = getGraphicsDaTela();
        if (g == null) return;

        if (telaX >= 0 && telaX < Consts.RES * Consts.CELL_SIDE
                && telaY >= 0 && telaY < Consts.RES * Consts.CELL_SIDE) {
            iImage.paintIcon(jCenario, g, telaX, telaY);
        }
    }
}
