/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Auxiliar;

import Modelo.BichinhoVaiVemHorizontal;
import Modelo.BichinhoVaiVemVertical;
import Modelo.Caveira;
import Modelo.Chaser;
import Modelo.Esfera;
import Modelo.Hero;
import Modelo.ZigueZague;
import java.io.Serializable;

/**
 *
 * @author aserr
 */
public class Fase_5 extends Fase implements Serializable{
    public Fase_5() {
        this.numeroDaFase = 5;
    }
    public boolean constroiFase(){
    this.powerups.clear();
        
    mapaFase = new Mapa("0000000000000"
    /*linha1*/         +"0101010101010"
    /*linha2*/         +"0332332003023"   
    /*linha3*/         +"0101010101010"
    /*linha4*/         +"0333000002220"
    /*linha5*/         +"0131010101010"
    /*linha6*/         +"0333300022220"
    /*linha7*/         +"0101010101010"
    /*linha8*/         +"0323230323230"
    /*linha9*/         +"0101010131010"
    /*linha10*/        +"0333000003333", this.numeroDaFase);
        
        hero = new Hero("hero", 2, 7);
        this.addPersonagem(hero);
        //this.atualizaCamera();
        
        

        

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("inimigoTipo1.png", 4,4);
        this.addPersonagem(bBichinhoH2);

        BichinhoVaiVemVertical bVv = new BichinhoVaiVemVertical("Frente_inimigoTipo1.png", 8,12);
        this.addPersonagem(bVv);
        Caveira bV = new Caveira("inimigoTipo2.png", 0, 0, "Horizontal", "Baixo", 20);
        
        this.addPersonagem(bV);

        Chaser chase1 = new Chaser("inimigoTipo3.png", 0, 0);
        this.addPersonagem(chase1);
        
        Chaser chase2 = new Chaser("inimigoTipo3.png", 10, 6);
        this.addPersonagem(chase2);
        
        Chaser chase3 = new Chaser("inimigoTipo3.png", 5, 12);
        this.addPersonagem(chase3);

        return true;
    }
}
