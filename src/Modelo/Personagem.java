package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Personagem extends Model implements Serializable  {

     /*Pode passar por cima?*/
           /*Se encostar, morre?*/
          
    int vida;
   
    protected Personagem(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG,linha,coluna);
        this.bMortal = false;
        
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

   

    public boolean moveUp() { 
        return this.pPosicao.moveUp();
    }

    public boolean moveDown() {
        return this.pPosicao.moveDown();
    }

    public boolean moveRight() {
        return this.pPosicao.moveRight();
    }

    public boolean moveLeft() {
        return this.pPosicao.moveLeft();
    }
    
    public void levaDano(int dano) {
        this.vida -= dano;
    }

    public int getVida() {
        return vida;
    }
    
}
