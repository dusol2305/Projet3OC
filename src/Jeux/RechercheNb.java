package Jeux;

import Joueur.Joueur;
import Main.Main;

import java.io.*;
import java.util.Properties;

public class RechercheNb implements Jeu {
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private String indice;

    private int essaisRechercheNB;
    private static int tailleRechercheNB;

    public RechercheNb(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;

        essaisRechercheNB = Propriétée.rechercheNbTry;
        tailleRechercheNB = Propriétée.rechercheNbLengh;

        System.out.println("Nombre d'éssais : " + essaisRechercheNB);//dell
    }

    @Override
    public void initialisation() {
        System.out.println("Taille de la combinaison : " + Propriétée.rechercheNbLengh);
        combinaison = defenseur.demandeCombinaisonAleatoire();
        if (Main.devMod) {
            System.out.println("Combinaison à trouver : " + combinaison);
        }
    }

    @Override
    public void jouer() {
        indice = this.comparaison(attaquant.demandeCombinaison(), combinaison);
        attaquant.envoyerIndice(indice);
        essaisRechercheNB--;
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

    public static int getTailleRechercheNB() {
        return tailleRechercheNB;
    }

    public static void setTailleRechercheNB(int tailleRechercheNB) { //dell
        RechercheNb.tailleRechercheNB = tailleRechercheNB;
    }
}