package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import Auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Classe mãe de todos os personagens: heroi, inimigos, bomba, explosao etc.
//Utiliza os atributos da classe mãe Model, além disso, tem os atributos:
//É mortal? bMortal, Vida: vida
public abstract class Personagem extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    protected boolean bMortal;
    protected int vida;
    protected int dano;
    protected int timerMorte;
    protected String nomeImagem;
   
    protected Personagem(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        setiImage(sNomeImagePNG);
        this.bMortal = true;
        this.vida = 3;
        this.dano = 0;
        this.nomeImagem = sNomeImagePNG;
        this.timerMorte = -1;
    }
    @Override
    public String getsNomeImagePNG() {
        return sNomeImagePNG;
    }
   
    public void setiImage(String sNomeImagePNG) {
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH +Consts.PATHINIMIGOS + sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void levaDano(int dano) {
        this.vida -= dano;
        if(this.vida <=0)
            this.timerMorte = 2;
        System.out.println("vida perdidaPersonagem:"+this.vida);
    }

    public boolean moveUp() { 
        if(this.pPosicao.moveUp()){
            return validaPosicao();
        }
        return false;
    }

    public boolean moveDown() {
        if(this.pPosicao.moveDown()){
            return validaPosicao();
        }
        return false;
    }

    public boolean moveRight() {
        if(this.pPosicao.moveRight()){
            return validaPosicao();
        }
        return false;
    }

    public boolean moveLeft() {
        if(this.pPosicao.moveLeft()){
            return validaPosicao();
        }
        return false;
    }

    public boolean isbMortal() {
        return bMortal;
    }
    public boolean ehInimigo(){
        return false;
    }
    public int getDano() {
        return dano;
    }

    public int getTimerMorte() {
        return timerMorte;
    }
    public void autoDesenho() {
        if(this.timerMorte>0){
            this.timerMorte--;
            this.setiImage("Dead_"+this.nomeImagem);            
        }
        Desenho.desenhar(this.iImage, this.pPosicao.getColuna(), this.pPosicao.getLinha());
    }

}
