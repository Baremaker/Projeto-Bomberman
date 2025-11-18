/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;

import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class BombaMega extends TipoBomba implements Serializable {
    private  String IMAGEM_NOME_EXPLOSAO = "explosaoTipo4.png"; 
    private  String IMAGEM_NOME_BOMBA = "megaBomba.png";
    private String IMAGEM_INTERMEDIARIA_BOMBA = "megaBombaVermelha.png";
    public Explosao criarInstanciaExplosao(int linha, int coluna) {
        return new ExplosaoMega(IMAGEM_NOME_EXPLOSAO,linha, coluna);
    }

    @Override
    public String getImagemExplosao() {
        return IMAGEM_NOME_EXPLOSAO;
    }

    @Override
    public String getImagemBomba() {
        return IMAGEM_NOME_BOMBA;
    }

    public String getIMAGEM_INTERMEDIARIA_BOMBA() {
        return IMAGEM_INTERMEDIARIA_BOMBA;
    }
    
}
