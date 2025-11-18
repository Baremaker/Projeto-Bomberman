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
public class Fase_3 extends Fase implements Serializable{
    public Fase_3() {
        this.numeroDaFase = 3;
    }
    
    public boolean constroiFase(){
    this.powerups.clear();
        
    mapaFase = new Mapa("0000300000300"
    /*linha1*/         +"0101313101010"
    /*linha2*/         +"2000300000300"   
    /*linha3*/         +"2101010101010"
    /*linha4*/         +"3000020002000"
    /*linha5*/         +"0131010101010"
    /*linha6*/         +"0000000000000"
    /*linha7*/         +"0101010101210"
    /*linha8*/         +"0000300000000"
    /*linha9*/         +"0101010131010"
    /*linha10*/        +"0000000000000");
        
        hero = new Hero("heroDeFrente.png", 0, 7);
        this.addPersonagem(hero);
        //this.atualizaCamera();
        
        ZigueZague zz = new ZigueZague("skoot.png", 5, 6);
        this.addPersonagem(zz);

        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("Dir_inimigoTipo1.png", 0, 4);
        this.addPersonagem(bBichinhoH);

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("Dir_inimigoTipo1.png", 4,4);
        this.addPersonagem(bBichinhoH2);

        BichinhoVaiVemVertical bVv = new BichinhoVaiVemVertical("Esq_inimigoTipo2.png", 10,10);
        this.addPersonagem(bVv);

        Caveira bV = new Caveira("Frente_inimigoTipo2.png", 9, 0, "Vertical", "Direita", 20);
        this.addPersonagem(bV);

        Chaser chase = new Chaser("inimigoTipo3Frente.png", 9, 12);
        this.addPersonagem(chase);

        Esfera es = new Esfera("inimigoTipo3Dead.png", 10, 12);
        this.addPersonagem(es);   
        return true;
    }
}
