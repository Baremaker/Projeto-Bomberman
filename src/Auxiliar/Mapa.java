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
import auxiliar.Posicao;
import java.io.Serializable;
/**
 *
 * @author aserr
 */
public class Mapa {
   
   private ArrayList<Blocos> mapa;

    public Mapa(String mapeado) {
        mapa = new ArrayList<>();    
        for(int i =0;i<11;i++){
            for(int j =0;j<13;j++){
                if(mapeado.charAt(i*13+j)=='1'){
                    BlocoMetal bm = new BlocoMetal("coracao.png", i, j);
                    mapa.add(bm);
                
                }
                if(mapeado.charAt(i*13+j)=='0'){
                    BlocoVazio bv = new BlocoVazio("background.png", i, j);
                    mapa.add(bv);
                
                }
        
        
            }
        }
    }

    public ArrayList<Blocos> getMapa() {
        return mapa;
    }
    
   
   
   
    
}
