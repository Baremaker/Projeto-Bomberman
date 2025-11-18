package Auxiliar;

import java.io.Serializable;

public class Posicao implements Serializable {
    private int linha;
    private int coluna;
    
    private int linhaAnterior;
    private int colunaAnterior;
    
    

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
