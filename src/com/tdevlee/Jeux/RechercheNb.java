package com.tdevlee.Jeux;

import com.tdevlee.Joueur.Joueur;
import com.tdevlee.Main.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RechercheNb implements Jeu {
    private static Logger logger = LogManager.getLogger(RechercheNb.class);
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private String indice;
    boolean combinaisonAttaquantValide = false;

    private int essaisRechercheNB;
    private int tailleRechercheNB;

    public RechercheNb(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;

        essaisRechercheNB = Proprietee.rechercheNbTry;
        tailleRechercheNB = Proprietee.rechercheNbLengh;

        System.out.println("Nombre d'éssais : " + essaisRechercheNB);//dell
    }

    @Override
    public void initialisation() {
        System.out.println("Taille de la combinaison : " + Proprietee.rechercheNbLengh);
        combinaison = defenseur.demandeCombinaisonAleatoire();
        if (Main.devMod) {
            System.out.println("Combinaison à trouver : " + combinaison);
        }
    }

    @Override
    public void jouer() {
        String combinaisonAttaquant = null;

        combinaisonAttaquantValide = false;
        while (!combinaisonAttaquantValide) {
            combinaisonAttaquant = verificationCombinaisonAttaquant(attaquant.demandeCombinaison());
        }

        indice = this.comparaison(combinaisonAttaquant, combinaison);
        attaquant.envoyerIndice(indice);
        essaisRechercheNB--;
    }

    @Override
    public boolean estFin() {
        if (indice == null) {
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

    String verificationCombinaisonAttaquant(String combinaisonAttaquant) {
        combinaisonAttaquantValide = true;
        if (combinaisonAttaquant.length() > tailleRechercheNB){
            System.out.println("taille de la combinaison entrée supérieur à la taille requise. Taille requise : " + tailleRechercheNB);
            combinaisonAttaquantValide = false;
        } else if (combinaisonAttaquant.length() < tailleRechercheNB){
            System.out.println("taille de la combinaison entrée inférieur à la taille requise. Taille requise : " + tailleRechercheNB);
            combinaisonAttaquantValide = false;
        }
        return combinaisonAttaquant;
    }
}