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

/**
 *
 * @author aserr
 */
public class Fase_2 extends Fase implements Serializable {
    public Fase_2() {
        this.numeroDaFase = 2;
    }
    
    public boolean constroiFase(){
    this.powerups.clear();
        
    mapaFase = new Mapa("2220000000303"
    /*linha1*/         +"0101010101010"
    /*linha2*/         +"2000023200303"   
    /*linha3*/         +"2101010101010"
    /*linha4*/         +"3022020002000"
    /*linha5*/         +"0131210101010"
    /*linha6*/         +"0003003320000"
    /*linha7*/         +"0101010101010"
    /*linha8*/         +"0000300330030"
    /*linha9*/         +"0101010131010"
    /*linha10*/        +"0002220000020", this.numeroDaFase);
        
        hero = new Hero("hero", 0, 7);
        this.addPersonagem(hero);

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("inimigoTipo1.png", 2,2);
        this.addPersonagem(bBichinhoH2);

        BichinhoVaiVemVertical bVv = new BichinhoVaiVemVertical("Frente_inimigoTipo1.png", 10,10);
        this.addPersonagem(bVv);

        Caveira bV = new Caveira("inimigoTipo2.png", 9, 0, "Vertical", "Direita", 20);
        this.addPersonagem(bV);

        Chaser chase = new Chaser("inimigoTipo3.png", 10, 1);
        this.addPersonagem(chase);
   
        return true;
    }
    
    
}
