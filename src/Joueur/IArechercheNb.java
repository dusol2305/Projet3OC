package Joueur;

import Jeux.Mastermind;
import Jeux.Proprietee;
import Jeux.RechercheNb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IArechercheNb implements Joueur {
    private static Logger logger = LogManager.getLogger(IArechercheNb.class);
    private String combinaison;

    public IArechercheNb () {

        RechercheNb.setTailleRechercheNB(Proprietee.rechercheNbLengh);
        StringBuilder combinaisonTemp = new StringBuilder();
        for (int i = 0; i < RechercheNb.getTailleRechercheNB(); i++) {
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

        for (int i = RechercheNb.getTailleRechercheNB(); i > 0; i--) {
            combinaisonTemp.append((int) (Math.random() * 9 + 0));
        }

        combinaison = combinaisonTemp.toString();
        return combinaison;
    }

    @Override
    public void envoyerIndice(String indice) {
        System.out.println("Proposition : " + combinaison + " -> Réponse : " + indice + "\n");

        char[] iaComb = combinaison.toCharArray();
        int i = indice.length() - 1;
        while (i > -1) {
            if (indice.charAt(i) == '-') {
                iaComb[i]--;
                combinaison = String.valueOf(iaComb);
            } else if (indice.charAt(i) == '+') {
                iaComb[i]++;
                combinaison = String.valueOf(iaComb);
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