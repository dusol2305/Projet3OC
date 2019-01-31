package com.tdevlee.Joueur;

import com.tdevlee.Jeux.Proprietee;
import com.tdevlee.Jeux.RechercheNb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IArechercheNb implements Joueur {
    private static Logger logger = LogManager.getLogger(IArechercheNb.class);
    private String combinaison;
    private int rechercheNBLengh = Proprietee.rechercheNbLengh;

    public IArechercheNb () {

        StringBuilder combinaisonTemp = new StringBuilder();
        for (int i = 0; i < rechercheNBLengh; i++) {
            combinaisonTemp.append(5);
        }
        combinaison = combinaisonTemp.toString();
    }

    @Override
    public String demandeCombinaison() {
        return combinaison;
    }

    @Override
    public String demandeCombinaisonAleatoire() {
        StringBuilder combinaisonTemp = new StringBuilder();
        String combinaison;

        for (int i = rechercheNBLengh; i > 0; i--) {
            combinaisonTemp.append((int) (Math.random() * 9 + 0));
        }

        combinaison = combinaisonTemp.toString();
        return combinaison;
    }

    @Override
    public void envoyerIndice(String indice) {
        System.out.println("Proposition : " + combinaison + " -> Réponse : " + indice + "\n");

        char[] iaCombinaison = combinaison.toCharArray();
        int i = indice.length() - 1;
        while (i > -1) {
            if (indice.charAt(i) == '-') {
                iaCombinaison[i]--;
                combinaison = String.valueOf(iaCombinaison);
            } else if (indice.charAt(i) == '+') {
                iaCombinaison[i]++;
                combinaison = String.valueOf(iaCombinaison);
            }
            i--;
        }
    }

    @Override
    public void affichageResultatPartie(boolean aGagne) {
        if (aGagne) {
            System.out.println("IArechercheNb Gagné");
        } else {
            System.out.println("IArechercheNb Perdu");
        }
    }
}