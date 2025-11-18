package Modelo;

import Auxiliar.Desenho;
import java.io.Serializable;

/**
 *
 * @author Jose F Rodrigues-Jr
 */
public class Esfera extends Personagem implements Serializable{
    public Esfera(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        this.bMortal = false;
        this.bTransponivel = false;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }    
}
