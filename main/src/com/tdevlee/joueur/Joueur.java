package com.tdevlee.joueur;

public interface Joueur {
    String demandeCombinaison();
    String demandeCombinaisonAleatoire();
    void envoyerIndice(String indice);
    void affichageResultatPartie (boolean aGagne);

}
