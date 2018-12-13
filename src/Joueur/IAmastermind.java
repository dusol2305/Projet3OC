package Joueur;

import Jeux.Mastermind;

import java.util.ArrayList;
import java.util.List;

public class IAmastermind implements Joueur {
    List Solutions = new ArrayList();
    private String combinaison = "0000";
    private String combinaison2 = "0000";

    @Override
    public String demandeCombinaison() {
        int bienPlace = 0;
        int present = 0;
        char[] combinaisonTemp = combinaison.toCharArray();

        while (combinaison2 != "9999") {
            for (int i = 3; i > -1; i--) {
                if (combinaisonTemp[i] == '9' && i != 0) {
                    combinaisonTemp[i] = '0';
                } else if(combinaisonTemp[i] != '9'){
                    combinaisonTemp[i]++;
                }
            }

            combinaison2 = String.valueOf(combinaisonTemp);

            for (int i = 0; i < combinaison.length(); i++) {
                if (combinaison2.charAt(i) == combinaison.charAt(i)) {
                    bienPlace++;
                    continue;
                }
                for (int j = 0; j < combinaison2.length(); j++) {
                    if (combinaison2.charAt(j) == combinaison.charAt(i)) {
                        present++;
                    }
                }
            }

            if ((bienPlace == Mastermind.getBienPlace()) && present == Mastermind.getPresent()) {
                Solutions.add(combinaison2);
            }
        }
        combinaison = ((String) Solutions.get(1));
        System.out.println("combinaison de l'ia = " + combinaison);
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
