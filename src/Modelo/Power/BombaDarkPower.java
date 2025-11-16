/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Power;

import Modelo.BombaDark;
import Modelo.Hero;
import Modelo.TipoBomba;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class BombaDarkPower extends Powerup implements Serializable{
        private TipoBomba tipoAnterior;
    public BombaDarkPower(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
    }
    @Override
    public void aplicarEfeito(Hero h) {
        // Salva o tipo atual
        this.tipoAnterior = h.getTipoBomba(); 
        
        // Aplica o novo tipo de bomba
        h.setTipoBomba(new BombaDark());
        System.out.println("Powerup BombaDark coletado! Nova bomba ativa.");
    }
    
    @Override
    public void reverterEfeito(Hero h) {
        // Se precisar reverter (ex: ao morrer ou expirar)
        h.setTipoBomba(this.tipoAnterior);
    }
}

