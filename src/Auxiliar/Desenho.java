package Auxiliar;

import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ImageIcon;
import Controler.Tela;

public class Desenho implements Serializable {

    static Tela jCenario;

    public static void setCenario(Tela umJCenario) {
        jCenario = umJCenario;
    }

    public static Tela acessoATelaDoJogo() {
        return jCenario;
    }

    public static Graphics getGraphicsDaTela() {
        return jCenario.getGraphicsBuffer();
    }

    public static void desenhar(ImageIcon iImage, int iColuna, int iLinha) {
        int telaX = (iColuna - jCenario.getCameraColuna()) * Consts.CELL_SIDE;
        int telaY = (iLinha - jCenario.getCameraLinha()) * Consts.CELL_SIDE;

      
        
        if (telaX >= 0 && telaX < Consts.RES * Consts.CELL_SIDE
                && telaY >= 0 && telaY < Consts.RES * Consts.CELL_SIDE) {
            iImage.paintIcon(jCenario, getGraphicsDaTela(), telaX, telaY);
        }
    }
    /*public static void desenhar(ImageIcon iImage, float pixelX, float pixelY) {
    
    // NOVO: Converte a posição da câmera (que está em grade) para um offset de pixel
        float cameraPixelX = jCenario.getCameraColuna() * Consts.CELL_SIDE;
        float cameraPixelY = jCenario.getCameraLinha() * Consts.CELL_SIDE;
    
    // Calcula a posição na tela (em pixels) subtraindo o offset da câmera
    // O resultado é a posição que o sprite deve ter na janela.
        int telaX = (int)(pixelX - cameraPixelX);
        int telaY = (int)(pixelY - cameraPixelY);

    // Condição de visibilidade: o sprite é desenhado se estiver *dentro* da área
    // visível (Consts.RES * Consts.CELL_SIDE)
        if (telaX >= -Consts.CELL_SIDE && telaX < Consts.RES * Consts.CELL_SIDE
            && telaY >= -Consts.CELL_SIDE && telaY < Consts.RES * Consts.CELL_SIDE) {
            iImage.paintIcon(jCenario, getGraphicsDaTela(), telaX, telaY);
        }
    }*/
}
