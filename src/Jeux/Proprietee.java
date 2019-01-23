package Jeux;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class Proprietee {
    private static Logger logger = LogManager.getLogger(Proprietee.class);
    public static int mastermindLengh;
    public static int mastermindTry;
    public static int mastermindColor;

    public static int rechercheNbLengh;
    public static int rechercheNbTry;

    public static boolean devMod = false;

    private Proprietee() {
        Properties prop = new Properties();
        Reader in = null;
        try {
            in = new FileReader("src/ressources/config.properties");
            prop.load(in);
        } catch (
                IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        mastermindLengh = Integer.parseInt(prop.getProperty("mastermindLengh"));
        mastermindTry = Integer.parseInt(prop.getProperty("mastermindTry"));
        mastermindColor = Integer.parseInt(prop.getProperty("mastermindColor"));

        rechercheNbLengh = Integer.parseInt(prop.getProperty("rechercheNbLengh"));
        rechercheNbTry = Integer.parseInt(prop.getProperty("rechercheNbTry"));
        if (prop.getProperty("devmod").equals("true")) {
            devMod = true;
        }
    }

    private static Proprietee Proprietee = null;
    public static synchronized Proprietee getInstance() {
        if (Proprietee == null) {
            Proprietee = new Proprietee();
        }
        return Proprietee;
    }
}
