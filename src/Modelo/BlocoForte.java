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
public class BlocoForte extends Blocos implements Serializable {
    
    public BlocoForte(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        this.bTransponivel = false;
        this.codigo = '3';
        this.vida = 2;
        this.bDestrutivel = true;
    
    
    }
    
}
