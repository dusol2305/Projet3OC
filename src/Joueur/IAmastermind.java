package Joueur;

import Jeux.Mastermind;
import Jeux.Propriétée;

import java.util.LinkedList;
import java.util.List;

public class IAmastermind implements Joueur {
    private List<String> solutions = new LinkedList<>();
    private String combinaison = "0000";

    private int mastermindLengh = Propriétée.mastermindLengh;
    private int mastermindColor = Propriétée.mastermindColor;

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
        System.out.println(solutions + "\ntaille solution : " + solutions.size());
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

        for (int combI = 0; combI < solutions.size(); ) { //retire de la liste des solutions, les solutions qui ne sont pas des possibilitées de réponse.

            String combinaisonATester = solutions.get(combI);

            int[] comparaison = Mastermind.comparaison(combinaisonATester, combinaison);

            if (comparaison[0] == Mastermind.getPresent() && comparaison[1] == Mastermind.getBienPlace()) {
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
