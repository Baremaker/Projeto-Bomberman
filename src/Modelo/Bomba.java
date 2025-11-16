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
    private int tamanhoBomba;
    private char codigo;
    
    public Bomba(String sNomeImagePNG, int linha, int coluna, Hero bomberman){
        super(sNomeImagePNG, linha, coluna);
        setiImage(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMortal = false;
        this.bomberman = bomberman;
        this.tamanhoBomba = bomberman.getTamanhoBomba();
        
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

    private boolean validaPosicao(int linhaOffset, int colunaOffset){
        if ((pPosicao.getLinha()+linhaOffset) < 0 || (pPosicao.getLinha()+linhaOffset) >= Auxiliar.Consts.MUNDO_ALTURA)return false;
        if ((pPosicao.getColuna()+colunaOffset) < 0 || (pPosicao.getColuna()+colunaOffset) >= Auxiliar.Consts.MUNDO_LARGURA)return false;
        if((143 > (pPosicao.getLinha() + linhaOffset)*13 + pPosicao.getColuna() + colunaOffset && (pPosicao.getLinha() + linhaOffset)*13 + pPosicao.getColuna() + colunaOffset >= 0)){
        if(Desenho.acessoATelaDoJogo().getFaseAtual().getMapaFase().getMapa().get((pPosicao.getLinha() + linhaOffset)*13 + pPosicao.getColuna() + colunaOffset) instanceof BlocoMetal){
            return false;
            }
        }
        return true;
    }

    public void criaExplosoes(){
        int danoBomba = this.vida;
        boolean flagCima=true;
        boolean flagBaixo=true;
        boolean flagEsquerda=true;
        boolean flagDireita=true;
        
        Explosao meio = new Explosao("explosãoTipo1.png", pPosicao.getLinha(), pPosicao.getColuna(), danoBomba);
        //Desenho.acessoATelaDoJogo().addPersonagem(meio);
        meio.validaPosicao();
        Desenho.acessoATelaDoJogo().adicionaModelo(meio);
        for(int i=0; i<tamanhoBomba; i++){
            //Pra cima
            if(flagCima){
                Explosao cima = new Explosao("explosãoTipo1.png", pPosicao.getLinha()-1-i, pPosicao.getColuna(),danoBomba);
                if(cima.validaPosicao()){
                    //System.out.println("valido acima");
                    //Explosao cima = new Explosao("fire.png", pPosicao.getLinha()-1-i, pPosicao.getColuna(),danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(cima);
                }
                else flagCima = false;
            }
            //Pra direita
            if(flagDireita){
                
                Explosao direita = new Explosao("explosãoTipo1.png", pPosicao.getLinha(), pPosicao.getColuna()+1+i,danoBomba);
            
                if(direita.validaPosicao()){
                    //Explosao direita = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna()+1+i,danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(direita);
                }
                else flagDireita = false;
            }
            //Pra baixo
            if(flagBaixo){
                
                Explosao baixo = new Explosao("explosãoTipo1.png", pPosicao.getLinha()+1+i, pPosicao.getColuna(),danoBomba);
                if(baixo.validaPosicao()){
                    //Explosao baixo = new Explosao("fire.png", pPosicao.getLinha()+1+i, pPosicao.getColuna(),danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(baixo);
                }
                else flagBaixo = false;
            }
            //Pra esquerda
            if(flagEsquerda){
                
                Explosao esquerda = new Explosao("explosãoTipo1.png", pPosicao.getLinha(), pPosicao.getColuna()-1-i,danoBomba);
                if(esquerda.validaPosicao()){
                    //Explosao esquerda = new Explosao("fire.png", pPosicao.getLinha(), pPosicao.getColuna()-1-i,danoBomba);
                    Desenho.acessoATelaDoJogo().adicionaModelo(esquerda);
                }
                else flagEsquerda = false;
            }
        }
        
    }
}

