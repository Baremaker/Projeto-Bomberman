 package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Fase;
import Auxiliar.Mapa;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Clase heroi: estende a classe mãe personagem e outra classe mãe Model
//Possui os atributos:
//numeroBombas, tamanhoBomba, tipoBomba, velocidade
public class Hero extends Personagem implements Serializable {
    private int numeroBombas = 1;
    private int tamanhoBomba = 1;
    private int tipoBomba = 1;
    private int velocidade;
    
    public Hero(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        vida = 100;
    }
    
    public boolean setPosicao(int linha, int coluna){
        if(this.pPosicao.setPosicao(linha, coluna)){
            //return validaPosicao();
            return true;
        }
        return false;       
    }

    //Essa função valida as posições do heroi considerando o mapa e os personagens
    public boolean validaPosicao(){
        if(super.validaPosicao()){
            Fase fase = Desenho.acessoATelaDoJogo().getFaseAtual();
            Mapa mapa = fase.getMapaFase();
            for(Personagem p : fase.getPersonagens()){
                if(this.pPosicao.igual(p.getpPosicao())){
                    if(!p.isbTransponivel()){
                        voltaAUltimaPosicao();
                        return false;
                    }
                }
            }
            for(Blocos b : mapa.getMapa()){
                if(this.pPosicao.igual(b.getpPosicao())){
                    if(!b.isbTransponivel()){
                        voltaAUltimaPosicao();
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
        
    }

    public int getNumeroBombas() {
        return numeroBombas;
    }

    public void setNumeroBombas(int numeroBombas) {
        this.numeroBombas = numeroBombas;
    }

    public int getTamanhoBomba() {
        return tamanhoBomba;
    }

    public void setTamanhoBomba(int tamanhoBomba) {
        this.tamanhoBomba = tamanhoBomba;
    }
    
    public void colocaBomba(){
        if(numeroBombas > 0){
            Bomba b = new Bomba("bombaNormal.png", this.pPosicao.getLinha(), this.pPosicao.getColuna(), this);
            Desenho.acessoATelaDoJogo().adicionaModelo(b);
            numeroBombas--;
        }
    }
    /*
    public boolean moveUp() {
        this.pPosicao.velocidadeY = -Consts.HERO_SPEED_PIXELS;
        //this.pPosicao.velocidadeX = 0; 
        return true; 
    }

    public boolean moveDown() {
        this.pPosicao.velocidadeY = Consts.HERO_SPEED_PIXELS;
        //this.pPosicao.velocidadeX = 0;
        return true;
    }

    public boolean moveRight() {
        this.pPosicao.velocidadeX = Consts.HERO_SPEED_PIXELS;
        //this.pPosicao.velocidadeY = 0;
        return true;
    }

    public boolean moveLeft() {
        this.pPosicao.velocidadeX = -Consts.HERO_SPEED_PIXELS;
        //this.pPosicao.velocidadeY = 0;
        return true;
    }
    */
    
    
    
}
