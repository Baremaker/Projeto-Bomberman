/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.io.Serializable;
/**
 *
 * @author aserr
 */
public class BlocoForte extends Blocos implements Serializable {
    
    public BlocoForte(String sNomeImagePNG, int linha, int coluna) {
        super(sNomeImagePNG, linha, coluna);
        this.bTransponivel = false;
        this.codigo = '3';
        this.vida = 2;
        this.bDestrutivel = true;
    
    
    }
    @Override
    public boolean danifica(){
        // 1. Chama o dano da classe pai (Blocos.java), que decrementa a vida
        boolean sucesso = super.danifica();
        
        if (sucesso) {
            // 2. Verifica a vida restante
            if (this.vida == 1) {
                // 3. Se a vida caiu para 1, troca a imagem (só faz uma vez)
                
                // Atualiza o nome da imagem armazenada no Model
                this.sNomeImagePNG = "paredeForteRachada.png"; 
                
                // Recarrega o ImageIcon
                this.setiImage("paredeForteRachada.png"); 
                
                System.out.println("Bloco Forte danificado. Nova vida: 1.");
            }
            // Se a vida for 0, ele será destruído pela Explosão, então não precisamos trocar a imagem aqui.
        }
        
        return sucesso;
    }
}
