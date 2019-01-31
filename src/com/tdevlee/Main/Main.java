package com.tdevlee.Main;

import com.tdevlee.Jeux.MenuJeu;
import com.tdevlee.Jeux.Proprietee;

public class Main {
    public static boolean devMod = false;

    public static void main(String[] args) {
        Proprietee.getInstance();
        if (args.length == 1) {
            if ("-dev".equals(args[0]) || Proprietee.devMod) {
                devMod = true;
            }
        }
        MenuJeu menu = new MenuJeu();
        menu.run();
    }
}

