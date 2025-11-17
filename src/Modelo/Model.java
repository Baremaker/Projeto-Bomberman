/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.BombaExplosao.Bomba;
import Modelo.BombaExplosao.Explosao;
import Auxiliar.Mapa;
import Auxiliar.Fase;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import Auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


//Essa classe é a mãe de Personagem e Blocos, possuindo atributos:
//Imagem: iImage, Posicao: pPosicao, Transponivel? bTransponivel
public abstract class Model implements Serializable {
    protected ImageIcon iImage; //Imagem do modelo
    public Posicao pPosicao; //Posicao do modelo
    protected boolean bTransponivel; //É transponivel?

    protected Model(String sNomeImagePNG, int linha, int coluna) {
        this.pPosicao = new Posicao(1, 1);
        this.bTransponivel = true;
        //setiImage(sNomeImagePNG);
        this.setPosicao(linha, coluna);
    }

    public abstract void setiImage(String sNomeImagePNG);/*{
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }*/

    public Posicao getpPosicao() {
        return pPosicao;
    }

    public boolean setPosicao(int linha, int coluna) {
        return pPosicao.setPosicao(linha, coluna);
    }

    public boolean isbTransponivel() {
        return bTransponivel;
    }

    public void setbTransponivel(boolean bTransponivel) {
        this.bTransponivel = bTransponivel;
    }

    public void autoDesenho() {
        Desenho.desenhar(this.iImage, this.pPosicao.getColuna(), this.pPosicao.getLinha());
    }
    
    public Rectangle getHitbox() {
        // Usa as coordenadas de pixel (float x, y) da Posicao para a checagem AABB
        return new Rectangle((int)pPosicao.getX(), (int)pPosicao.getY(), Consts.CELL_SIDE, Consts.CELL_SIDE);
    }
        //Essa função valida a posição APÓS a movimentação: se a posição não for válida ele retorna para a posição anterior
    //Função para validar a posição do modelo considerando o mapa, os personagens e se esta fora ou não da tela
    public boolean validaPosicao(){
        if(!this.ehPosicaoValida(this.pPosicao)){
            voltaAUltimaPosicao();
            return false;
        }else return true;        
    }
    public boolean ehPosicaoValida(Posicao p){
        if(p.getLinha() < 0 || p.getLinha() >= Auxiliar.Consts.MUNDO_ALTURA){
            return false;
        }
        if(p.getColuna() < 0 || p.getColuna() >= Auxiliar.Consts.MUNDO_LARGURA){
            return false;
        }
        Fase fase = Desenho.acessoATelaDoJogo().getFaseAtual();
        Mapa mapa = fase.getMapaFase();
        for (Personagem personagem : fase.getPersonagens()) {
            if (p.igual(personagem.getpPosicao())) {
                if (!personagem.isbTransponivel()) {
                    return false;
                }
            }
        }
        for (Blocos b : mapa.getMapa()) {
            if (p.igual(b.getpPosicao())) {
                if (!b.isbTransponivel()) {
                    return false;
                }
            }
        }
        return true;
        
    }

    public boolean voltaAUltimaPosicao(){
        return this.pPosicao.volta();
    }    
    
}
