 package Modelo;

import Modelo.BombaExplosao.BombaMina;
import Modelo.BombaExplosao.BombaNormal;
import Modelo.BombaExplosao.Bomba;
import Modelo.BombaExplosao.TipoBomba;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Fase;
import Auxiliar.Mapa;
import Controler.ControleDeJogo;
import Controler.Tela;
import Controler.Menu;
 import Controler.GameOverScreen;
import Modelo.BombaExplosao.BombaEletrica;
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
    private Bomba ultimaBombaPlantada = null;// pra checar mina
    private String movDirecao;
    private int movStage;
    
    
    
    public Hero(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG+"Frente.png", linha, coluna);
        vida = 1;
        setiImage(sNomeImagePNG+ "Frente.png");
        this.powerups = new ArrayList<>();
        this.tipoBomba = new BombaNormal();
        this.movDirecao = null;
        this.movStage = 0;
        this.nomeImagem = sNomeImagePNG;
        //this.bMortal = false;//godMode
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
            System.out.println("bomba");
            if (this.tipoBomba instanceof BombaMina && ultimaBombaPlantada != null) {
                System.out.println("bomba ja plantada");
                ultimaBombaPlantada.estouraBomba();
                ultimaBombaPlantada = null;
                return;
            }
            String nomeSpriteBomba = this.tipoBomba.getImagemBomba();
            
            Bomba b = new Bomba(nomeSpriteBomba, this.pPosicao.getLinha(), this.pPosicao.getColuna(), this,this.tipoBomba);
            Desenho.acessoATelaDoJogo().adicionaModelo(b);
            numeroBombas--;
            if (this.tipoBomba instanceof BombaMina) {
                System.out.println("bomba mina");
                ultimaBombaPlantada = b; //
                numeroBombas++;
            } else {
                ultimaBombaPlantada = null;
                
            }
            if (tipoBomba instanceof BombaEletrica) {
            Fase fase = Desenho.acessoATelaDoJogo().getFaseAtual();
            // Ativa o estado de paralisia na fase
            fase.setIsEletricidadeAtiva(true);
            
            
            
            }
            
        }
    }
    public void levaDano(int dano) {
        this.vida -= dano; // Reduz a vida

        if (this.vida <= 0) {
            if (this.possuiPowerup(MaisVida.class)) {
                // LÓGICA: PERDER POWERUP AO INVÉS DE MORRER
                //System.out.println("tenho powerup");
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
                                        
                    // Retorna a vida ao máximo
                    this.vida = 1;
                    //System.out.println("Powerup MaisVida consumido. Vida restaurada.");
                    return; // Sai da função sem remover o personagem
                }
            }
            //Começa a sequencia de morte do heroi
            this.bMortal = false;
            this.timerMorte = 6;
        }
    }
    
   

    public ArrayList<Powerup> getPowerups() {
        return powerups;
    }
    
    public int getVidas() {
        return vida;
    }
    
    public int getNumeroPowerupsVida() {
        int numero =0;
        for(Powerup pow:this.powerups){
            if(pow instanceof MaisVida)numero++;
        
        }
        
        return numero;
    }
    
    public boolean moveUp() {
        if(this.movStage == 0){
            if(this.pPosicao.moveUp()){
                if(validaPosicao()){
                    this.movDirecao = "UP";
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moveDown() {
        if(this.movStage == 0){
            if(this.pPosicao.moveDown()){
                if(validaPosicao()){
                    this.movDirecao = "DOWN";
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moveRight() {
        if(this.movStage == 0){
            if(this.pPosicao.moveRight()){
                if(validaPosicao()){
                    this.movDirecao = "RIGHT";
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moveLeft() {
        if(this.movStage == 0){
            if(this.pPosicao.moveLeft()){
                if(validaPosicao()){
                    this.movDirecao = "LEFT";
                    return true;
                }
            }
        }
        return false;
    }
    public void autoDesenho() {
        int coluna = this.pPosicao.getColuna();
        int linha = this.pPosicao.getLinha();
        int paraHorizontal = 0;
        int paraVertical = 0;
        if(this.timerMorte == -1){
            if(this.movStage == 2){
                switch(this.movDirecao){
                    case "UP": 
                        this.setiImage(this.nomeImagem + "Atras.png");
                        break;
                    case "DOWN": 
                        this.setiImage(this.nomeImagem + "Frente.png");
                        break;
                    case "RIGHT": 
                        this.setiImage(this.nomeImagem + "Direita.png");
                        break;
                    case "LEFT": 
                        this.setiImage(this.nomeImagem + "Esquerda.png");
                        break;
                }
                this.movDirecao = null;
                this.movStage = 0;          
            }
            if(this.movDirecao != null){
                this.movStage++;
                switch(this.movDirecao){
                    case "UP": 
                        this.setiImage(this.nomeImagem + "AndaAtras" + this.movStage + ".png");
                        paraVertical = -1;
                        linha++;
                        break;
                    case "DOWN":
                        this.setiImage(this.nomeImagem + "AndaFrente1.png");
                        paraVertical = 1;
                        linha--;
                        break;
                    case "RIGHT":
                        this.setiImage(this.nomeImagem + "AndaDir" + this.movStage + ".png");
                        paraHorizontal = 1;
                        coluna--;
                        break;
                    case "LEFT":
                        this.setiImage(this.nomeImagem + "AndaEsq" + this.movStage + ".png");
                        paraHorizontal = -1;
                        coluna++;
                        break;
                }
            }
        }else{
            if(this.timerMorte == 0)
                // LÓGICA DE MORTE FINAL (GAME OVER)
                Desenho.acessoATelaDoJogo().mostrarGameOver(); 
            this.setiImage(this.nomeImagem + "Dead" + this.timerMorte + ".png");
            System.out.println("morte:"+this.timerMorte);
            this.timerMorte--;
        }
        Desenho.desenharHero(this.iImage, coluna, linha,this.movStage, paraHorizontal, paraVertical);
        return;
    }
}
