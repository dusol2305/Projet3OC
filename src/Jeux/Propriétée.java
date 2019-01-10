package Jeux;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class Propriétée {
    public static int mastermindLengh;
    public static int mastermindTry;
    public static int mastermindColor;

    public static int rechercheNbLengh;
    public static int rechercheNbTry;

    public Propriétée() {
        Properties prop = new Properties();
        Reader in = null;
        try {
            in = new FileReader("src/ressources/config.properties");

            // load a properties file
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
    }
}
