package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.BombaExplosao.RaioEletrico;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BichinhoVaiVemHorizontal extends Personagem implements Serializable {

    private boolean bRight;
    int iContador;

    public BichinhoVaiVemHorizontal(String sNomeImagePNG, int linha, int coluna) {
        super("Dir_" + sNomeImagePNG, linha, coluna);
        //setiImage(sNomeImagePNG);
        bRight = true;
        iContador = 0;
        this.bTransponivel = true;
        this.bMortal = true;  
        this.vida = 6;
        this.nomeImagem = sNomeImagePNG;
        this.dano = 3;
    }

    public void autoDesenho() {
        //RaioEletrico raio = new RaioEletrico("raioNoInimigo.png",this.getpPosicao().getLinha(),this.getpPosicao().getColuna());
        if(!paralisia()){
            
            if (iContador == 5) {
                iContador = 0;
                if(bRight){
                    if(!this.moveRight()){
                        bRight = false;
                        this.setiImage("Esq_"+this.nomeImagem);
                    }    
                } else { 
                    if(!this.moveLeft()){ 
                        bRight = true;
                        this.setiImage("Dir_"+this.nomeImagem);
                    }
                }
            }
        iContador++;
        }else{
            
            //Desenho.acessoATelaDoJogo().adicionaModelo(raio);
            
            
        }
        
        super.autoDesenho();
    }
    public boolean ehInimigo(){
        return true;
    }
}   