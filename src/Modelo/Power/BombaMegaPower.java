/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Power;

import Modelo.BombaExplosao.BombaMega;
import Modelo.BombaExplosao.TipoBomba;
import Modelo.Hero;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class BombaMegaPower extends Powerup implements Serializable{
    private TipoBomba tipoAnterior;
    public BombaMegaPower(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
    }
    
    @Override
    public void aplicarEfeito(Hero h) {
        this.tipoAnterior = h.getTipoBomba(); 
        h.setTipoBomba(new BombaMega());
        // Define o tamanho da bomba no Hero para 4
        //h.setTamanhoBomba(4); 
        System.out.println("Powerup BombaMega coletado! Raio de explosão: 4.");
    }
    
    @Override
    public void reverterEfeito(Hero h) {
        h.setTipoBomba(this.tipoAnterior);
        // Retorna o tamanho da bomba ao padrão (assumindo 1 como default)
        
    }
}
