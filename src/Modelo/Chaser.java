package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Fase;
import Auxiliar.Posicao;
import Controler.Tela;
import java.io.Serializable;
import java.util.ArrayList; // Precisamos disto para guardar o caminho
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author 2373891
 */
public class Chaser extends Personagem implements Serializable {

    /**
     * Guarda o caminho (lista de Posições) que o BFS calculou.
     * Esta é a "memória" do Chaser.
     */
    private ArrayList<Posicao> caminhoAtual;
    
    private int counter;
    
    private Posicao PHero;

    public Chaser(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        
        // Inicializamos a lista de caminho. É melhor começar com uma lista vazia do que com null.
        this.caminhoAtual = new ArrayList<>(); 
        
        this.bTransponivel = true;
        this.counter = 0;
        this.dano=3;
    }

    /**
     * Este é o novo "cérebro" que substitui o computeDirection.
     * Ele é chamado para recalcular o caminho até ao herói.
     */
    public void atualizarPHeroi(Posicao PHero){
        this.PHero = PHero;
    }
    
    private void recalcularCaminho() {
        // Acedemos à Tela principal e, a partir dela, ao herói e ao controlador do jogo
        Tela tela = Desenho.acessoATelaDoJogo();
       
        Posicao posAtual = this.getpPosicao();
        
        // Pedimos ao "cérebro" (em ControleDeJogo) para encontrar o caminho
        this.caminhoAtual = this.findPath(
                tela.getFaseAtual(), 
                posAtual, 
                PHero
        );
    }
     public ArrayList<Posicao> findPath(Fase umaFase, Posicao inicio, Posicao fim) {
         if(!this.ehPosicaoValida(fim)) return null;
        Queue<Posicao> fila = new LinkedList<>();
        // Guarda de onde viemos (ex: "para chegar a [5,5], eu vim de [5,4]")
        Map<Posicao, Posicao> veioDe = new HashMap<>();
        // Guarda as casas que já explorámos
        Set<Posicao> visitado = new HashSet<>();

        // 1. Começamos pelo início
        fila.add(inicio);
        visitado.add(inicio);
        veioDe.put(inicio, null); // O início não veio de nenhum lugar

        while (!fila.isEmpty()) {
            Posicao atual = fila.poll();

            // 2. Encontrámos o fim!
            if (atual.igual(fim)) {
                return reconstruirCaminho(veioDe, atual);
            }

            // 3. Verificar Vizinhos (Cima, Baixo, Esquerda, Direita)
            Posicao[] vizinhos = new Posicao[] {
                new Posicao(atual.getLinha() - 1, atual.getColuna()), // Cima
                new Posicao(atual.getLinha() + 1, atual.getColuna()), // Baixo
                new Posicao(atual.getLinha(), atual.getColuna() - 1), // Esquerda
                new Posicao(atual.getLinha(), atual.getColuna() + 1)  // Direita
            };

            for (Posicao vizinho : vizinhos) {
                // Se ainda não visitámos esta casa E ela é válida...
                if (!visitado.contains(vizinho) && this.ehPosicaoValida(vizinho)) {
                    // ...adicionamos à fila para explorar
                    fila.add(vizinho);
                    visitado.add(vizinho);
                    veioDe.put(vizinho, atual); // Marcamos de onde viemos
                }
            }
        }
        

        // 4. Esgotámos a fila e não encontrámos o herói
        return null; // Nenhum caminho encontrado
    }

    /**
     * Função auxiliar para seguir o "mapa" 'veioDe' de volta ao início.
     */
    private ArrayList<Posicao> reconstruirCaminho(Map<Posicao, Posicao> veioDe, Posicao fim) {
        ArrayList<Posicao> caminho = new ArrayList<>();
        Posicao atual = fim;

        while (veioDe.get(atual) != null) { // Enquanto não chegarmos ao início
            caminho.add(atual);
            atual = veioDe.get(atual);
        }
        // Invertemos a lista, porque ela foi construída do Fim -> Início
        Collections.reverse(caminho); 
        return caminho;
    }
    
    /**
     * O autoDesenho agora é responsável por:
     * 1. Decidir QUANDO recalcular o caminho (a cada 20 ticks).
     * 2. Executar o próximo passo desse caminho.
     */
    @Override
    public void autoDesenho() {
      if(!paralisia()){  
        
        if (counter == 10) { 
            counter = 0; // Reseta o contador
            
            // 1. FAZER O PLANO (DINÂMICO)
            // Sempre recalcula o caminho para a posição ATUAL do herói
            this.recalcularCaminho(); 
            // 2. EXECUTAR O PLANO
            // Se o BFS encontrou um caminho...
            if (this.caminhoAtual != null && !this.caminhoAtual.isEmpty()) {
                
                // Pega o primeiro passo do caminho
                Posicao proximoPasso = this.caminhoAtual.get(0); 
                
                // Move-se DIRETAMENTE para essa posição
                // Não precisamos de validação (mergulho), pois o findPath JÁ GARANTIU
                // que o "proximoPasso" é uma casa válida e livre.
                this.setPosicao(proximoPasso.getLinha(), proximoPasso.getColuna());
            }
            // Se o caminho for null (herói inalcançável), o Chaser simplesmente não se move.
        }
        counter++;
      }   
        
        super.autoDesenho();
    }
    public boolean ehInimigo(){
        return true;
    }
}