/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.BombaExplosao;
import Modelo.BombaExplosao.Bomba;
import Auxiliar.Consts;
import java.io.Serializable;

import Auxiliar.Desenho;
import Auxiliar.Fase;
import Auxiliar.Mapa;
import Modelo.BlocoVazio;
import Modelo.Blocos;
import Modelo.Hero;
import Modelo.Personagem;
import Modelo.Power.BombaDarkPower;
import Modelo.Power.BombaEletricaPower;
import Modelo.Power.BombaMegaPower;
import Modelo.Power.BombaMinaPower;
import Modelo.Power.MaisVida;
import Modelo.Power.Powerup;
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
    private int duracaoExplosao;
    private int dano=3;
    
    public Explosao(String sNomeImagePNG, int linha, int coluna){
        //super(decodificaTipo(tipoExplosao), linha, coluna);
        super(sNomeImagePNG, linha, coluna); 
        setiImage(sNomeImagePNG);
        this.bMortal = false;
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
        if(contador == duracaoExplosao){
            Desenho.acessoATelaDoJogo().removePersonagem(this);
        }
        contador++;
    }
    
    public boolean validaPosicao(){//define a interação da bomba com o resto do mapa
        int contador = 0;
        //System.out.println("linha:"+pPosicao.getLinha()+"coluna:"+pPosicao.getColuna());
        if ((this.pPosicao.getLinha()) < 0 || (this.pPosicao.getLinha()) >= Auxiliar.Consts.MUNDO_ALTURA)return false;
        if ((this.pPosicao.getColuna()) < 0 || (this.pPosicao.getColuna()) >= Auxiliar.Consts.MUNDO_LARGURA)return false;
        Fase fase = Desenho.acessoATelaDoJogo().getFaseAtual();
        Blocos blocoAlvo= fase.getMapaFase().getBlocoNaPosicao(this.pPosicao);
        ArrayList<Personagem> listaPersonagens = fase.getPersonagens();
    // 2. Busca o bloco na posição da Explosão.
        
        aplicadano(listaPersonagens,fase);// aplica dano nos personagens
        // para destruir blocos
        if(blocoAlvo != null){
            if(!blocoAlvo.isbTransponivel()){
            // Colidiu com um bloco que não é vazio.
                if(blocoAlvo.isbDestrutivel()){
                // O bloco é destrutível (BlocoNormal '2'). Aplica dano.
                    
                    blocoAlvo.danifica(); 
                    if(blocoAlvo.getVida() <= 0){
                    // Se a vida zerou, remove o bloco e substitui por BlocoVazio
                    fase.getMapaFase().removerBloco(blocoAlvo); // NOVO MÉTODO NECESSÁRIO NO MAPA
                    BlocoVazio bv;
                    switch(fase.getNumeroDaFase()){
                        case 1:
                             bv = new BlocoVazio("background1Grama.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        case 2:
                             bv = new BlocoVazio("background2Circuito.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        case 3:
                             bv = new BlocoVazio("background3Azulejo.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        case 4:
                             bv = new BlocoVazio("background4Ferro.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        case 5:
                             bv = new BlocoVazio("background5Final.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            break;
                        default:
                            bv = new BlocoVazio("background1Grama.png", blocoAlvo.getpPosicao().getLinha(), blocoAlvo.getpPosicao().getColuna());
                            
                    }      
                    fase.getMapaFase().adicionarBloco(bv); // NOVO MÉTODO NECESSÁRIO NO MAPA;
                    
                    if (Math.random() < Consts.CHANCE_POWERUP) {
                        double bombaOUvida = Consts.CHANCE_VIDA;
                        for(Powerup pow: fase.getHero().getPowerups()){if(!(pow instanceof MaisVida))bombaOUvida=1.00;}
                        System.out.println("bomba ou vida:"+bombaOUvida);
                        if(Math.random() < bombaOUvida){//garante que apos pegar o powerup da bomba, só pode spawnar mais vida
                            MaisVida novoPowerup = new MaisVida("coracao.png",blocoAlvo.getpPosicao().getLinha(),blocoAlvo.getpPosicao().getColuna());
                            
                            fase.addPowerUp(novoPowerup); // Adiciona o Powerup ao mapa (lista de Personagens)
                        }else{
                            Powerup novoPowerup = null;
                            switch(fase.getNumeroDaFase()){//garante que o pwerup dropa na parte certa
                                case 2:
                                    for(Powerup pow: fase.getHero().getPowerups()){if(pow instanceof BombaMegaPower)contador++;}
                                    if(contador ==0){
                                
                                        
                                    novoPowerup = new BombaMegaPower("bomba.png",blocoAlvo.getpPosicao().getLinha(),blocoAlvo.getpPosicao().getColuna());
                                    fase.addPowerUp(novoPowerup);
                            
                                    }
                                    break;
                                case 3:
                                    for(Powerup pow: fase.getHero().getPowerups()){if(pow instanceof BombaDarkPower)contador++;}
                                    if(contador ==0){
                                
                                    novoPowerup = new BombaDarkPower("bomba.png",blocoAlvo.getpPosicao().getLinha(),blocoAlvo.getpPosicao().getColuna());
                                        
                                    
                                    fase.addPowerUp(novoPowerup);
                            
                                    }
                                     break;
                                case 4:
                                    for(Powerup pow: fase.getHero().getPowerups()){if(pow instanceof BombaEletricaPower)contador++;}
                                    if(contador ==0){
                                
                                        novoPowerup = new BombaEletricaPower("bomba.png",blocoAlvo.getpPosicao().getLinha(),blocoAlvo.getpPosicao().getColuna());
                                
                                        fase.addPowerUp(novoPowerup);
                                        break;
                                    }
                                case 5:
                                    for(Powerup pow: fase.getHero().getPowerups()){if(pow instanceof BombaMinaPower)contador++;}
                                    if(contador ==0){
                                    novoPowerup = new BombaMinaPower("bomba.png",blocoAlvo.getpPosicao().getLinha(),blocoAlvo.getpPosicao().getColuna());
                                
                                    fase.addPowerUp(novoPowerup);
                                        break;
                                    }
                                default:
                                    //System.out.println("fase invalida:"+fase.getNumeroDaFase());
                                    novoPowerup = new MaisVida("coracao.png",blocoAlvo.getpPosicao().getLinha(),blocoAlvo.getpPosicao().getColuna());
                                    fase.addPowerUp(novoPowerup);
                                    break;
                            }
                            contador = 0;
                        }
                    }
                        // 3. Se não spawnar Powerup, substitui por BlocoVazio (lógica original)
                    
                    return true;
                }else{return false;}
                // Permite que a explosão avance (já que destruiu o alvo)
                } else {
                // Colidiu com BlocoMetal ('1') - Não é destrutível
                    //System.out.println("metal");
                    return false; // A explosão não avança
                }
            }
            return true;
        }
        
    
        return true;
    }
   
    public void aplicadano(ArrayList<Personagem> listaPersonagens, Fase fase){
        for (int i =0;i< listaPersonagens.size(); i++) {
            Personagem alvo = listaPersonagens.get(i);
        // Colisão com o alvo: Posição igual e não é a própria explosão
            if (alvo != this && alvo.getpPosicao().igual(this.pPosicao) && !(alvo instanceof Bomba)) {
                //System.out.println("vai ter");
            // Aplica dano a qualquer coisa que não seja a Bomba (que já estourou)
                    if(alvo.isbMortal() ){
                        //System.out.println("dano");
                        alvo.levaDano(this.dano); // Aplica o dano da explosão (valor vindo da Bomba)
                        }
                    }
                
            }
        }
    
    
    

}
