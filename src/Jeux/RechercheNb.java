package Jeux;

import Joueur.Joueur;

public class RechercheNb implements Jeu {
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;

    public RechercheNb(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;
    }

    @Override
    public void initialisation() {
        combinaison = defenseur.demandeCombinaisonAleatoire();
    }

    @Override
    public void jouer() {

    }

    @Override
    public boolean estFin() {
        return false;
    }

    private String comparaison(String combinaisonJoueur, String combinaisonATrouver) {
        String indice = "";
        for (int i = 0; i < combinaisonATrouver.length(); i++) {
            if (combinaisonJoueur.charAt(i) < combinaisonATrouver.charAt(i)) {
                indice += "+";
            } else if (combinaisonJoueur.charAt(i) > combinaisonATrouver.charAt(i)) {
                indice += "-";
            } else {
                indice += "=";
            }
        }
        return indice;
    }
}
