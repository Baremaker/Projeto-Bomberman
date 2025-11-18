package Auxiliar;

import Modelo.BlocoForte;
import Modelo.BlocoMetal;
import Modelo.BlocoNormal;
import Modelo.BlocoVazio;
import java.io.Serializable;

public class GeraBlocoProMapa implements Serializable{
    public void geraBloco(Mapa mapa, String mapeado, int i, int j){
        switch (mapeado.charAt(13*i + j)) {
            case '0':
                BlocoVazio bv = new BlocoVazio("background"+"1Grama"+".png", i, j);
                mapa.getMapa().add(bv);
                break;
            
            case '1':
                BlocoMetal bm = new BlocoMetal("paredeMetal1.png", i, j);
                mapa.getMapa().add(bm);
                break;

            case '2':
                BlocoNormal bn = new BlocoNormal("parede.png", i, j);
                mapa.getMapa().add(bn);
                break;
            case '3':
                BlocoForte bf = new BlocoForte("paredeForte.png", i, j);
                mapa.getMapa().add(bf);
                break;
            
            
            default:
                break;
        }
    }
    
    
    
    
}