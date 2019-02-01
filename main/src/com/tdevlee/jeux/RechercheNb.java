package com.tdevlee.jeux;

import com.tdevlee.helpers.Proprietee;
import com.tdevlee.joueur.Joueur;
import com.tdevlee.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RechercheNb implements Jeu {
    private static Logger logger = LogManager.getLogger(RechercheNb.class);
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private String indice;
    boolean combinaisonAttaquantValide = false;

    private int rechercheNbTry;
    private int rechercheNbLengh;

    public RechercheNb(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;

        rechercheNbTry = Proprietee.rechercheNbTry;
        rechercheNbLengh = Proprietee.rechercheNbLengh;
    }

    @Override
    public void initialisation() {
        System.out.println("Nombre d'éssais : " + rechercheNbTry);
        System.out.println("Taille de la combinaison : " + Proprietee.rechercheNbLengh);

        combinaisonAttaquantValide = false;
        while (!combinaisonAttaquantValide) {
            combinaison = verificationCombinaison(defenseur.demandeCombinaisonAleatoire());
        }

        if (Main.devMod) {
            System.out.println("Combinaison à trouver : " + combinaison);
        }
        logger.debug("Iniitalisation de RechercheNb. Combinaison : " + combinaison + ". Nombre d'essais : " + rechercheNbTry + ". Taille de la combinaison : " + rechercheNbLengh);
    }

    @Override
    public void jouer() {
        String combinaisonAttaquant = null;

        combinaisonAttaquantValide = false;
        while (!combinaisonAttaquantValide) {
            combinaisonAttaquant = verificationCombinaison(attaquant.demandeCombinaison());
        }

        indice = this.comparaison(combinaisonAttaquant, combinaison);
        logger.debug("Indice : " + indice);
        attaquant.envoyerIndice(indice);
        rechercheNbTry--;
    }

    @Override
    public boolean estFin() {
        if (indice == null) {
            return false;
        }
        if (rechercheNbTry >= 0) {
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

    String verificationCombinaison(String combinaisonAttaquant) {
        combinaisonAttaquantValide = true;
        if (combinaisonAttaquant.length() > rechercheNbLengh){
            System.out.println("taille de la combinaison entrée supérieur à la taille requise. Taille requise : " + rechercheNbLengh);
            logger.warn("Taille de la combinaison entrée supérieur à la taille requise");
            combinaisonAttaquantValide = false;
        } else if (combinaisonAttaquant.length() < rechercheNbLengh){
            logger.warn("Taille de la combinaison entrée inférieur à la taille requise.");
            System.out.println("taille de la combinaison entrée inférieur à la taille requise. Taille requise : " + rechercheNbLengh);
            combinaisonAttaquantValide = false;
        }
        return combinaisonAttaquant;
    }
}