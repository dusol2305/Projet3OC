package com.tdevlee.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class GamesProperties {
    private static Logger logger = LogManager.getLogger(GamesProperties.class);
    public static int mastermindLengh;
    public static int mastermindTry;
    public static int mastermindColor;

    public static int rechercheNbLengh;
    public static int rechercheNbTry;

    public static boolean devMod = false;

    private GamesProperties() {
        Properties prop = new Properties();
        Reader in = null;
        try {
            in = new FileReader("main/src/ressources/config.properties");
            prop.load(in);
        } catch (IOException ex) {
            logger.error("Erreur lors de l'ouverture du fichier de propriétée");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("Impossible de fermé le fichier de propriétée");
                }
            }
        }

        mastermindLengh = Integer.parseInt(prop.getProperty("mastermindLengh"));
        mastermindTry = Integer.parseInt(prop.getProperty("mastermindTry"));
        mastermindColor = Integer.parseInt(prop.getProperty("mastermindColor"));

        rechercheNbLengh = Integer.parseInt(prop.getProperty("rechercheNbLengh"));
        rechercheNbTry = Integer.parseInt(prop.getProperty("rechercheNbTry"));

        if (mastermindColor < 4){ //Set max or min value if mastermindColor is not between 4 and 10
            mastermindColor = 4;
        } else if (mastermindColor > 10) {
            mastermindColor = 10;
        }

        if (prop.getProperty("devmod").equals("true")) {
            devMod = true;
        }
    }

    private static GamesProperties GamesProperties = null;
    public static synchronized GamesProperties getInstance() {
        if (GamesProperties == null) {
            GamesProperties = new GamesProperties();
        }
        return GamesProperties;
    }
}
