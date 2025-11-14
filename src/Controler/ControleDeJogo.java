package Controler;
import Modelo.Model;
import Modelo.Chaser;
import Modelo.Personagem;
import Modelo.Hero;
import auxiliar.Posicao;
import java.util.ArrayList;
import Auxiliar.Fase;
import Auxiliar.Mapa;
import Modelo.Blocos;
import Modelo.BlocoMetal;
import Modelo.Bomba;
import Modelo.Explosao;
public class ControleDeJogo {
    
    public void desenhaTudo(Fase faseAtual) {
        Mapa map = faseAtual.getMapaFase();
        ArrayList<Blocos> mapa = map.getMapa();
        for (int i = 0; i < mapa.size(); i++) {
            // Chamada do autoDesenho para renderização
            mapa.get(i).autoDesenho(); 
        }
        ArrayList<Model> listaPersonagens = faseAtual.getPersonagens();
        for (int i = 0; i < listaPersonagens.size(); i++) {
            // Chamada do autoDesenho para renderização
            listaPersonagens.get(i).autoDesenho(); 
        }
    }
    
    public void processaTudo(ArrayList<Model> umaFase) {
        Hero hero = (Hero) umaFase.get(0);
        Model pIesimoPersonagem;
        
        for (int i = 1; i < umaFase.size(); i++) {
            pIesimoPersonagem = umaFase.get(i);
            
            if (hero.getPosicao().igual(pIesimoPersonagem.getPosicao())) {
                if (pIesimoPersonagem.isbTransponivel()) /*TO-DO: verificar se o personagem eh mortal antes de retirar*/ {
                    if (pIesimoPersonagem.isbMortal())
                        umaFase.remove(pIesimoPersonagem);
                }
            }
            //pIesimoPersonagem = umaFase.get(i);
                        
            if (pIesimoPersonagem instanceof Chaser) {
                ((Chaser) pIesimoPersonagem).computeDirection(hero.getPosicao());
            }
            if (pIesimoPersonagem instanceof Bomba) {
                if(!pIesimoPersonagem.getPosicao().igual(hero.getPosicao())){
                    pIesimoPersonagem.setbTransponivel(false);
                }
            } 
            
            
            if (pIesimoPersonagem instanceof Explosao) {
                Explosao explosao = (Explosao) pIesimoPersonagem;
                int dano = explosao.getVida();
            
                for (int j = 0; j < umaFase.size(); j++) {
                Personagem alvo =(Personagem)umaFase.get(j);
                
                // Regras de Colisão: Alvo diferente da própria Explosão e na mesma posição
                    if (alvo != explosao && alvo.getPosicao().igual(explosao.getPosicao())) {
                    
                    // Regra de dano: Aplica dano a qualquer coisa que não seja a Bomba (que já estourou)
                        if (!(alvo instanceof Bomba)) {
                        
                        // Inimigos e Hero devem ser alvos:
                            if (alvo != hero) { // Se for um inimigo ou outro personagem
                                alvo.levaDano(dano); 
                            
                            // Remove o alvo se a vida acabar
                                if (alvo.getVida() <= 0) {
                                    umaFase.remove(alvo);
                                    j--; // Ajusta o índice após a remoção
                                }
                            }
        
                        }    
                    }
                }
            }
                
        }       
        
    }

    /*Retorna true se a posicao p é válida para Hero com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(Fase umaFase, Posicao p) {
        ArrayList<Model> lista = umaFase.getPersonagens();
        Mapa map = umaFase.getMapaFase();
        Model pIesimoPersonagem;
      
        
        for (int i = 0; i < map.getMapa().size(); i++){  
            Blocos bloco = map.getMapa().get(i);
            if(!bloco.isbTransponivel()){
                if (bloco.getPosicao().igual(p)) {
                    return false;
                }
        
            }
        }
        
        
        
        for (int i = 1; i < lista.size(); i++) {
            pIesimoPersonagem = lista.get(i);
            if (!pIesimoPersonagem.isbTransponivel()) {
                if (pIesimoPersonagem.getPosicao().igual(p)) {
                    return false;
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
                if (bloco.getPosicao().igual(p)) {
                    return false;
                }
        
            }
        }
        return true;
    }
 }
