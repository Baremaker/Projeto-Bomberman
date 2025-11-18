/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;

import Modelo.BombaExplosao.Explosao;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Fase;
import Modelo.BlocoVazio;
import Modelo.Blocos;
import Modelo.Personagem;
import Modelo.Power.MaisVida;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author aserr
 */
public class ExplosaoDark extends Explosao implements Serializable {

    public ExplosaoDark(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
    }
    

@Override
    public boolean validaPosicao(){
        // Esta explosão IGNORA a checagem de Blocos/Colisão para poder atravessar paredes.
        // Reutilizamos a lógica de dano de Explosao, mas removemos a checagem de bloqueio.
        
        if ((this.pPosicao.getLinha()) < 0 || (this.pPosicao.getLinha()) >= Auxiliar.Consts.MUNDO_ALTURA) return false;
        if ((this.pPosicao.getColuna()) < 0 || (this.pPosicao.getColuna()) >= Auxiliar.Consts.MUNDO_LARGURA) return false;
        
        Fase fase = Desenho.acessoATelaDoJogo().getFaseAtual();
        Blocos blocoAlvo = fase.getMapaFase().getBlocoNaPosicao(this.pPosicao);
        ArrayList<Personagem> listaPersonagens = fase.getPersonagens();
        
        // --- 1. Aplica Dano aos Personagens ---
        aplicadano(listaPersonagens,fase);
        
        // --- 2. Aplica Dano aos Blocos Destrutíveis (Mas não bloqueia a propagação) ---
        if(blocoAlvo != null && blocoAlvo.isbDestrutivel()){
             blocoAlvo.danifica(); 
             if(blocoAlvo.getVida() <= 0){
                fase.getMapaFase().removerBloco(blocoAlvo);
                BlocoVazio bv;
                switch(fase.getNumeroDaFase()){
                        case 1:
                             bv = new BlocoVazio("background1Grama.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        case 2:
                             bv = new BlocoVazio("background2Circuito.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        case 3:
                             bv = new BlocoVazio("background3Azulejo.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        case 4:
                             bv = new BlocoVazio("background4Ferro.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        case 5:
                             bv = new BlocoVazio("background5Final.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        default:
                            bv = new BlocoVazio("background1Grama.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            
                    }      
                fase.getMapaFase().adicionarBloco(bv);
                
                if (Math.random() < Consts.CHANCE_POWERUP) {
                    MaisVida novoPowerup = new MaisVida("coracao.png",blocoAlvo.getpPosicao().getLinha(),blocoAlvo.getpPosicao().getColuna());
                    fase.addPowerUp(novoPowerup);
                }
             }
        }
        
        // Importante: Retorna TRUE, pois ExplosaoDark IGNORA paredes de metal ('1').
        return true;

    }
}    