package Main;

import Jeux.MenuJeu;
import Jeux.Propriétée;

public class Main {
    public static boolean devMod = false;

    public static void main(String[] args) {
        Propriétée propriétée = new Propriétée();
        if (args.length > 1) {
            if (args[1].contains("-dev")) {
                devMod = true;
            }
        }
        MenuJeu menu = new MenuJeu();
        menu.run();

    }
}

