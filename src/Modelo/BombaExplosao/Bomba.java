/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;

import Modelo.BombaExplosao.TipoBomba;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Fase;
import Controler.ControleDeJogo;
import Controler.Tela;
import Auxiliar.Posicao;
import Modelo.Hero;
import Modelo.Personagem;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author aserr
 */
public class Bomba extends Personagem implements Serializable {

    private int contador = 0;
    private int tempoDetonacao = 10;
    private Hero bomberman;
    private char codigo;
    //private String tipoExplosao;
    private int tamanhoBomba;
    private TipoBomba tipoEstrategia; 
    
    public Bomba(String sNomeImagePNG, int linha, int coluna, Hero bomberman,TipoBomba tipo){
        //super(decodificaTipo(tipoBomba), linha, coluna);
        super(sNomeImagePNG, linha, coluna);
        /*switch(tipoBomba){
                case "Basica": 
                    sNomeImagePNG = "bombaNormal.png";
                    this.tamanhoBomba = 3;
                    break;
                default: 
                    sNomeImagePNG = "bombaNormal.png";
                    tamanhoBomba = 1;
                    
            }*/
        setiImage(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMortal = false;
        this.bomberman = bomberman;
        //this.tipoExplosao = tipoBomba;
        this.tipoEstrategia = tipo;
        
        this.tamanhoBomba=1;
                
    }
    
    /*private static String decodificaTipo(String tipoBomba){
        switch(tipoBomba){
                case "Basica": 
                    return "bombaNormal.png";
                default:
                    return "bombaNormal.png";
        }
    }*/

    public void setiImage(String sNomeImagePNG) {
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH +Consts.PATHBOMBA+ sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(!(tipoEstrategia instanceof BombaMina)){
            if(contador == tempoDetonacao){
                estouraBomba();
            }
        }
        contador++;
    }

    public void estouraBomba(){
        if (tipoEstrategia instanceof BombaEletrica) {//para de paralisar personagens
            Fase fase = Desenho.acessoATelaDoJogo().getFaseAtual();
            // Desativa o estado de paralisia na fase
            fase.setIsEletricidadeAtiva(false);
            if(!fase.isIsEletricidadeAtiva())System.out.println("sem eletrico:");
        }
        bomberman.setNumeroBombas(bomberman.getNumeroBombas() + 1);
        criaExplosoes();
        Desenho.acessoATelaDoJogo().removePersonagem(this);
    }

    public void criaExplosoes(){
        this.tipoEstrategia.criaExplosoes(bomberman, pPosicao, tamanhoBomba); 
    }
    /*public void forcarExplosao() {
        // Garante que só explode se for uma BombaMina
        if (tipoEstrategia instanceof BombaMina) {
            estouraBomba();
        }
    }*/
    
}

