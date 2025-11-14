/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import auxiliar.Posicao;
import java.io.Serializable;

/**
 *
 * @author 2373891
 */
public class Chaser extends Personagem implements Serializable {

    private boolean iDirectionV;
    private boolean iDirectionH;
    private int counter;

    public Chaser(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        iDirectionV = true;
        iDirectionH = true;
        this.bTransponivel = true;
        counter = 0;
    }

    public void computeDirection(Posicao heroPos) {
        if (heroPos.getColuna() < this.getPosicao().getColuna()) {
            iDirectionH = true;
        } else if (heroPos.getColuna() > this.getPosicao().getColuna()) {
            iDirectionH = false;
        }
        if (heroPos.getLinha() < this.getPosicao().getLinha()) {
            iDirectionV = true;
        } else if (heroPos.getLinha() > this.getPosicao().getLinha()) {
            iDirectionV = false;
        }
    }

    public void autoDesenho() {
        if (counter == 5) {
            if (iDirectionH) {
                this.moveLeft();
            } else {
                this.moveRight();
            }
            if (iDirectionV) {
                this.moveUp();
            } else {
                this.moveDown();
            }
            counter = 0;
        }
        counter++;
        super.autoDesenho();
    }

}
