package Controler;
import Auxiliar.Consts;
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
import Modelo.Bomba;
import Modelo.Explosao;
import java.awt.Rectangle;
public class ControleDeJogo {
    
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
    }
    
    public void processaTudo(Fase FaseAtual) {
        Hero hero = FaseAtual.getHero();
        ArrayList<Personagem> umaFase = FaseAtual.getPersonagens();
        ArrayList<Blocos> mapa = FaseAtual.getMapaFase().getMapa();

        for(Personagem p : umaFase){
            //Lida com casos de posição do personagem igual a do heroi
            if(hero.getpPosicao().igual(p.getpPosicao())){
                if(p.isbMortal()){
                    //Fazer função para heroi tomar dano e ficar invencivel por algum tempo
                    if(p instanceof Explosao){
                        hero.levaDano(((Explosao) p).getVida());
                    }
                }
            }
            //Lida com cada caso de instância do personagem
            if(p instanceof Chaser){
                ((Chaser) p).computeDirection(hero.getpPosicao());
            }
            if(p instanceof Bomba){
                if(!p.getpPosicao().igual(hero.getpPosicao())){
                    p.setbTransponivel(false);
                }
            }
        }
        
        /*for (int i = 1; i < umaFase.size(); i++) {
            if (pIesimoPersonagem instanceof Explosao) {
                Explosao explosao = (Explosao) pIesimoPersonagem;
                int dano = explosao.getVida();
                for (int j = 0; j < umaFase.size(); j++) {
                Personagem alvo =(Personagem)umaFase.get(j);
                bloco = mapa.get(j);
                // Regras de Colisão: Alvo diferente da própria Explosão e na mesma posição
                    if (bloco.isbDestrutivel() && bloco.getpPosicao().igual(explosao.getpPosicao())){
                        bloco.danifica(dano);
                        if(bloco.getCodigo()<=0){
                        }
                    }
                    if (alvo != explosao && alvo.getpPosicao().igual(explosao.getpPosicao())) {
                    // Regra de dano: Aplica dano a qualquer coisa que não seja a Bomba (que já estourou)
                        if (!(alvo instanceof Bomba)) {
                        // Inimigos e Hero devem ser alvos:
                             // Se for um inimigo ou outro personagem
                                alvo.levaDano(dano); 
                            // Remove o alvo se a vida acabar
                                if (alvo.getVida() <= 0) {
                                    umaFase.remove(alvo);
                                    j--; // Ajusta o índice após a remoção
                                }
                        }    
                    }*/
    }

    /*Retorna true se a posicao p é válida para Hero com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(Fase umaFase, Posicao p) {
        ArrayList<Personagem> lista = umaFase.getPersonagens();
        Mapa map = umaFase.getMapaFase();
        Model pIesimoPersonagem;
        for (int i = 0; i < map.getMapa().size(); i++){  
            Blocos bloco = map.getMapa().get(i);
            if(!bloco.isbTransponivel()&& !(bloco instanceof BlocoVazio)){
                if (bloco.getpPosicao().igual(p)) {
                    return false;
                }
        
            }
        }
        
        
        
        for (int i = 1; i < lista.size(); i++) {
            pIesimoPersonagem = lista.get(i);
            if (!pIesimoPersonagem.isbTransponivel()) {
                if (pIesimoPersonagem.getpPosicao().igual(p)) {
                    return false;
                }
            }
        }
        return true;
        
        
        
    }
    
    public boolean checarColisaoAABB(Fase faseAtual, Hero hero, float novaX, float novaY) {
        
        Rectangle heroFutureBounds = new Rectangle(
            (int) novaX, 
            (int) novaY, 
            Consts.CELL_SIDE, 
            Consts.CELL_SIDE
        );

        // 2. Checa Colisão com BORDAS DO MUNDO
        float mundoLarguraPixel = Consts.MUNDO_LARGURA * Consts.CELL_SIDE;
        float mundoAlturaPixel = Consts.MUNDO_ALTURA * Consts.CELL_SIDE;
        
        if (novaX < 0 || novaX + Consts.CELL_SIDE > mundoLarguraPixel || 
            novaY < 0 || novaY + Consts.CELL_SIDE > mundoAlturaPixel) {
            return false;
        }
        
        // 3. Checa Colisão com PERSONAGENS
        for (int i = 1; i < faseAtual.getPersonagens().size(); i++) {
            Personagem p = faseAtual.getPersonagens().get(i);
            if (!p.isbTransponivel()) { 
                if (heroFutureBounds.intersects(p.getHitbox())) { 
                    return false;
                }
            }
        }
        
        // 4. Checa Colisão com BLOCOS
        if (faseAtual.getMapaFase() != null) {
            for (Blocos bloco : faseAtual.getMapaFase().getMapa()) {
                if (!bloco.isbTransponivel()) { 
                    if (heroFutureBounds.intersects(bloco.getHitbox())) { 
                        return false; 
                    }
                }
            }
        }

        return true; 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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