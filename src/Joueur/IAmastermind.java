package Joueur;

import Jeux.Mastermind;

import java.util.LinkedList;
import java.util.List;

public class IAmastermind implements Joueur {
    private List<String> solutions = new LinkedList<>();
    private String combinaison = "0000";

    public IAmastermind() {
        char[] combinaisonTemp = {'0', '0', '0', '0'};

        int i = combinaisonTemp.length - 1;
        while (solutions.size() < 10000) {
            solutions.add(String.valueOf(combinaisonTemp));
            while (i > -1 && combinaisonTemp[i] == '9') {
                combinaisonTemp[i] = '0';
                i--;
            }
            if (i > -1 && combinaisonTemp[i] < '9') {
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
        StringBuilder combinaisonTemp = new StringBuilder();

        for (int i = 4; i > 0; i--) {
            combinaisonTemp.append((int) (Math.random() * 9 + 0));
        }
        combinaison = combinaisonTemp.toString();

        return combinaison;
    }

    @Override
    public void envoyerIndice(String indice) {

        for (int combI = 0; combI < solutions.size(); ) { //retire de la liste des solutions, les solutions qui ne sont pas des possibilitées de réponse.

            String combinaisonATester = solutions.get(combI);

            if (Mastermind.comparaison(combinaisonATester, combinaison).equals(indice)) {
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
