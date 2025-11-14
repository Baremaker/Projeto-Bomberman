/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Auxiliar;

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
import Modelo.BlocoVazio;
import Modelo.Esfera;
import Modelo.ZigueZague;
import Auxiliar.Posicao;
import java.io.Serializable;
/**
 *
 * @author aserr
 */
public class Mapa {
   
   private ArrayList<Blocos> mapa;
   private GeraBlocoProMapa geradorBlocos;

    public Mapa(String mapeado) {
        mapa = new ArrayList<>();
        geradorBlocos = new GeraBlocoProMapa();
        for(int i=0; i<11; i++){
            for(int j=0; j<13; j++){
                geradorBlocos.geraBloco(this, mapeado, i, j);
            }
        }
    }

    public ArrayList<Blocos> getMapa() {
        return mapa;
    }
    
   
   
   
    
}
