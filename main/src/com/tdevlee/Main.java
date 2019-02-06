package com.tdevlee;

import com.tdevlee.jeux.GameMenu;
import com.tdevlee.helpers.GamesProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    public static boolean devMod = false;

    public static void main(String[] args) {
        logger.info("Lancement de l'application");
        GamesProperties.getInstance();
        if (args.length == 1) {
            if ("-dev".equals(args[0]) || GamesProperties.devMod) {
                devMod = true;
            }
        }
        GameMenu menu = new GameMenu();
        menu.run();
    }
}