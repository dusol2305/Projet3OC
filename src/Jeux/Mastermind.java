package Jeux;

import Joueur.Joueur;

public class Mastermind implements Jeu {
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private String indice;

    public Mastermind(Joueur attaquant, Joueur defenseur) {
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
    public boolean estFin(String indice) {
        return false;
    }
}
