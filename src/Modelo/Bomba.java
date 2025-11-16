/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.ControleDeJogo;
import Controler.Tela;
import Auxiliar.Posicao;
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
    private String tipoExplosao;
    private int tamanhoBomba;
    
    
    public Bomba(String tipoBomba, int linha, int coluna, Hero bomberman){
        super(decodificaTipo(tipoBomba), linha, coluna);
        String sNomeImagePNG;
        switch(tipoBomba){
                case "Basica": 
                    sNomeImagePNG = "bombaNormal.png";
                    this.tamanhoBomba = 3;
                    break;
                default: 
                    sNomeImagePNG = "bombaNormal.png";
                    tamanhoBomba = 1;
                    
            }
        setiImage(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMortal = false;
        this.bomberman = bomberman;
        this.tipoExplosao = tipoBomba;
    }
    
    private static String decodificaTipo(String tipoBomba){
        switch(tipoBomba){
                case "Basica": 
                    return "bombaNormal.png";
                default:
                    return "bombaNormal.png";
        }
    }

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
        if(contador == tempoDetonacao){
            estouraBomba();
        }
        contador++;
    }

    public void estouraBomba(){
        bomberman.setNumeroBombas(bomberman.getNumeroBombas() + 1);
        criaExplosoes();
        Desenho.acessoATelaDoJogo().removePersonagem(this);
    }

    public void criaExplosoes(){
        boolean flagCima=true;
        boolean flagBaixo=true;
        boolean flagEsquerda=true;
        boolean flagDireita=true;
        
        Explosao meio = new Explosao(tipoExplosao, pPosicao.getLinha(), pPosicao.getColuna());
        //Desenho.acessoATelaDoJogo().addPersonagem(meio);
        meio.validaPosicao();
        Desenho.acessoATelaDoJogo().adicionaModelo(meio);
        for(int i=0; i<tamanhoBomba; i++){
            //Pra cima
            if(flagCima){
                Explosao cima = new Explosao(tipoExplosao, pPosicao.getLinha()-1-i, pPosicao.getColuna());
                if(cima.validaPosicao()){
                    //System.out.println("valido acima");
                    //Explosao cima = new Explosao("fire.png", pPosicao.getLinha()-1-i, pPosicao.getColuna(),danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(cima);
                }
                else flagCima = false;
            }
            //Pra direita
            if(flagDireita){
                
                Explosao direita = new Explosao(tipoExplosao, pPosicao.getLinha(), pPosicao.getColuna()+1+i);
            
                if(direita.validaPosicao()){
                    //Explosao direita = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna()+1+i,danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(direita);
                }
                else flagDireita = false;
            }
            //Pra baixo
            if(flagBaixo){
                
                Explosao baixo = new Explosao(tipoExplosao, pPosicao.getLinha()+1+i, pPosicao.getColuna());
                if(baixo.validaPosicao()){
                    //Explosao baixo = new Explosao("fire.png", pPosicao.getLinha()+1+i, pPosicao.getColuna(),danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(baixo);
                }
                else flagBaixo = false;
            }
            //Pra esquerda
            if(flagEsquerda){
                
                Explosao esquerda = new Explosao(tipoExplosao, pPosicao.getLinha(), pPosicao.getColuna()-1-i);
                if(esquerda.validaPosicao()){
                    //Explosao esquerda = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna()-1-i,danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(esquerda);
                }
                else flagEsquerda = false;
            }
        }
        
    }
}

