package com.tdevlee.Joueur;

import com.tdevlee.Jeux.Mastermind;
import com.tdevlee.Jeux.Proprietee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IAmastermind implements Joueur {
    private static Logger logger = LogManager.getLogger(IAmastermind.class);
    private List<String> solutions = new LinkedList<>();
    private String combinaison = "0000";

    private int mastermindLengh = Proprietee.mastermindLengh;
    private int mastermindColor = Proprietee.mastermindColor;

    public IAmastermind() {
        char[] combinaisonTemp = new char[mastermindLengh];
        for (int i = 0; i < combinaisonTemp.length; i++) {
            combinaisonTemp[i] = '0';
        }

        int i = combinaisonTemp.length - 1;
        int tailleSlutions = ((int) Math.pow(mastermindColor, mastermindLengh));
        while (solutions.size() < tailleSlutions) {
            solutions.add(String.valueOf(combinaisonTemp));
            while (i > -1 && combinaisonTemp[i] == mastermindColor + 47) {
                combinaisonTemp[i] = '0';
                i--;
            }
            if (i > -1 && combinaisonTemp[i] < mastermindColor + 47) {
                combinaisonTemp[i]++;
            }
            i = combinaisonTemp.length - 1;
        }
    }

    @Override
    public String demandeCombinaison() {
        combinaison = solutions.get((int) (Math.random() * solutions.size() - 1 + 0));
        return combinaison;
    }

    @Override
    public String demandeCombinaisonAleatoire() {
        return demandeCombinaison();
    }

    @Override
    public void envoyerIndice(String indice) {
        int jeuBienPlace;
        int jeuPresent;
        Pattern p = Pattern.compile("(\\d).+(\\d).+");
        Matcher m = p.matcher(indice);
        m.find();
        jeuPresent = Integer.parseInt(m.group(1));
        jeuBienPlace = Integer.parseInt(m.group(2));

        for (int combI = 0; combI < solutions.size(); ) { //retire de la liste des solutions, les solutions qui ne sont pas des possibilitées de réponse.

            String combinaisonATester = solutions.get(combI);

            int[] comparaison = Mastermind.comparaison(combinaisonATester, combinaison);

            if (comparaison[0] == jeuPresent && comparaison[1] == jeuBienPlace) {
                combI++;
            } else {
                solutions.remove(combinaisonATester);
            }
        }
        solutions.remove(combinaison);
        System.out.print("Proposition : " + combinaison + " -> ");
        System.out.println(indice);
    }

    @Override
    public void affichageResultatPartie(boolean aGagne) {
        if (aGagne) {
            System.out.println("IAmastermind Gagné");
        } else {
            System.out.println("IAmastermind Perdu");
        }
    }
}
