/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;

import Modelo.BombaExplosao.Explosao;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class ExplosaoMina extends Explosao implements Serializable{

    public ExplosaoMina(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
    }
    
    public boolean validaPosicao(){
    
        return super.validaPosicao();
    }
}
