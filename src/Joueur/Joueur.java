package Joueur;

public interface Joueur {
    String demandeCombinaison(String indice);
    String demandeCombinaisonAleatoire();
    void affichageIndice(String indice);
    void affichageResultatPartie (boolean aGagne);

}
