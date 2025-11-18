package Auxiliar;

import Modelo.BlocoForte;
import Modelo.BlocoMetal;
import Modelo.BlocoNormal;
import Modelo.BlocoVazio;
import java.io.Serializable;

public class GeraBlocoProMapa implements Serializable{
    public void geraBloco(Mapa mapa, String mapeado, int i, int j, int numeroDaFase){
        if(numeroDaFase == 1) {
            switch (mapeado.charAt(13*i + j)) {
                case '0':
                     BlocoVazio bv = new BlocoVazio("background"+"1Grama"+".png", i, j);
                     mapa.getMapa().add(bv);
                     break;
            
                case '1':
                     BlocoMetal bm = new BlocoMetal("background1Casinha.png", i, j);
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
        if(numeroDaFase == 2) {
            switch (mapeado.charAt(13*i + j)) {
                case '0':
                     BlocoVazio bv = new BlocoVazio("background"+"2Circuito"+".png", i, j);
                     mapa.getMapa().add(bv);
                     break;
            
                case '1':
                     BlocoMetal bm = new BlocoMetal("background2CPU.png", i, j);
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
        if(numeroDaFase == 3) {
            switch (mapeado.charAt(13*i + j)) {
                case '0':
                     BlocoVazio bv = new BlocoVazio("background"+"3Azulejo"+".png", i, j);
                     mapa.getMapa().add(bv);
                     break;
            
                case '1':
                     BlocoMetal bm = new BlocoMetal("background3Pilar.png", i, j);
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
        if(numeroDaFase == 4) {
            switch (mapeado.charAt(13*i + j)) {
                case '0':
                     BlocoVazio bv = new BlocoVazio("background"+"4Ferro"+".png", i, j);
                     mapa.getMapa().add(bv);
                     break;
            
                case '1':
                     BlocoMetal bm = new BlocoMetal("background4Reator.png", i, j);
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
        if(numeroDaFase == 5) {
            switch (mapeado.charAt(13*i + j)) {
                case '0':
                     BlocoVazio bv = new BlocoVazio("background"+"5Final"+".png", i, j);
                     mapa.getMapa().add(bv);
                     break;
            
                case '1':
                     BlocoMetal bm = new BlocoMetal("background5Caldeirao.png", i, j);
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
    
    
    
    
}