/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class BlocoVazio extends Blocos implements Serializable {
   
    public BlocoVazio(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        this.bTransponivel = true;
        this.codigo = '0';
        this.vida = 1;
        this.bDestrutivel = false;
    }
   
}
