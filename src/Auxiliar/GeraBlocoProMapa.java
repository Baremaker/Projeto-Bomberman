package Auxiliar;

import Modelo.BlocoMetal;
import Modelo.BlocoNormal;
import Modelo.BlocoVazio;

public class GeraBlocoProMapa {
    public void geraBloco(Mapa mapa, String mapeado, int i, int j){
        switch (mapeado.charAt(13*i + j)) {
            case '0':
                BlocoVazio bv = new BlocoVazio("background.png", i, j);
                mapa.getMapa().add(bv);
                break;
            
            case '1':
                BlocoMetal bm = new BlocoMetal("coracao.png", i, j);
                mapa.getMapa().add(bm);
                break;

            case '2':
                BlocoNormal bn = new BlocoNormal("blacktile.png", i, j);
                mapa.getMapa().add(bn);
                break;
        
            default:
                break;
        }
    }
}