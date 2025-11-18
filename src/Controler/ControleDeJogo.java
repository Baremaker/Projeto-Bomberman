package Controler;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.Model;
import Modelo.Chaser;
import Modelo.Personagem;
import Modelo.Hero;
import Auxiliar.Posicao;
import java.util.ArrayList;
import Auxiliar.Fase;
import Auxiliar.Mapa;
import Modelo.Blocos;
import Modelo.BlocoMetal;
import Modelo.BlocoVazio;
import Modelo.BombaExplosao.Bomba;
import Modelo.BombaExplosao.Explosao;
import Modelo.Portal;
import Modelo.Power.Powerup;
import java.awt.Rectangle;
import java.io.Serializable;
public class ControleDeJogo implements Serializable{
    
    public void desenhaTudo(Fase faseAtual) {
        Mapa map = faseAtual.getMapaFase();
        ArrayList<Blocos> mapa = map.getMapa();
        for (int i = 0; i < mapa.size(); i++) {
            // Chamada do autoDesenho para renderização
            mapa.get(i).autoDesenho(); 
        }
        
        
        ArrayList<Personagem> listaPersonagens = faseAtual.getPersonagens();
        for (int i = 0; i < listaPersonagens.size(); i++) {
            // Chamada do autoDesenho para renderização
            listaPersonagens.get(i).autoDesenho(); 
        }
        ArrayList<Powerup> powerups = faseAtual.getPowerups();
        
        for (int i = 0; i < powerups.size(); i++) {
            // Chamada do autoDesenho para renderização
            powerups.get(i).autoDesenho(); 
        }
        
    }
    
    public void processaTudo(Fase FaseAtual) {
        Hero hero = FaseAtual.getHero();
        ArrayList<Personagem> umaFase = FaseAtual.getPersonagens();
        ArrayList<Blocos> mapa = FaseAtual.getMapaFase().getMapa();
        ArrayList<Powerup> power =FaseAtual.getPowerups();
        int inimigosVivos = 0;
        for(Personagem p : umaFase){
            //Lida com casos de posição do personagem igual a do heroi
            if (!(p instanceof Hero) && !(p instanceof Bomba) && !(p instanceof Explosao)) {
                inimigosVivos++;
            }
            if(hero.getpPosicao().igual(p.getpPosicao())){
                if(p.ehInimigo()){
                    //Fazer função para heroi tomar dano e ficar invencivel por algum tempo
                    hero.levaDano(p.getDano());
                }
            }
            
            
            if(p instanceof Chaser){
                ((Chaser) p).atualizarPHeroi(hero.getpPosicao());
            }
        }
        
        if (inimigosVivos == 0 ) {
            
            int linhaPortal = 0;
            int colunaPortal = 1;
            Portal p = new Portal(linhaPortal, colunaPortal);
            Blocos b =FaseAtual.getMapaFase().getBlocoNaPosicao(p.getpPosicao());
            if (b instanceof Blocos) {
                FaseAtual.getMapaFase().removerBloco(b);
                FaseAtual.getMapaFase().adicionarBloco(p);
            
            }
        }
        Blocos blocoHeroi = FaseAtual.getMapaFase().getBlocoNaPosicao(hero.getpPosicao());
        if (blocoHeroi instanceof Portal) {
            // O herói pisou no Portal!
            Desenho.acessoATelaDoJogo().vitoria(); // Chama o método de progressão de fase
            return; // Encerra o processamento para evitar movimentos adicionais
        }
        
        for(Powerup pow:power){
            if(hero.getpPosicao().igual(pow.getpPosicao())){
              hero.coletarPowerup(pow);
              FaseAtual.removerPowerUp(pow);
              break;
            }
        }
        
        
        
        
        
    }

    /*Retorna true se a posicao p é válida para Hero com relacao a todos os personagens no array*/
    /*public void ehPosicaoValida(Fase umaFase, Posicao p) {
        ArrayList<Personagem> lista = umaFase.getPersonagens();
        Mapa map = umaFase.getMapaFase();
        Model pIesimoPersonagem;
        for (int i = 0; i < map.getMapa().size(); i++){  
            Blocos bloco = map.getMapa().get(i);
            if(!bloco.isbTransponivel()&& !(bloco instanceof BlocoVazio)){
                if (bloco.getpPosicao().igual(p)) {
                    //return false;
                }
        
            }
        }
        
        
        
        for (int i = 1; i < lista.size(); i++) {
            pIesimoPersonagem = lista.get(i);
            if (!pIesimoPersonagem.isbTransponivel()) {
                if (pIesimoPersonagem.getpPosicao().igual(p)) {
                    //return false;
                }
            }
        }
        //return true;
        
        
        
    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public boolean ehPosicaoInimigo(Fase umaFase, Posicao p) {
        Mapa map = umaFase.getMapaFase();      
        for (int i = 0; i < map.getMapa().size(); i++){  
            Blocos bloco = map.getMapa().get(i);
            if(!bloco.isbTransponivel()){
                if (bloco.getpPosicao().igual(p)) {
                    return false;
                }
        
            }
        }
        return true;
    }
    
    public boolean ehExplosaoValida(Fase umaFase,Posicao p){
        
        
        
        
        return true;
        
    }
    
    
    
}