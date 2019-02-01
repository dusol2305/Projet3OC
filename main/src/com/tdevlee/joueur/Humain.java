package com.tdevlee.joueur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Humain implements Joueur {
    private static Logger logger = LogManager.getLogger(Humain.class);
    private String combinaison;

    @Override
    public String demandeCombinaison() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Proposition : ");
        combinaison = sc.nextLine();
        logger.debug("Humain saisie la combinaison : " + combinaison);
        return combinaison;
    }

    @Override
    public String demandeCombinaisonAleatoire() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez une combinaison : ");
        combinaison = sc.nextLine();
        logger.debug("Humain saisie la combinaison : " + combinaison);
        return combinaison;
    }

    @Override
    public void envoyerIndice(String indice) {
        System.out.println("------------------------------------------------------");
        System.out.println("Proposition : " + combinaison + " -> Réponse : " + indice);
        System.out.println("------------------------------------------------------");
    }

    @Override
    public void affichageResultatPartie(boolean aGagne) {
        if (aGagne) {
            logger.info("Humain à gagné");
            System.out.println("Gagné");
        } else {
            logger.info("Humain à perdu");
            System.out.println("Perdu");
        }
    }
}
