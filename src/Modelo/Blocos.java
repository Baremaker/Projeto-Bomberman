/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import Auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author aserr
 */
public class Blocos extends Model implements Serializable {
    protected char codigo;
    protected int vida;
    protected boolean bDestrutivel;

    public Blocos(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
    }

    public boolean danifica(int dano){
        this.vida -= dano;
        return true;
    }

    public char getCodigo() {
        return codigo;
    }

    public int getVida() {
        return vida;
    }

    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public boolean isbDestrutivel() {
        return bDestrutivel;
    }
}
