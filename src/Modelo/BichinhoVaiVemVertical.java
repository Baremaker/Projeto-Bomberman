
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
        this.vida = 6;
    }

    public void autoDesenho(){
        if(!paralisia()){
        if(contadorDeFrames == 5){
            contadorDeFrames = 0;
            if(bUp){
                if(!this.moveUp())
                    bUp = false;
            }else if(!this.moveDown())
                    bUp = true;            
        }
        contadorDeFrames++;
        }
        super.autoDesenho();
    }  
}
