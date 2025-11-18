package Auxiliar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import Modelo.Personagem;

public class GerenciaDnD {
    
    public void salvaPersonagemZIP(Personagem p){
        String nomeArquivo = p.getClass().getName() + ".zip";
        File arquivo = new File(nomeArquivo);
        try{
            FileOutputStream fos = new FileOutputStream(arquivo);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(p.getClass().getName() + ".bin");
            zos.putNextEntry(ze);
            ObjectOutputStream oos = new ObjectOutputStream(zos);
            
            oos.writeObject(p);
            oos.flush();

            zos.closeEntry();
            zos.close();
            fos.close();
            System.out.println("Personagem salvo!");
        }
        catch(Exception e){
            System.out.println("Problema no salvamento do personagem");
        }
    }

    public Personagem carregaPersonagemZIP(File arquivoZip){
        try{
            FileInputStream fis = new FileInputStream(arquivoZip);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry arquivo = zis.getNextEntry();
            if(arquivo == null){
                System.out.println("Nao achou o arquivo");
            }
            else{
                System.out.println("ARQUIVO ENCONTRADO");
            }
            ObjectInputStream ois = new ObjectInputStream(zis);
            Personagem p = (Personagem) ois.readObject();
            ois.close();
            zis.close();
            fis.close();

            return p;
        }
        catch(Exception e){
            System.out.println("Problema no carregamento do personagem");
            return null;
        }
    }
}
