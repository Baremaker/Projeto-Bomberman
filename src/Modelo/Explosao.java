/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.io.Serializable;

import Auxiliar.Desenho;

/**
 *
 * @author aserr
 */
public class Explosao extends Personagem implements Serializable {
    private int contador = 0;
    
    public Explosao(String sNomeImagePNG, int linha, int coluna,int vida){
        super(sNomeImagePNG, linha, coluna);
        this.bMortal = true;
        this.vida=vida;
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(contador == 3){
            Desenho.acessoATelaDoJogo().removePersonagem(this);
        }
        contador++;
    }

   

}
