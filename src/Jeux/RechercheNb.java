package Jeux;

import Joueur.Joueur;
import Main.Main;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class RechercheNb implements Jeu {
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private String indice;

    private int essaisRechercheNB;
    private int tailleRechercheNB;

    public RechercheNb(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;

        //code copié
        Properties prop = new Properties();
        Reader in = null;
        try {
            in = new FileReader("src/ressources/config.properties");

            // load a properties file
            prop.load(in);

        } catch (IOException ex) {
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
        //code copié
        essaisRechercheNB = Integer.parseInt(prop.getProperty("rechercheNbessais"));

        System.out.println("Nombre d'éssais : " + essaisRechercheNB);
    }

    @Override
    public void initialisation() {
        combinaison = defenseur.demandeCombinaisonAleatoire();
        if (Main.devMod) {
            System.out.println("Combinaison à trouver : " + combinaison);
        }
    }

    @Override
    public void jouer() {
        indice = this.comparaison(attaquant.demandeCombinaison(), combinaison);
        attaquant.envoyerIndice(indice);
    }

    @Override
    public boolean estFin() {
        if (indice == null){
            return false;
        }
        if (essaisRechercheNB > 0) {
            int i = indice.length() - 1;
            while (i > -1) {
                if (indice.charAt(i) == '=') {
                    i--;
                } else {
                    return false;
                }
            }
            attaquant.affichageResultatPartie(true);
        } else {
            attaquant.affichageResultatPartie(false);
        }
        return true;
    }

    private String comparaison(String combinaisonJoueur, String combinaisonATrouver) {
        String indice;
        StringBuilder indiceTemp = new StringBuilder();
        for (int i = 0; i < combinaisonATrouver.length(); i++) {
            if (combinaisonJoueur.charAt(i) < combinaisonATrouver.charAt(i)) {
                indiceTemp.append("+");
            } else if (combinaisonJoueur.charAt(i) > combinaisonATrouver.charAt(i)) {
                indiceTemp.append("-");
            } else {
                indiceTemp.append("=");
            }
        }
        indice = indiceTemp.toString();
        return indice;
    }
}