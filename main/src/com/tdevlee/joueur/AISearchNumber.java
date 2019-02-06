package com.tdevlee.joueur;

import com.tdevlee.helpers.GamesProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <b>AI for the Search Number game.</b>
 */
public class AISearchNumber implements Player {
    private static Logger logger = LogManager.getLogger(AISearchNumber.class);
    private String combination;
    private int searchNumberLengh = GamesProperties.rechercheNbLengh;

    /**
     * Creat a combination for the first time the game need a combination.
     */
    public AISearchNumber() {
        StringBuilder combinaisonTemp = new StringBuilder();
        for (int i = 0; i < searchNumberLengh; i++) {
            combinaisonTemp.append(5);
        }
        combination = combinaisonTemp.toString();
    }


    @Override
    public String askCombination() {
        return combination;
    }

    @Override
    public String askSecretCombination() {
        StringBuilder combinaisonTemp = new StringBuilder();
        String combinaison;

        for (int i = searchNumberLengh; i > 0; i--) {
            combinaisonTemp.append((int) (Math.random() * 9 + 0));
        }
        combinaison = combinaisonTemp.toString();
        logger.debug("AIMastermind combination aléatoire : " + combinaison);
        return combinaison;
    }

    @Override
    public void displayClue(String clue) {
        System.out.println("Proposition : " + combination + " -> Réponse : " + clue);

        char[] iaCombinaison = combination.toCharArray();
        int i = clue.length() - 1;
        while (i > -1) {
            if (clue.charAt(i) == '-') {
                iaCombinaison[i]--;
                combination = String.valueOf(iaCombinaison);
            } else if (clue.charAt(i) == '+') {
                iaCombinaison[i]++;
                combination = String.valueOf(iaCombinaison);
            }
            i--;
        }
    }

    @Override
    public void displayGameResult(boolean win) {
        if (win) {
            logger.info("IARechercheNb a gagné");
            System.out.println("AISearchNumber Gagné");
        } else {
            logger.info("IARechercheNb a perdu");
            System.out.println("AISearchNumber Perdu");
        }
    }
}