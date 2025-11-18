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
public class Fase_1 extends Fase implements Serializable {
        public Fase_1() {
            this.numeroDaFase = 1;
        }
        
        @Override
        public boolean constroiFase() {
        
        // 1. Resetar ou Inicializar o Mapa
        //this.mapaFase.getMapa().clear();
        //this.getPersonagens().clear();
        this.powerups.clear();
        
        mapaFase = new Mapa("0000000000002"
                           +"0101010101012"
                           +"0030032300002"   
                           +"0101010101010"
                           +"0000000000030"
                           +"0101010101010"
                           +"0000000000030"
                           +"0101010101010"
                           +"0000000000000"
                           +"0101010101010"
                           +"0000000000000");
        
        hero = new Hero("heroDeFrente.png", 0, 7);
        this.addPersonagem(hero);
        //this.atualizaCamera();
        
        ZigueZague zz = new ZigueZague("skoot.png", 5, 6);
        this.addPersonagem(zz);

        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("Dir_inimigoTipo1.png", 3, 4);
        this.addPersonagem(bBichinhoH);

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("Dir_inimigoTipo1.png", 6,6);
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
