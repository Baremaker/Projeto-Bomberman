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
import auxiliar.Posicao;
import java.io.Serializable;
 
public class Fase implements Serializable{
   
    private ArrayList<Model> fase;
    private Hero hero;
    private Mapa mapaFase;
    public Fase() {
        this.fase = new ArrayList<>();
        
    }
    
    public ArrayList<Model> getPersonagens() {
        return fase;
    }
       
    public void addModelo(Model umModelo) {
        fase.add(umModelo);
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
         mapaFase = new Mapa("0000000000000"
                         +"0101010101010"
                         +"0101010101010"   
                         +"0101010101010"
                         +"0101010101010"
                         +"0101010101010"
                         +"0101010101010"
                         +"0101010101010"
                         +"0101010101010"
                         +"0101010101010"
                         +"0101010101010");
        
        hero = new Hero("heroDireita.png", 0, 7);
        this.addModelo(hero);
        //this.atualizaCamera();

        ZigueZague zz = new ZigueZague("bomba.png", 5, 6);
        this.addModelo(zz);

        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("roboPink.png", 3, 4);
        this.addModelo(bBichinhoH);

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("roboPink.png", 6,6);
        this.addModelo(bBichinhoH2);

        BichinhoVaiVemVertical bVv = new BichinhoVaiVemVertical("Caveira.png", 10,10);
        this.addModelo(bVv);

        Caveira bV = new Caveira("caveira.png", 9, 0);
        this.addModelo(bV);

        Chaser chase = new Chaser("chaser.png", 9, 12);
        this.addModelo(chase);

        Esfera es = new Esfera("esfera.png", 10, 12);
        this.addModelo(es);
   
        
    }


    
    
}
