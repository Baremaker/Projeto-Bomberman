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

public class BichinhoVaiVemVertical extends Personagem implements Serializable {

    private boolean bUp;
    int iContador;
    private String nomeImagem;

    public BichinhoVaiVemVertical(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna); 
        bUp = true;
        iContador = 0;
        this.bTransponivel = true;
        this.bMortal = true;
        this.vida = 6;
        this.nomeImagem = sNomeImagePNG;
    }

    public void autoDesenho() {
        if(!paralisia()){
            if (iContador == 5) {
                iContador = 0;
                if (bUp) {
                    if (!this.moveUp()) {
                        bUp = false;
                    }
                } else {
                    if (!this.moveDown()) { 
                        bUp = true;
                    }
                }
            }
            iContador++;
        }

        super.autoDesenho();
    }
}