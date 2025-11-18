package Modelo;

import Auxiliar.Desenho;
import Auxiliar.Posicao;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Fogo extends Personagem implements Serializable{
    String projDirecao;
    public Fogo(String sNomeImagePNG, int linha, int coluna, String projDirecao) {
        super(sNomeImagePNG, linha, coluna);
        this.bMortal = false;
        this.bTransponivel = true;
        this.projDirecao = projDirecao;
        this.dano = 3;
    }

    @Override
    public void autoDesenho() {
        boolean remover = false;
        switch(projDirecao){
            case "Direita": 
                remover = !this.moveRight();
                break;
            case "Esquerda": 
                remover = !this.moveLeft();
                break;
            case "Cima": 
                remover = !this.moveUp();
                break;
            case "Baixo": 
                remover = !this.moveDown();
                break;
        }
        if(remover)
            Desenho.acessoATelaDoJogo().removePersonagem(this);
        super.autoDesenho();
    }
    public boolean ehPosicaoValida(Posicao p){
        if(p.getLinha() < 0 || p.getLinha() >= Auxiliar.Consts.MUNDO_ALTURA){
            return false;
        }
        if(p.getColuna() < 0 || p.getColuna() >= Auxiliar.Consts.MUNDO_LARGURA){
            return false;
        }
        return true;
    }
    public boolean ehInimigo(){
        return true;
    }
}
