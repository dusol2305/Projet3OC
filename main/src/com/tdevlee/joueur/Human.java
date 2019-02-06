package com.tdevlee.joueur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * <b>Use for human player in a game.</b>
 */
public class Human implements Player {
    private static Logger logger = LogManager.getLogger(Human.class);
    private String combination;

    @Override
    public String askCombination() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Proposition : ");
        combination = sc.nextLine();
        logger.debug("Humain saisie la combination : " + combination);
        return combination;
    }

    @Override
    public String askSecretCombination() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez une combination : ");
        combination = sc.nextLine();
        logger.debug("Humain saisie la combination : " + combination);
        return combination;
    }

    @Override
    public void displayClue(String clue) {
        System.out.println("Proposition : " + combination + " -> Réponse : " + clue);
    }

    @Override
    public void displayGameResult(boolean win) {
        if (win) {
            logger.info("Human à gagné");
            System.out.println("Gagné");
        } else {
            logger.info("Human à perdu");
            System.out.println("Perdu");
        }
    }
}
