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

    /**
     * Retorna o Graphics que deve ser usado pelas chamadas de desenho.
     * Se setGraphics foi chamado, retorna esse gráfico (com offset HUD).
     * Caso contrário, cai para o comportamento antigo (retorna o buffer da tela).
     */
    public static Graphics getGraphicsDaTela() {
        if (gGame != null) return gGame;
        return jCenario.getGraphicsBuffer();
    }

    /**
     * Permite ao código externo (Tela.paint) fornecer o Graphics que deve ser usado
     * para desenhar o jogo. Esse Graphics normalmente já tem o offset (y) aplicado.
     */
    public static void setGraphics(Graphics g) {
        gGame = g;
    }

    /**
     * Limpa a referência ao Graphics (usar quando terminar de desenhar).
     */
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

    /* Se você tiver outras versões de desenhar que usam posições em pixels,
       pode adaptar para usar getGraphicsDaTela() da mesma forma. */
}
