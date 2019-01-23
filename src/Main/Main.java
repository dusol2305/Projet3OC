package Main;

import Jeux.MenuJeu;
import Jeux.Proprietee;

public class Main {
    public static boolean devMod = false;

    public static void main(String[] args) {
        Proprietee.getInstance();
        if (args.length > 1) {
            if (args[1].equals("-dev") || Proprietee.devMod) {
                devMod = true;
            }
        }
        MenuJeu menu = new MenuJeu();
        menu.run();
    }
}

