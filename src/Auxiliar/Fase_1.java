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
        
        
        this.powerups.clear();
        
        mapaFase = new Mapa("0000000000002"
                           +"0101010101012"
                           +"0030032300002"   
                           +"0101010101010"
                           +"3000300000030"
                           +"0101310101010"
                           +"0220000000030"
                           +"0131010121010"
                           +"0200220220000"
                           +"0101010101010"
                           +"2222000000030", this.numeroDaFase);
        
        hero = new Hero("hero", 0, 7);
        this.addPersonagem(hero);
        //this.atualizaCamera();

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("inimigoTipo1.png", 6,6);
        this.addPersonagem(bBichinhoH2);
        Desenho.acessoATelaDoJogo().getGerenciadorDrops().salvaPersonagemZIP(bBichinhoH2);
        BichinhoVaiVemVertical bVv = new BichinhoVaiVemVertical("Frente_inimigoTipo1.png", 10,10);
        this.addPersonagem(bVv);
        Desenho.acessoATelaDoJogo().getGerenciadorDrops().salvaPersonagemZIP(bVv);

        Caveira bV = new Caveira("inimigoTipo2.png", 9, 0, "Vertical", "Direita", 20);
        this.addPersonagem(bV);
        Desenho.acessoATelaDoJogo().getGerenciadorDrops().salvaPersonagemZIP(bV);

        Chaser chase = new Chaser("inimigoTipo3.png", 9, 12);
        this.addPersonagem(chase);
        Desenho.acessoATelaDoJogo().getGerenciadorDrops().salvaPersonagemZIP(chase);

         
        return true;
    }
}
