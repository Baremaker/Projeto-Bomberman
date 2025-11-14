import Controler.Menu;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Menu menu = new Menu();
            menu.setVisible(true);
        });
    }
}

