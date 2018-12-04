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
        indice = this.comparaison(attaquant.demandeCombinaison(getIndice()), combinaison);
        attaquant.affichageIndice(indice);
    }

    @Override
    public boolean estFin(String indice) {
        return true;
    }

    public String getIndice() {
        return indice;
    }

    private String comparaison(String combinaisonJoueur, String combinaisonATrouver) {
        int bienPlace = 0;
        int present = 0;
        String indice;
        StringBuilder indiceTemp = new StringBuilder();
        for (int i = 0; i < combinaisonATrouver.length() - 1; i++) {
            if (combinaisonJoueur.charAt(i) == combinaisonATrouver.charAt(i)) {
                bienPlace++;
            }
            for (int j = 0; j < combinaisonJoueur.length() - 1; j++) {
                if (combinaisonJoueur.charAt(i) == combinaisonATrouver.charAt(j) && i != j) {
                    present++;
                }
            }
        }
        indiceTemp.append(present + " présent, " + bienPlace + " bien placé");
        indice = indiceTemp.toString();
        return indice;
    }
}
