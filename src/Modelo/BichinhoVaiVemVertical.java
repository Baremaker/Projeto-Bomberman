
package Modelo;

import Auxiliar.Desenho;
import java.util.Random;

public class BichinhoVaiVemVertical extends Personagem{
    boolean bUp;
    int contadorDeFrames;
    public BichinhoVaiVemVertical(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        contadorDeFrames = 0;
        this.bTransponivel = true;        
        this.bMortal = true;
        bUp = true;        
    }

    public void autoDesenho(){
        if(contadorDeFrames == 5){
            contadorDeFrames = 0;
            if(bUp)
                this.moveUp();
            else
                this.moveDown();
            bUp = !bUp;            
        }
        contadorDeFrames++;
        super.autoDesenho();
    }  
}
