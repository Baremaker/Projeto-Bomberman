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
public class Fase_4 extends Fase implements Serializable{
    public Fase_4() {
        this.numeroDaFase = 4;
    }
    public boolean constroiFase(){
    this.powerups.clear();
        //        coluna:0123456789...
    mapaFase = new Mapa("0022000003330"
    /*linha1*/         +"0101010101010"
    /*linha2*/         +"2000333000303"   
    /*linha3*/         +"2101310101010"
    /*linha4*/         +"3011033002000"
    /*linha5*/         +"0131013101010"
    /*linha6*/         +"0003330000000"
    /*linha7*/         +"0101010101010"
    /*linha8*/         +"1110300033030"
    /*linha9*/         +"0101010131010"
    /*linha10*/        +"3030303030300", this.numeroDaFase);
        
        hero = new Hero("hero", 0, 7);
        this.addPersonagem(hero);

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("inimigoTipo1.png", 6,8);
        this.addPersonagem(bBichinhoH2);

        BichinhoVaiVemVertical bVv1 = new BichinhoVaiVemVertical("Frente_inimigoTipo1.png", 9,10);
        this.addPersonagem(bVv1);

        BichinhoVaiVemVertical bVv2 = new BichinhoVaiVemVertical("Frente_inimigoTipo1.png", 7,12);
        this.addPersonagem(bVv2);

        Chaser chase = new Chaser("inimigoTipo3.png", 6, 0);
        this.addPersonagem(chase);
   
        return true;
    }
}
