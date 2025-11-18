package Modelo;

import Auxiliar.Desenho;
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
    
}
