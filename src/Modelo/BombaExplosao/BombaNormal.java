/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;

import Modelo.BombaExplosao.TipoBomba;
import Auxiliar.Posicao;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class BombaNormal extends TipoBomba implements Serializable {
        String IMAGEM_NOME_EXPLOSAO = "explosaoTipo3.png";
        String IMAGEM_NOME_BOMBA = "bombaNormal.png";
   
    
    public String getImagemExplosao() {
        System.out.println("nome:"+IMAGEM_NOME_EXPLOSAO);
        return IMAGEM_NOME_EXPLOSAO;
    }

    public String getImagemBomba() {
        return IMAGEM_NOME_BOMBA;
    }

    @Override
    public Explosao criarInstanciaExplosao(int linha, int coluna) {
        return new Explosao(IMAGEM_NOME_EXPLOSAO, linha, coluna);
    }
}
