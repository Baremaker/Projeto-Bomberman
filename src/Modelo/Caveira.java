package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Caveira extends Personagem implements Serializable{
    private int iContaIntervalos;
    
    public Caveira(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        this.bTransponivel = false;
        this.bMortal = false;
        this.iContaIntervalos = 0;
    }

    public void autoDesenho() {
        super.autoDesenho();

        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMER){
            this.iContaIntervalos = 0;
            Fogo f = new Fogo("fire.png",pPosicao.getLinha(),pPosicao.getColuna()+1);
            Desenho.acessoATelaDoJogo().adicionaModelo(f);
        }
    }    
}
