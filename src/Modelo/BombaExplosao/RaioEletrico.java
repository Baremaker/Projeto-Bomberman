/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;

import Auxiliar.Consts;
import Modelo.Personagem;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author aserr
 */
public class RaioEletrico extends Personagem implements Serializable{
    
    public RaioEletrico(String sNomeImagePNG,int linha, int coluna) {
        // Use uma imagem de raio, por exemplo: "raioEletrico.png"
        super(sNomeImagePNG, linha, coluna); 
        setiImage(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMortal = false; 
    }
    public void setiImage(String sNomeImagePNG) {
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH +Consts.PATHBOMBA+ sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
            //System.out.println("imprimiu corretamente");
        } catch (IOException ex) {
            System.out.println("Erro ao carregar imagem"+ex.getMessage());
        }
    }
    @Override
    public void autoDesenho() {
        // O Raio apenas se desenha. A remoção será gerenciada por uma classe externa.
        super.autoDesenho();
    }
    @Override
    public boolean validaPosicao() {
        return true; 
    }
}
