/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;

import Modelo.BombaExplosao.TipoBomba;
import Auxiliar.Desenho;
import Auxiliar.Posicao;
import Modelo.Hero;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class BombaDark extends TipoBomba implements Serializable {
    private String IMAGEM_NOME_EXPLOSAO = "explosãoDarkness.png";
    private String IMAGEM_NOME_BOMBA = "bombaDarknessTrevoso.png";
    
    @Override
    
    
    
    public Explosao criarInstanciaExplosao(int linha, int coluna) {
        return new ExplosaoDark(IMAGEM_NOME_EXPLOSAO, linha, coluna);
    }
    public String getImagemExplosao() {
        
        return IMAGEM_NOME_EXPLOSAO;
    }

    public String getImagemBomba() {
        return IMAGEM_NOME_BOMBA;
    }
    void criaExplosoes(Hero bomberman, Posicao pPosicao, int tamanhoBomba){
        super.criaExplosoes(bomberman, pPosicao, tamanhoBomba);
        boolean flagSupDireito=true;
        boolean flagSupEsquerdo=true;
        boolean flagInfDireito=true;
        boolean flagInfesquerdo=true;
        
        for(int i=0; i<tamanhoBomba; i++){
            //Pra cima
            
            if(flagSupDireito){
                
                Explosao supdireito = criarInstanciaExplosao(pPosicao.getLinha()-1-i, pPosicao.getColuna()+1+i);
                if(supdireito.validaPosicao()){
                    
                    Desenho.acessoATelaDoJogo().adicionaModelo(supdireito);
                }
                else flagSupDireito = false;
            }
            //Pra direita
            if(flagSupEsquerdo){
                
                Explosao supesquerdo = criarInstanciaExplosao( pPosicao.getLinha()-1-i, pPosicao.getColuna()-1-i);
            
                if(supesquerdo.validaPosicao()){
                    
                    Desenho.acessoATelaDoJogo().adicionaModelo(supesquerdo);
                }
                else flagSupEsquerdo = false;
            }
            //Pra baixo
            if(flagInfDireito){
                
                Explosao infdireito = criarInstanciaExplosao(pPosicao.getLinha()+1+i, pPosicao.getColuna()+1+i);
                if(infdireito.validaPosicao()){
                    
                    Desenho.acessoATelaDoJogo().adicionaModelo(infdireito);
                }
                else flagInfDireito = false;
            }
            //Pra esquerda
            if(flagInfesquerdo){
                
                Explosao infesquerdo = criarInstanciaExplosao(pPosicao.getLinha()+1+i, pPosicao.getColuna()-1-i);
                if(infesquerdo.validaPosicao()){
                    
                    Desenho.acessoATelaDoJogo().adicionaModelo(infesquerdo);
                }
                else flagInfesquerdo = false;
            }
            
        }
    
    }
}
