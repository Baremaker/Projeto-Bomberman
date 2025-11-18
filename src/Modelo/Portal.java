/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
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
public class Portal extends Blocos implements Serializable{
    
    public Portal(int linha, int coluna) {
        // Assume que você tem um arquivo de imagem para o portal, por exemplo, "portal.png"
        super("portal.png", linha, coluna); 
        this.bTransponivel = true; // O herói pode pisar nele
        this.bDestrutivel = false;
        this.codigo = 'P'; // Novo código para o mapa (opcional)
    }
    public void setiImage(String sNomeImagePNG) {
        // Adapte o path se necessário, seguindo o padrão da sua classe Blocos
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH  + sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
