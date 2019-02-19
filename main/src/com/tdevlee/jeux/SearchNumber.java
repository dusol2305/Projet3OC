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
    private boolean validCombination = false;

    private int searchNumberTry;
    private int searchNumberLengh;

    /**
     * Set the player who will be the attacker and the player who will be de denfender.
     *
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

        validCombination = false;
        while (!validCombination) {
            combination = checkCombination(defender.askSecretCombination());
        }

        if (Main.devMod || GamesProperties.devMod) {
            System.out.println("(Combinaison secrète : " + combination + ")");
        }
        logger.debug("Iniitalisation de SearchNumber. Combinaison : " + combination + ". Nombre d'essais : " + searchNumberTry + ". Taille de la combination : " + searchNumberLengh);
    }

    @Override
    public void play() {
        String combinaisonAttaquant = null;

        System.out.println("Il reste " + searchNumberTry + " essais");
        validCombination = false;
        while (!validCombination) {
            combinaisonAttaquant = checkCombination(attacker.askCombination());
        }

        clue = this.comparison(combinaisonAttaquant, combination);
        logger.debug("Indice : " + clue);
        attacker.displayClue(clue);
        searchNumberTry--;
    }

    @Override
    public boolean isEnd() {
        boolean win = true;
        if (clue == null) {
            return false;
        }
        for (int i = clue.length() - 1; i > -1; i--) {
            if (clue.charAt(i) != '=') {
                win = false;
            }
        }
        if (searchNumberTry == 0 || win){
            attacker.displayGameResult(win);
            if (!win){
                System.out.println("La combinaison était : " + combination);
            }
            return true;
        }
        return false;
    }

    /**
     * Do the comparison to set the clue to find the combination.
     *
     * @param attackerCombination   combination enter by the attacking player.
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
     * @param combination combination enter by the player
     * @return the combination when it's valid
     */
    private String checkCombination(String combination) {
        boolean incorectColor = false;
        validCombination = true;
        if (combination.length() != searchNumberLengh) {
            System.out.println("Taille de la combination incorrecte. Taille de la combination à entrer : " + searchNumberLengh);
            logger.warn("Taille de la combination saisie incorrecte");
            validCombination = false;
        }

        for (int i = 0; i < combination.length(); i++) {
            if (combination.charAt(i) > '9' || combination.charAt(i) < '0') {
                incorectColor = true;
                validCombination = false;
            }
        }

        if (incorectColor) {
            logger.warn("Couleur saisie incorrecte");
            System.out.println("Couleur incorrecte. Couleurs de la combination à entrer comprise entre 0 et 9");
        }

        return combination;
    }
}