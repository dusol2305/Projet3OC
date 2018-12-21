package Joueur;

import Jeux.Mastermind;

import java.util.ArrayList;
import java.util.List;

public class IAmastermind implements Joueur {
    private List<String> solutions = new ArrayList();
    private String combinaison = "0000";
    private String combinaison2 = "0000";

    public IAmastermind() {
        char[] combinaisonTemp = {'0', '0', '0', '0'};
        int i = combinaisonTemp.length - 1;
        while (solutions.size() <= 10000) {
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
        int bienPlace = 0;
        int present = 0;
        int random;

        for (int combI = 0; combI < solutions.size();){ //retire de la liste des solutions, les solutions qui ne sont pas des possibilitées de réponse.

            combinaison2 = solutions.get(combI);

            for (int i = 0; i < combinaison.length(); i++) {
                if (combinaison2.charAt(i) == combinaison.charAt(i)) {
                    bienPlace++;
                    continue;
                }
                for (int j = 0; j < combinaison2.length(); j++) {
                    if (combinaison2.charAt(j) == combinaison.charAt(j)) {
                        present++;
                    }
                }
            }

            if ((bienPlace != Mastermind.getBienPlace()) || present != Mastermind.getPresent()) {
                solutions.remove(combinaison2);
            } else {
                combI++;
            }
            bienPlace = 0;
            present = 0;
        }
        solutions.remove(combinaison);
        random = (int)(Math.random() * solutions.size()-1 + 0);
        System.out.println("\nsolution.size = " + solutions.size() + "\nrandom : " + random + "\n"); //dell
        combinaison = solutions.get(random);

        System.out.print("Proposition : " + combinaison + " -> ");
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
