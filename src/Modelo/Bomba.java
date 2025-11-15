/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.ControleDeJogo;
import Controler.Tela;
import Auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author aserr
 */
public class Bomba extends Personagem implements Serializable {

    private int contador = 0;
    private int tempoDetonacao = 10;
    private Hero bomberman;
    private int tamanhoBomba;
    private char codigo;
    
    public Bomba(String sNomeImagePNG, int linha, int coluna, Hero bomberman){
        super(sNomeImagePNG, linha, coluna);
        this.bTransponivel = true;
        this.bMortal = false;
        this.bomberman = bomberman;
        this.tamanhoBomba = bomberman.getTamanhoBomba();
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(contador == tempoDetonacao){
            estouraBomba();
        }
        contador++;
    }

    public void estouraBomba(){
        bomberman.setNumeroBombas(bomberman.getNumeroBombas() + 1);
        criaExplosoes();
        Desenho.acessoATelaDoJogo().removePersonagem(this);
    }

    private boolean validaPosicao(int linhaOffset, int colunaOffset){
        if ((pPosicao.getLinha()+linhaOffset) < 0 || (pPosicao.getLinha()+linhaOffset) >= Auxiliar.Consts.MUNDO_ALTURA)return false;
        if ((pPosicao.getColuna()+colunaOffset) < 0 || (pPosicao.getColuna()+colunaOffset) >= Auxiliar.Consts.MUNDO_LARGURA)return false;
        if((143 > (pPosicao.getLinha() + linhaOffset)*13 + pPosicao.getColuna() + colunaOffset && (pPosicao.getLinha() + linhaOffset)*13 + pPosicao.getColuna() + colunaOffset >= 0)){
        if(Desenho.acessoATelaDoJogo().getFaseAtual().getMapaFase().getMapa().get((pPosicao.getLinha() + linhaOffset)*13 + pPosicao.getColuna() + colunaOffset) instanceof BlocoMetal){
            return false;
            }
        }
        return true;
    }

    public void criaExplosoes(){
        int danoBomba = this.vida;
        boolean flagCima=true;
        boolean flagBaixo=true;
        boolean flagEsquerda=true;
        boolean flagDireita=true;
        
        Explosao meio = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna(), danoBomba);
        //Desenho.acessoATelaDoJogo().addPersonagem(meio);
        Desenho.acessoATelaDoJogo().adicionaModelo(meio);
        for(int i=0; i<tamanhoBomba; i++){
            //Pra cima
            if(flagCima){
   
                if(validaPosicao(-1-i, 0)){
                    System.out.println("valido acima");
                    Explosao cima = new Explosao("fire.png", pPosicao.getLinha()-1-i, pPosicao.getColuna(),danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(cima);
                }
                else flagCima = false;
            }
            //Pra direita
            if(flagDireita){
                if(validaPosicao(0, 1+i)){
                    Explosao direita = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna()+1+i,danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(direita);
                }
                else flagDireita = false;
            }
            //Pra baixo
            if(flagBaixo){
                if(validaPosicao(1+i, 0)){
                    Explosao baixo = new Explosao("fire.png", pPosicao.getLinha()+1+i, pPosicao.getColuna(),danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(baixo);
                }
                else flagBaixo = false;
            }
            //Pra esquerda
            if(flagEsquerda){
                if(validaPosicao(0, -1-i)){
                    Explosao esquerda = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna()-1-i,danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(esquerda);
                }
                else flagEsquerda = false;
            }
        }
        
    }
}

