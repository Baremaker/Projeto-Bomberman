/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.ControleDeJogo;
import Controler.Tela;
import auxiliar.Posicao;
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
    private int tamanhoBomba;
    private Hero bomberman;
    
    public Bomba(String sNomeImagePNG, int linha, int coluna, Hero bomberman,int tipo){
        super(sNomeImagePNG, linha, coluna);
        this.bomberman = bomberman;
        this.tamanhoBomba = bomberman.getTamanhoBomba();
        if(tipo==0){
            this.vida = 3;
        }
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(contador == 10){
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
        
       
        // Desenho.acessoATelaDoJogo().getFaseAtual().getMapaFase().getMapa().get((pPosicao.getLinha()+linhaOffset)*13+pPosicao.getColuna()+colunaOffset) instanceof BlocoVazio )
        return(Desenho.acessoATelaDoJogo().ehPosicaoValida(new Posicao(pPosicao.getLinha() + linhaOffset, pPosicao.getColuna() + colunaOffset)));
        
        //return false;
    }

    public void criaExplosoes(){
        int danoBomba = this.vida;
        int flagcima=1;
        int flagbaixo=1;
        int flagesquerda=1;
        int flagdireita=1;
        Explosao meio = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna(),danoBomba);
        //Desenho.acessoATelaDoJogo().addPersonagem(meio);
        Desenho.acessoATelaDoJogo().adicionaModelo(meio);
        for(int i=0; i<tamanhoBomba; i++){
            //Pra cima
            if(validaPosicao(-1-i, 0)){
                Explosao cima = new Explosao("fire.png", pPosicao.getLinha()-1-i, pPosicao.getColuna(),danoBomba);
                Desenho.acessoATelaDoJogo().adicionaModelo(cima);
            }
            //Pra direita
            if(validaPosicao(0, 1+i)){
                Explosao direita = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna()+1+i,danoBomba);
                Desenho.acessoATelaDoJogo().adicionaModelo(direita);
            }
            //Pra baixo
            if(validaPosicao(1+i, 0)){
                Explosao baixo = new Explosao("fire.png", pPosicao.getLinha()+1+i, pPosicao.getColuna(),danoBomba);
                Desenho.acessoATelaDoJogo().adicionaModelo(baixo);
            }
            //Pra esquerda
            if(validaPosicao(0, -1-i)){
                Explosao esquerda = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna()-1-i,danoBomba);
                Desenho.acessoATelaDoJogo().adicionaModelo(esquerda);
            }
        }
    }
}

