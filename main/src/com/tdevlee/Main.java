package com.tdevlee;

import com.tdevlee.jeux.MenuJeu;
import com.tdevlee.helpers.Proprietee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    public static boolean devMod = false;

    public static void main(String[] args) {
        logger.info("Lancement de l'application");
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