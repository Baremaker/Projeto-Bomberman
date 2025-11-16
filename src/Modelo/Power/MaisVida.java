/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Power;

import Modelo.Hero;
import Modelo.Model;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class MaisVida extends Powerup implements Serializable{
    
    public MaisVida(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
    }
    public void aplicarEfeito(Hero h) {
        // Nenhuma ação imediata. O efeito é passivo (checado em Hero.levaDano)
    }

  
    
}
