package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;
import Auxiliar.Posicao;

public class Caveira extends Personagem implements Serializable{
    private int intervaloMove;
    private int intervaloTiro;
    private String movDirecao;
    private String projDirecao;
    private int timer;
    private boolean bRight;
    private boolean bUp;
    private String nomeImagem;
    
    public Caveira(String sNomeImagePNG, int linha, int coluna, String movDirecao, String projDirecao, int timer) {
        super(processaNomeImagem(sNomeImagePNG,projDirecao), linha, coluna);
        this.bTransponivel = false;
        this.bMortal = true;
        this.intervaloMove = 0;
        this.intervaloTiro = 0;
        this.movDirecao = movDirecao;
        this.projDirecao = projDirecao;
        this.timer = timer;
        this.bRight = true;
        this.bUp = true;
        this.nomeImagem = sNomeImagePNG;
    }
    
    private static String processaNomeImagem(String nomeBase, String projDirecao){
        if(projDirecao.equals("Esquerda"))return "Esq_" + nomeBase;             
       else return "Dir_" + nomeBase;
    }
    
    public void autoDesenho() {
        super.autoDesenho();
        if(!paralisia()){
            this.intervaloMove++;
            this.intervaloTiro++;
            if(this.intervaloMove == 5){
                this.intervaloMove = 0;
                if(this.movDirecao.equals("Horizontal"))
                    if(bRight){
                        if(!this.moveRight()){
                            bRight = false;
                            this.setiImage("Esq_"+this.nomeImagem);
                        }
                    }else {if(!this.moveLeft()){
                        bRight = true;
                        this.setiImage("Dir_"+this.nomeImagem);
                    }}
                if(this.movDirecao.equals("Vertical"))
                    if(bUp){
                        if(!this.moveUp())
                            bUp = false;
                    }else if(!this.moveDown())
                            bUp = true;
            }
            if(this.intervaloTiro == timer){
                this.intervaloTiro = 0;
                Fogo f = new Fogo("fire.png", pPosicao.getLinha(), pPosicao.getColuna(), projDirecao);
                Desenho.acessoATelaDoJogo().adicionaModelo(f);

                this.setiImage("Dead_"+this.nomeImagem);
            }else if (this.intervaloTiro == 5) { 
                 if(this.movDirecao.equals("Horizontal")){
                     if(bRight){
                        this.setiImage("Dir_"+this.nomeImagem);
                     } else {
                        this.setiImage("Esq_"+this.nomeImagem);
                     }
                 } else {
                     if(this.projDirecao.equals("Esquerda")){
                        this.setiImage("Esq_"+this.nomeImagem);
                     } else {
                        this.setiImage("Dir_"+this.nomeImagem);
                     }
                 }
            }
        }
    }
    public boolean ehInimigo(){
        return true;
    }
}
