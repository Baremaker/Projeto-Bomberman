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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Classe mãe de todos os personagens: heroi, inimigos, bomba, explosao etc.
//Utiliza os atributos da classe mãe Model, além disso, tem os atributos:
//É mortal? bMortal, Vida: vida
public abstract class Personagem extends Model implements Serializable {
    protected boolean bMortal;
    protected int vida;
   
    protected Personagem(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        this.bMortal = false;
        this.vida = 3;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void levaDano(int dano) {
        this.vida -= dano;
    }

    //Essa função valida a posição APÓS a movimentação: se a posição não for válida ele retorna para a posição anterior
    //Função geral para validar apenas se esta fora ou não da tela
    protected boolean validaPosicao(){
        if(this.pPosicao.getLinha() < 0 || this.pPosicao.getLinha() >= Auxiliar.Consts.MUNDO_ALTURA){
            voltaAUltimaPosicao();
            return false;
        }
        if(this.pPosicao.getColuna() < 0 || this.pPosicao.getColuna() >= Auxiliar.Consts.MUNDO_LARGURA){
            voltaAUltimaPosicao();
            return false;
        }
        return true;
    }

    public boolean voltaAUltimaPosicao(){
        return this.pPosicao.volta();
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
}
