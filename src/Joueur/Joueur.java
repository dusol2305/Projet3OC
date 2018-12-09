package Joueur;

public interface Joueur {
    String demandeCombinaison();
    String demandeCombinaisonAleatoire();
    void envoyerIndice(String indice);
    void affichageResultatPartie (boolean aGagne);

}
