/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Auxiliar;

import Auxiliar.Mapa;
import Modelo.Blocos;
import Modelo.BlocoMetal;
import java.util.ArrayList;
import Modelo.Model;
import Modelo.Personagem;
import Modelo.Caveira;
import Modelo.Hero;
import Modelo.Chaser;
import Modelo.BichinhoVaiVemHorizontal;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.ControleDeJogo;
import Modelo.BichinhoVaiVemVertical;
import Modelo.Esfera;
import Modelo.ZigueZague;
import Auxiliar.Posicao;
import Modelo.Power.Powerup;
import java.io.Serializable;
 
public class Fase implements Serializable{
   
    private ArrayList<Personagem> fase;
    private ArrayList<Powerup> powerups;
    private Hero hero;
    private Mapa mapaFase;
    private boolean isEletricidadeAtiva = false;
    //private ArrayList<Powerup> powerup;
    public Fase() {
        this.fase = new ArrayList<>();
        this.powerups = new ArrayList<>();
    }

    public ArrayList<Powerup> getPowerups() {
        return powerups;
    }
    
    public ArrayList<Personagem> getPersonagens() {
        return fase;
    }
       
    public void addPersonagem(Personagem umPersonagem) {
        fase.add(umPersonagem);
    }

    public void removerPersonagem(Personagem umPersonagem) {
        fase.remove(umPersonagem);
    }
   
    public void addPowerUp(Powerup power) {
        powerups.add(power);
    }

    public void removerPowerUp(Powerup power) {
        powerups.remove(power);
    }

    public boolean isIsEletricidadeAtiva() {
        
        return isEletricidadeAtiva;
    }

    public void setIsEletricidadeAtiva(boolean isEletricidadeAtiva) {
        
        this.isEletricidadeAtiva = isEletricidadeAtiva;
        
    }
    
    
    
    public Hero getHero() {
        return this.hero;
    }

    /**
     *
     * @return
     */
    public Mapa getMapaFase() {
        return mapaFase;
    }
    
    
    
    public void fase1(){
         mapaFase = new Mapa("0000000000001"
                            +"0000000000000"
                            +"0000030300002"   
                            +"0101010101010"
                            +"0101010101010"
                            +"0101010101010"
                            +"0101010101010"
                            +"0101010101010"
                            +"0101010101010"
                            +"0101010101010"
                            +"0101010101010");
        
        hero = new Hero("heroDireita.png", 0, 7);
        this.addPersonagem(hero);
        //this.atualizaCamera();
        
        ZigueZague zz = new ZigueZague("skoot.png", 5, 6);
        this.addPersonagem(zz);

        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("inimigoTipo1.png", 1, 4);
        this.addPersonagem(bBichinhoH);

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("inimigoTipo1.png", 6,6);
        this.addPersonagem(bBichinhoH2);

        BichinhoVaiVemVertical bVv = new BichinhoVaiVemVertical("Frente_inimigoTipo1.png", 10,10);
        this.addPersonagem(bVv);

        Caveira bV = new Caveira("inimigoTipo2.png", 0, 0, "Vertical", "Direita", 20);
        this.addPersonagem(bV);

        Chaser chase = new Chaser("inimigoTipo3Frente.png", 9, 12);
        this.addPersonagem(chase);
        
    }

    public String getNumeroDaFase() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
    
}
