package com.tdevlee.jeux;

import com.tdevlee.helpers.GamesProperties;
import com.tdevlee.joueur.Player;
import com.tdevlee.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * <b>Mastermind game.</b>
 */
public class Mastermind implements Game {
    private static Logger logger = LogManager.getLogger(Mastermind.class);
    private Player attacker;
    private Player defender;
    private String combination;
    private String clue;
    private boolean validCombination = true;
    private int[] comparisonResult = new int[2];

    private int mastermindTry;
    private int mastermindColors;
    private int mastermindLengh;

    /**
     * Set the attacker and the defencer.
     * @param attacker Player who will play as attacker
     * @param defender Player who will play as defender
     */
    public Mastermind(Player attacker, Player defender) {
        this.attacker = attacker;
        this.defender = defender;

        mastermindTry = GamesProperties.mastermindTry;
        mastermindColors = GamesProperties.mastermindColor;
        mastermindLengh = GamesProperties.mastermindLengh;
    }

    @Override
    public void initialization() {
//        System.out.println("Nombre d'essais : " + mastermindTry);
//        System.out.println("Nombre de couleurs : " + mastermindColors);
//        System.out.println("Taille de la combination : " + mastermindLengh);

        validCombination = false;
        while (!validCombination) {
            combination = this.checkCombination(defender.askSecretCombination());
        }

        if (Main.devMod || GamesProperties.devMod) {
            System.out.println("(Combinaison secrète : " + combination + ")");
        }
        logger.debug("Iniitalisation de mastermind. Combinaison : " + combination + ". Nombre de couleurs : " + mastermindColors + ". Nombre d'essais : " + mastermindTry);
    }

    @Override
    public void play() {
        StringBuilder tempClue = new StringBuilder();

        System.out.println("Il reste " + mastermindTry + " essais");
        String playerCombination = null;

        validCombination = false;
        while (!validCombination) {
            playerCombination = this.checkCombination(attacker.askCombination());
        }
        logger.info("combination du joueur : " + playerCombination);

        comparisonResult = Mastermind.comparison(playerCombination, combination);
        logger.info("resultat de la comparison : " + comparisonResult[0] + comparisonResult[1]);
        tempClue.append(comparisonResult[0] + " présent(s), " + comparisonResult[1] + " bien placé(s)");
        clue = tempClue.toString();
        attacker.displayClue(clue);
        logger.debug("clue : " + clue +" | combination joueur : " + playerCombination + " | combination du jeu : "+ combination);
        mastermindTry--;
    }

    @Override
    public boolean isEnd() {
        boolean win = true;
        if (clue == null) {
            return false;
        }
        if (comparisonResult[1] != combination.length()) {
            win = false;
        }
        if (mastermindTry == 0 || win == true){
            attacker.displayGameResult(win);
            if (win == false){
                System.out.println("La combinaison était : " + combination);
            }
            return true;
        }
        return false;
    }

    /**
     * Do the comparison to set the clue to find the combination.
     * @param attackerCombination combination enter by the attacking player.
     * @param gameSecretCombination secret combination to find.
     * @return the clue send to the player to find the combination.
     */
    public static int[] comparison(String attackerCombination, String gameSecretCombination) {
        int present = 0;
        int wellPlaced = 0;
        int[] comparisonClue = new int[2];

        Set<Character> combinationWithoutDuplicatedNumber = new HashSet<>();
        for (int i = 0; i < attackerCombination.length(); i++) {
            combinationWithoutDuplicatedNumber.add(attackerCombination.charAt(i));
        }

        for (int i = 0; i < gameSecretCombination.length(); i++) {
            if (attackerCombination.charAt(i) == gameSecretCombination.charAt(i)) {
                wellPlaced++;
                combinationWithoutDuplicatedNumber.remove(gameSecretCombination.charAt(i));

            }
        }

        for (int i = 0; i < gameSecretCombination.length(); i++) {
            if (combinationWithoutDuplicatedNumber.contains(gameSecretCombination.charAt(i))) {
                present++;
                combinationWithoutDuplicatedNumber.remove(gameSecretCombination.charAt(i));
            }
        }

        comparisonClue[0] = present;
        comparisonClue[1] = wellPlaced;
        return comparisonClue;
    }

    /**
     * Check if the combination entered by the player is valid.
     * @param combination combination enter by the player
     * @return the combination when it's valid
     */
    private String checkCombination(String combination) {
        int validColor = mastermindColors - 1;
        boolean incorectColor = false;
        validCombination = true;
        if (combination.length() != mastermindLengh) {
            System.out.println("Taille de la combination incorrecte. Taille de la combination à entrer : " + mastermindLengh);
            logger.warn("Taille de la combination saisie incorrecte");
            validCombination = false;
        }

        for (int i = 0; i < combination.length(); i++) {
            if (combination.charAt(i) > mastermindColors + 47 || combination.charAt(i) < '0') {
                incorectColor = true;
                validCombination = false;
            }
        }

        if (incorectColor) {
            logger.warn("Couleur saisie incorrecte");
            System.out.println("Couleur incorrecte. Couleurs de la combination à entrer comprise entre 0 et " + validColor);
        }

        return combination;
    }
}