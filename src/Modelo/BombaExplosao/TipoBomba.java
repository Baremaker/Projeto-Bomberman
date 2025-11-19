/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;

import Auxiliar.Desenho;
import Auxiliar.Fase;
import Auxiliar.Posicao;
import Modelo.Hero;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public abstract class TipoBomba implements Serializable {
    private String IMAGEM_NOME_EXPLOSAO;
    private String IMAGEM_NOME_BOMBA;
    private String IMAGEM_INTERMEDIARIA_BOMBA;
    public abstract Explosao criarInstanciaExplosao(int linha, int coluna);
    
    
    void criaExplosoes(Hero bomberman, Posicao pPosicao, int tamanhoBomba){
        boolean flagCima=true;
        boolean flagBaixo=true;
        boolean flagEsquerda=true;
        boolean flagDireita=true;
        
        Explosao meio = criarInstanciaExplosao(pPosicao.getLinha(), pPosicao.getColuna());
       
        meio.validaPosicao();
        Desenho.acessoATelaDoJogo().adicionaModelo(meio);
        for(int i=0; i<tamanhoBomba; i++){
            //Pra cima
            
            if(flagCima){
                
                Explosao cima = criarInstanciaExplosao(pPosicao.getLinha()-1-i, pPosicao.getColuna());
                if(cima.validaPosicao()){
                    
                    
                    Desenho.acessoATelaDoJogo().adicionaModelo(cima);
                }
                else flagCima = false;
            }
            //Pra direita
            if(flagDireita){
                
                Explosao direita = criarInstanciaExplosao( pPosicao.getLinha(), pPosicao.getColuna()+1+i);
            
                if(direita.validaPosicao()){
                    
                    Desenho.acessoATelaDoJogo().adicionaModelo(direita);
                }
                else flagDireita = false;
            }
            //Pra baixo
            if(flagBaixo){
                
                Explosao baixo = criarInstanciaExplosao(pPosicao.getLinha()+1+i, pPosicao.getColuna());
                if(baixo.validaPosicao()){
                    
                    Desenho.acessoATelaDoJogo().adicionaModelo(baixo);
                }
                else flagBaixo = false;
            }
            //Pra esquerda
            if(flagEsquerda){
                
                Explosao esquerda = criarInstanciaExplosao(pPosicao.getLinha(), pPosicao.getColuna()-1-i);
                if(esquerda.validaPosicao()){
                    
                    Desenho.acessoATelaDoJogo().adicionaModelo(esquerda);
                }
                else flagEsquerda = false;
            }
            
        }
    
    
    
    }

    public String getImagemExplosao() {
        
        return IMAGEM_NOME_EXPLOSAO;
    }

    public String getImagemBomba() {
        return IMAGEM_NOME_BOMBA;
    }

   
    public String getIMAGEM_INTERMEDIARIA_BOMBA() {
        return IMAGEM_INTERMEDIARIA_BOMBA;
    }
    
    
}
