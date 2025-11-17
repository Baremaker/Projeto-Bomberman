 package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Fase;
import Auxiliar.Mapa;
import Controler.ControleDeJogo;
import Controler.Tela;
import Modelo.Power.MaisVida;
import Modelo.Power.Powerup;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Clase heroi: estende a classe mãe personagem e outra classe mãe Model
//Possui os atributos:
//numeroBombas, tamanhoBomba, tipoBomba, velocidade
public class Hero extends Personagem implements Serializable {
    private int numeroBombas = 1;
    private int velocidade;
    private ArrayList<Powerup> powerups;
    private TipoBomba tipoBomba;
    
    public Hero(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        vida = 1;
        setiImage(sNomeImagePNG);
        this.powerups = new ArrayList<>();
        this.tipoBomba = new BombaNormal();
    }
    
    
    public void setiImage(String sNomeImagePNG) {
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH +Consts.PATHHEROI+ sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void coletarPowerup(Powerup pow) {
        // Verifica se o powerup já existe (ex: se só pode ter um MaisVida)
        
            this.powerups.add(pow);
            pow.aplicarEfeito(this); // O MaisVida não faz nada, mas outros podem fazer
        
    }
    
    public boolean possuiPowerup(Class<? extends Powerup> tipo) {
        for (Powerup p : powerups) {
            if (tipo.isInstance(p)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean setPosicao(int linha, int coluna){
        this.pPosicao.setPosicao(linha, coluna);
        if(this.validaPosicao()){
            return true;
        }
        return false;       
    }

    public int getNumeroBombas() {
        return numeroBombas;
    }

    public void setNumeroBombas(int numeroBombas) {
        this.numeroBombas = numeroBombas;
    }

    public TipoBomba getTipoBomba() {
        return tipoBomba;
    }
    
    public String getNomeTipoBomba() {
        switch(tipoBomba.getImagemBomba()){
            case "bombaNormal.png":
                return "Bomba Normal";
            case "bombaDarknessTrevoso.png":
                return "Bomba Darkness Trevosso do Mal";
            case "bombaEletrizante.png":
                return "Bomba Eletrizante";
            case "megaBomba.png":
                return "Mega Bomba";
            case "minaExplosiva.png":
                return "Mina Explosiva";
            default: return "Tipo nao identificado";
        }
    }

    public void setTipoBomba(TipoBomba tipoBomba) {
        this.tipoBomba = tipoBomba;
    }
    
    public void colocaBomba(){
        if(numeroBombas > 0){
            String nomeSpriteBomba = this.tipoBomba.getImagemBomba();
            
            Bomba b = new Bomba(nomeSpriteBomba, this.pPosicao.getLinha(), this.pPosicao.getColuna(), this,this.tipoBomba);
            Desenho.acessoATelaDoJogo().adicionaModelo(b);
            numeroBombas--;
        }
    }
    public void levaDano(int dano) {
        this.vida -= dano; // Reduz a vida

        if (this.vida <= 0) {
            if (this.possuiPowerup(MaisVida.class)) {
                // LÓGICA: PERDER POWERUP AO INVÉS DE MORRER
                System.out.println("tenho powerup");
                // Encontra e remove o powerup MaisVida (só o primeiro)
                Powerup powerupPerdido = null;
                for (Powerup p : powerups) {
                    if (p instanceof MaisVida) {
                        powerupPerdido = p;
                        break;
                    }
                }
                
                if (powerupPerdido != null) {
                    this.powerups.remove(powerupPerdido);
                    //powerupPerdido.reverterEfeito(this); // Reverte o efeito (aqui não faz nada)
                    
                    // Retorna a vida ao máximo
                    this.vida = 1;
                    System.out.println("Powerup MaisVida consumido. Vida restaurada.");
                    return; // Sai da função sem remover o personagem
                }
            }
            System.out.println("vida:"+this.vida);
            // LÓGICA DE MORTE FINAL (GAME OVER)
            //Desenho.acessoATelaDoJogo().removePersonagem(this); 
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    /*
    public boolean moveUp() {
        this.pPosicao.velocidadeY = -Consts.HERO_SPEED_PIXELS;
        //this.pPosicao.velocidadeX = 0; 
        return true; 
    }

    public boolean moveDown() {
        this.pPosicao.velocidadeY = Consts.HERO_SPEED_PIXELS;
        //this.pPosicao.velocidadeX = 0;
        return true;
    }

    public boolean moveRight() {
        this.pPosicao.velocidadeX = Consts.HERO_SPEED_PIXELS;
        //this.pPosicao.velocidadeY = 0;
        return true;
    }

    public boolean moveLeft() {
        this.pPosicao.velocidadeX = -Consts.HERO_SPEED_PIXELS;
        //this.pPosicao.velocidadeY = 0;
        return true;
    }
    */

    public int getVidas() {
        return vida;
    }
    
    
    
}
