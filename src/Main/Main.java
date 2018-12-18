package Main;

import Jeux.MenuJeu;

public class Main {
    public static boolean devMod = false;

    public static void main(String[] args) {
        if (args.length > 1) {
            if (args[1].contains("-dev")) {
                devMod = true;
            }
        }
        MenuJeu menu = new MenuJeu();
        menu.run();

    }
}

