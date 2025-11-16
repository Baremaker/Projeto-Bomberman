package Auxiliar;

import java.io.Serializable;

public class Posicao implements Serializable {
    private int linha;
    private int coluna;
    
    private int linhaAnterior;
    private int colunaAnterior;
    
    private float pixel_x;
    private float pixel_y;
    
    public float velocidadeX = 0;
    public float velocidadeY = 0;

    public Posicao(int linha, int coluna) {
        this.setPosicao(linha, coluna);
    }

    public boolean setPosicao(int linha, int coluna) {
        //if (linha < 0 || linha >= Auxiliar.Consts.MUNDO_ALTURA)
            //return false;
        linhaAnterior = this.linha;
        this.linha = linha;

        //if (coluna < 0 || coluna >= Auxiliar.Consts.MUNDO_LARGURA)
            //return false;
        colunaAnterior = this.coluna;
        this.coluna = coluna;
        
        //this.pixel_x=coluna*Auxiliar.Consts.CELL_SIDE;
        //this.pixel_y=linha*Auxiliar.Consts.CELL_SIDE;
        return true ;
    }
        
    
    public void updatePixelPosition() {//funcao pra andar liso
        // Aplica o movimento
        this.pixel_x += this.velocidadeX;
        this.pixel_y += this.velocidadeY;

        // Sincroniza a grade (linha/coluna) com o pixel (para lógica de jogo)
        this.linhaAnterior = this.linha;
        this.colunaAnterior = this.coluna;
        this.linha = (int) (this.pixel_y / Consts.CELL_SIDE);
        this.coluna = (int) (this.pixel_x / Consts.CELL_SIDE);
    }
    
    public float getX() { return pixel_x; }
    public float getY() { return pixel_y; }
    public void setX(float x) { this.pixel_x = x; }
    public void setY(float y) { this.pixel_y = y; }
    
    
    
    
    
    
    
    public boolean volta() {
        return this.setPosicao(linhaAnterior, colunaAnterior);
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public boolean igual(Posicao posicao) {
        return (linha == posicao.getLinha() && coluna == posicao.getColuna());
    }

    public boolean copia(Posicao posicao) {
        return this.setPosicao(posicao.getLinha(), posicao.getColuna());
    }

    public boolean moveUp() {
        return this.setPosicao(this.getLinha() - 1, this.getColuna());
    }

    public boolean moveDown() {
        return this.setPosicao(this.getLinha() + 1, this.getColuna());
    }

    public boolean moveRight() {
        return this.setPosicao(this.getLinha(), this.getColuna() + 1);
    }

    public boolean moveLeft() {
        return this.setPosicao(this.getLinha(), this.getColuna() - 1);
    }
    
    public boolean equals(Object o) {
        // Se for o mesmo objeto na memória, é igual
        if (this == o) return true;
        
        // Se o outro for nulo ou de uma classe diferente, não é igual
        if (o == null || getClass() != o.getClass()) return false;
        
        // Converte o "Object" para "Posicao"
        Posicao posicao = (Posicao) o;
        
        // Compara os valores de linha e coluna
        return linha == posicao.linha && coluna == posicao.coluna;
    }
    public int hashCode() {
        // Usa números primos para misturar a linha e a coluna num único int
        return 31 * linha + coluna;
    }
}
