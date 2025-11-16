/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Auxiliar.Consts;
import java.io.Serializable;

import Auxiliar.Desenho;
import Auxiliar.Fase;
import Auxiliar.Mapa;
import Modelo.Power.MaisVida;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author aserr
 */
public class Explosao extends Personagem implements Serializable {
    private int contador = 0;
    
    public Explosao(String sNomeImagePNG, int linha, int coluna,int vida){
        super(sNomeImagePNG, linha, coluna);
        setiImage(sNomeImagePNG);
        this.bMortal = true;
        this.vida=vida;
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
        if(contador == 1){
            Desenho.acessoATelaDoJogo().removePersonagem(this);
        }
        contador++;
    }
    
    public boolean validaPosicao(){
        System.out.println("linha:"+pPosicao.getLinha()+"coluna:"+pPosicao.getColuna());
        if ((this.pPosicao.getLinha()) < 0 || (this.pPosicao.getLinha()) >= Auxiliar.Consts.MUNDO_ALTURA)return false;
        if ((this.pPosicao.getColuna()) < 0 || (this.pPosicao.getColuna()) >= Auxiliar.Consts.MUNDO_LARGURA)return false;
        Fase fase = Desenho.acessoATelaDoJogo().getFaseAtual();
        Blocos blocoAlvo= fase.getMapaFase().getBlocoNaPosicao(this.pPosicao);
        ArrayList<Personagem> listaPersonagens = fase.getPersonagens();
    // 2. Busca o bloco na posição da Explosão.
        for (int i =0;i< listaPersonagens.size(); i++) {
            Personagem alvo = listaPersonagens.get(i);
        // Colisão com o alvo: Posição igual e não é a própria explosão
            if (alvo != this && alvo.getpPosicao().igual(this.pPosicao)) {
                System.out.println("vai ter");
            // Aplica dano a qualquer coisa que não seja a Bomba (que já estourou)
                if (!(alvo instanceof Bomba) /*&& !(alvo instanceof Hero)*/) {
                    if(alvo.isbMortal() ){
                        //System.out.println("dano");
                        alvo.levaDano(this.vida); // Aplica o dano da explosão (valor vindo da Bomba)
                
                        if (alvo.getVida() <= 0) {
                    // Remove o personagem se a vida zerar
                            fase.removerPersonagem(alvo); // Usa o método de Fase para remoção
                    
                        // Se o Hero morrer, a lógica de Game Over deve ser implementada aqui
                            if (alvo instanceof Hero) {
                       // LOGICA DE GAME OVER AQUI
                            }
                        }
                    }
                }
            }
        }
    
        // para destruir blocos
        if(blocoAlvo != null){
            if(!blocoAlvo.isbTransponivel()){
            // Colidiu com um bloco que não é vazio.
                if(blocoAlvo.isbDestrutivel()){
                // O bloco é destrutível (BlocoNormal '2'). Aplica dano.
                    System.out.println("destrutivel");
                    blocoAlvo.danifica(); 
                    if(blocoAlvo.getVida() <= 0){
                    // Se a vida zerou, remove o bloco e substitui por BlocoVazio
                    fase.getMapaFase().removerBloco(blocoAlvo); // NOVO MÉTODO NECESSÁRIO NO MAPA
                    BlocoVazio bv = new BlocoVazio("background1Grama.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                    fase.getMapaFase().adicionarBloco(bv); // NOVO MÉTODO NECESSÁRIO NO MAPA;
                    
                    if (Math.random() < Consts.CHANCE_POWERUP) {
                        MaisVida novoPowerup = new MaisVida("coracao.png",blocoAlvo.getpPosicao().getLinha(),blocoAlvo.getpPosicao().getColuna());
                        System.out.println("powerup");
                        fase.addPowerUp(novoPowerup); // Adiciona o Powerup ao mapa (lista de Personagens)
                    }
                        // 3. Se não spawnar Powerup, substitui por BlocoVazio (lógica original)
                        
                    
                    //BlocoVazio bv = new BlocoVazio("background1Grama.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                    //fase.getMapaFase().adicionarBloco(bv); // NOVO MÉTODO NECESSÁRIO NO MAPA
                }
                // Permite que a explosão avance (já que destruiu o alvo)
                return true; 
                } else {
                // Colidiu com BlocoMetal ('1') - Não é destrutível
                    System.out.println("metal");
                    return false; // A explosão não avança
                }
            }
        }
        
    
        return true;
    }
   

}
