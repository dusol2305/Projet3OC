package com.tdevlee.jeux;

import com.tdevlee.helpers.GamesProperties;
import com.tdevlee.joueur.Player;
import com.tdevlee.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <b>Search Number game</b>
 */
public class SearchNumber implements Game {
    private static Logger logger = LogManager.getLogger(SearchNumber.class);
    private Player attacker;
    private Player defender;
    private String combination;
    private String clue;
    private boolean validAttackerCombination = false;

    private int searchNumberTry;
    private int searchNumberLengh;

    /**
     * Set the player who will be the attacker and the player who will be de denfender.
     * @param attacker attacking player
     * @param defender defender player
     */
    public SearchNumber(Player attacker, Player defender) {
        this.attacker = attacker;
        this.defender = defender;

        searchNumberTry = GamesProperties.rechercheNbTry;
        searchNumberLengh = GamesProperties.rechercheNbLengh;
    }

    @Override
    public void initialization() {
//        System.out.println("Nombre d'éssais : " + searchNumberTry);
//        System.out.println("Taille de la combination : " + GamesProperties.rechercheNbLengh);

        validAttackerCombination = false;
        while (!validAttackerCombination) {
            combination = checkCombination(defender.askSecretCombination());
        }

        if (Main.devMod) {
            System.out.println("(Combinaison secrète : " + combination + ")");
        }
        logger.debug("Iniitalisation de SearchNumber. Combinaison : " + combination + ". Nombre d'essais : " + searchNumberTry + ". Taille de la combination : " + searchNumberLengh);
    }

    @Override
    public void play() {
        String combinaisonAttaquant = null;

        System.out.println("Il reste " + searchNumberTry + " essais");
        validAttackerCombination = false;
        while (!validAttackerCombination) {
            combinaisonAttaquant = checkCombination(attacker.askCombination());
        }

        clue = this.comparison(combinaisonAttaquant, combination);
        logger.debug("Indice : " + clue);
        attacker.displayClue(clue);
        searchNumberTry--;
    }

    @Override
    public boolean isEnd() {
        if (clue == null) {
            return false;
        }
        if (searchNumberTry >= 0) {
            int i = clue.length() - 1;
            while (i > -1) {
                if (clue.charAt(i) == '=') {
                    i--;
                } else {
                    return false;
                }
            }
            attacker.displayGameResult(true);
        } else {
            attacker.displayGameResult(false);
        }
        return true;
    }

    /**
     * Do the comparison to set the clue to find the combination.
     * @param attackerCombination combination enter by the attacking player.
     * @param gameSecretCombination secret combination to find.
     * @return the clue send to the player to find the combination.
     */
    private String comparison(String attackerCombination, String gameSecretCombination) {
        String clue;
        StringBuilder tempClue = new StringBuilder();
        for (int i = 0; i < gameSecretCombination.length(); i++) {
            if (attackerCombination.charAt(i) < gameSecretCombination.charAt(i)) {
                tempClue.append("+");
            } else if (attackerCombination.charAt(i) > gameSecretCombination.charAt(i)) {
                tempClue.append("-");
            } else {
                tempClue.append("=");
            }
        }
        clue = tempClue.toString();
        return clue;
    }

    /**
     * Check if the combination entered by the player is valid.
     * @param playerCombination combination enter by the player
     * @return the combination when it's valid
     */
    private String checkCombination(String playerCombination) {
        validAttackerCombination = true;
        if (playerCombination.length() > searchNumberLengh){
            System.out.println("taille de la combination entrée supérieur à la taille requise. Taille requise : " + searchNumberLengh);
            logger.warn("Taille de la combination entrée supérieur à la taille requise");
            validAttackerCombination = false;
        } else if (playerCombination.length() < searchNumberLengh){
            logger.warn("Taille de la combination entrée inférieur à la taille requise.");
            System.out.println("taille de la combination entrée inférieur à la taille requise. Taille requise : " + searchNumberLengh);
            validAttackerCombination = false;
        }
        return playerCombination;
    }
}