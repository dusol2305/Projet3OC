package com.tdevlee.joueur;

import com.tdevlee.jeux.Mastermind;
import com.tdevlee.helpers.GamesProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>AI for the mastermind game.</b>
 */
public class AIMastermind implements Player {
    private static Logger logger = LogManager.getLogger(AIMastermind.class);
    private List<String> solutions = new LinkedList<>();
    private String combination;

    private int mastermindLengh = GamesProperties.mastermindLengh;
    private int mastermindColor = GamesProperties.mastermindColor;

    /**
     * Add all the possible solutions of the mastermind game in the List of String "solutions".
     */
    public AIMastermind() {
        char[] tempCombination = new char[mastermindLengh];
        for (int i = 0; i < tempCombination.length; i++) {
            tempCombination[i] = '0';
        }

        int i = tempCombination.length - 1;
        int solutionsLengh = ((int) Math.pow(mastermindColor, mastermindLengh));
        while (solutions.size() < solutionsLengh) {
            solutions.add(String.valueOf(tempCombination));
            while (i > -1 && tempCombination[i] == mastermindColor + 47) {
                tempCombination[i] = '0';
                i--;
            }
            if (i > -1 && tempCombination[i] < mastermindColor + 47) {
                tempCombination[i]++;
            }
            i = tempCombination.length - 1;
        }
        logger.debug("Nombre de solution possible : " + solutions.size());
    }

    @Override
    public String askCombination() {
        combination = solutions.get((int) (Math.random() * solutions.size() - 1 + 0));
        return combination;
    }

    @Override
    public String askSecretCombination() {
        return askCombination();
    }

    @Override
    public void displayClue(String clue) {
        int jeuBienPlace;
        int jeuPresent;
        Pattern p = Pattern.compile("(\\d).+(\\d).+");
        Matcher m = p.matcher(clue);
        m.find();
        jeuPresent = Integer.parseInt(m.group(1));
        jeuBienPlace = Integer.parseInt(m.group(2));

        for (int combI = 0; combI < solutions.size(); ) { //retire de la liste des solutions, les solutions qui ne sont pas des possibilitées de réponse.

            String combinaisonATester = solutions.get(combI);

            int[] comparaison = Mastermind.comparison(combinaisonATester, combination);

            if (comparaison[0] == jeuPresent && comparaison[1] == jeuBienPlace) {
                combI++;
            } else {
                solutions.remove(combinaisonATester);
            }
        }
        logger.debug("Nombre de solutions restante : " + solutions.size());
        solutions.remove(combination);
        System.out.println("Proposition : " + combination + " -> " + clue);
    }

    @Override
    public void displayGameResult(boolean win) {
        if (win) {
            logger.info("AIMastermind a gagné");
            System.out.println("AIMastermind Gagné");
        } else {
            logger.info("AIMastermind a perdu");
        }
    }
}
