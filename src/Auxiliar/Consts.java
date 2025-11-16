package Auxiliar;

import java.io.File;

public class Consts {
    public static final int CELL_SIDE = 60; // pixels por lado de celula
    public static final int MUNDO_LARGURA = 13; // total do mundo
    public static final int MUNDO_ALTURA = 11;
    public static final int RES = 25; // visível na tela - câmera
    public static final int PERIOD = 100 ;
    public static final String PATH = File.separator+"imgs"+File.separator;
    public static final String PATHBLOCO = File.separator+"background_parede"+File.separator;
    public static final String PATHHEROI = File.separator+"hero"+File.separator;
    public static final String PATHINIMIGOS = File.separator+"inimigo"+File.separator;
    public static final String PATHBOMBA = File.separator+"bombas"+File.separator;
    public static final int TIMER = 20;
    public static final float HERO_SPEED_PIXELS = 4.0f; // NOVO: Velocidade em pixels/frame
}