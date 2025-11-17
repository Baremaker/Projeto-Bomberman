package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BichinhoVaiVemHorizontal extends Personagem implements Serializable {

    private boolean bRight;
    int iContador;

    public BichinhoVaiVemHorizontal(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        //setiImage(sNomeImagePNG);
        bRight = true;
        iContador = 0;
        this.bTransponivel = true;
        this.bMortal = true;     
    }

    public void autoDesenho() {
        if(!paralisia()){
            if (iContador == 5) {
                iContador = 0;
                if (bRight) {
                    this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna() + 1);
                } else {
                    this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna() - 1);
                }

                bRight = !bRight;
            }
        iContador++;
        }
        
        super.autoDesenho();
        //iContador++;
    }
}
