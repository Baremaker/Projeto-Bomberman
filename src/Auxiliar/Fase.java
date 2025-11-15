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
import Modelo.Powerup;
import java.io.Serializable;
 
public class Fase implements Serializable{
   
    private ArrayList<Personagem> fase;
    private Hero hero;
    private Mapa mapaFase;
    //private ArrayList<Powerup> powerup;
    public Fase() {
        this.fase = new ArrayList<>();
        
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
                            +"0101220101010"
                            +"0101030301010"   
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

        ZigueZague zz = new ZigueZague("bomba.png", 5, 6);
        this.addPersonagem(zz);

        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("roboPink.png", 3, 4);
        this.addPersonagem(bBichinhoH);

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("roboPink.png", 6,6);
        this.addPersonagem(bBichinhoH2);

        BichinhoVaiVemVertical bVv = new BichinhoVaiVemVertical("Caveira.png", 10,10);
        this.addPersonagem(bVv);

        Caveira bV = new Caveira("caveira.png", 9, 0);
        this.addPersonagem(bV);

        Chaser chase = new Chaser("chaser.png", 9, 12);
        this.addPersonagem(chase);

        Esfera es = new Esfera("esfera.png", 10, 12);
        this.addPersonagem(es);     
    }


    
    
}
