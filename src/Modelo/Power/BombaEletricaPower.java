/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Power;

import Modelo.BombaExplosao.BombaEletrica;
import Modelo.BombaExplosao.TipoBomba;
import Modelo.Hero;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class BombaEletricaPower extends Powerup implements Serializable{
    private TipoBomba tipoAnterior;
    public BombaEletricaPower(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
    }
    
    @Override
    public void aplicarEfeito(Hero h) {
        this.tipoAnterior = h.getTipoBomba(); 
        h.setTipoBomba(new BombaEletrica());
        System.out.println("Powerup BombaEletrica coletado! Nova bomba ativa.");
    }
    
    @Override
    public void reverterEfeito(Hero h) {
        h.setTipoBomba(this.tipoAnterior);
    }
}
