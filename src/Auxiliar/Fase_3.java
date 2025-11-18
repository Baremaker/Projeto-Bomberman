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
        
    mapaFase = new Mapa("3000300000323"
    /*linha1*/         +"0101313101010"
    /*linha2*/         +"2000300000300"   
    /*linha3*/         +"2101010101010"
    /*linha4*/         +"3000020002000"
    /*linha5*/         +"0131010101010"
    /*linha6*/         +"2222222222222"
    /*linha7*/         +"0101010101010"
    /*linha8*/         +"0000300000000"
    /*linha9*/         +"0101010131010"
    /*linha10*/        +"0020202020000", this.numeroDaFase);
        
        hero = new Hero("hero", 0, 7);
        this.addPersonagem(hero);
        //this.atualizaCamera();
        
       

       

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("inimigoTipo1.png", 8,8);
        this.addPersonagem(bBichinhoH2);

        BichinhoVaiVemVertical bVv = new BichinhoVaiVemVertical("Esq_inimigoTipo1.png", 5,8);
        this.addPersonagem(bVv);

        Caveira bV = new Caveira("inimigoTipo2.png", 2, 12, "Vertical", "Esquerda", 20);
        this.addPersonagem(bV);

        Chaser chase = new Chaser("inimigoTipo3Frente.png", 9, 12);
        this.addPersonagem(chase);

          
        return true;
    }
}
