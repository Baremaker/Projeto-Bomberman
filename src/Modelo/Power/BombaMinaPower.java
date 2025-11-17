/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Power;

import Modelo.BombaExplosao.BombaMina;
import Modelo.Hero;
import Modelo.BombaExplosao.TipoBomba;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class BombaMinaPower extends Powerup implements Serializable{
    private TipoBomba tipoAnterior;
    
    public BombaMinaPower(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
    }
    
    @Override
    public void aplicarEfeito(Hero h) {
        this.tipoAnterior = h.getTipoBomba(); 
        h.setTipoBomba(new BombaMina());
        System.out.println("Powerup BombaMina coletado! Nova bomba ativa.");
    }
    
    @Override
    public void reverterEfeito(Hero h) {
        h.setTipoBomba(this.tipoAnterior);
    }
}
