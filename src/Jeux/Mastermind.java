package Jeux;

import Joueur.Joueur;
import Main.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Mastermind implements Jeu {
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private String indice;
    private int essaisMastermind = 15;
    private static int bienPlace = 0;
    private static int present = 0;


    public Mastermind(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;
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
        if (MenuJeu.isDuel()) {
            indice = this.comparaison(defenseur.demandeCombinaison(), combinaison);
            defenseur.envoyerIndice(indice);
        }
    }

    @Override
    public boolean estFin() {
        if (essaisMastermind > 0) {
            if (bienPlace == combinaison.length()) {
                attaquant.affichageResultatPartie(true);
                return false;
            }
        } else {
            attaquant.affichageResultatPartie(false);
            return false;
        }
        return true;
    }

    private String comparaison(String combinaisonJoueur, String combinaisonATrouver) {
        bienPlace = 0;
        present = 0;
        String indice;
        StringBuilder indiceTemp = new StringBuilder();
        for (int i = 0; i < combinaisonATrouver.length(); i++) {
            if (combinaisonJoueur.charAt(i) == combinaisonATrouver.charAt(i)) {
                bienPlace++;
                continue;
            }
            for (int j = 0; j < combinaisonJoueur.length(); j++) {
                if (combinaisonJoueur.charAt(j) == combinaisonATrouver.charAt(i)) {
                    present++;
                }
            }
        }
        indiceTemp.append(present + " présent, " + bienPlace + " bien placé");
        indice = indiceTemp.toString();
        return indice;
    }

    public static int getBienPlace() {
        return bienPlace;
    }

    public static int getPresent() {
        return present;
    }
}
