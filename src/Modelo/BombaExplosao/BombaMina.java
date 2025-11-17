/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;

import Modelo.BombaExplosao.TipoBomba;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class BombaMina extends TipoBomba implements Serializable{
    private  String IMAGEM_NOME_EXPLOSAO = "explosaoMina1.png"; 
    private  String IMAGEM_NOME_BOMBA = "minaExplosiva.png";

    public String getImagemExplosao() {
        return IMAGEM_NOME_EXPLOSAO;
    }

    @Override
    public String getImagemBomba() {
        return IMAGEM_NOME_BOMBA;
    }

    @Override
    public Explosao criarInstanciaExplosao(int linha, int coluna) {
        return new ExplosaoMina(IMAGEM_NOME_EXPLOSAO,linha,coluna);
    }

    

    






}
