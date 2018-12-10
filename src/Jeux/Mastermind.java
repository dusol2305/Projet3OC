package Jeux;

import Joueur.Joueur;

public class Mastermind implements Jeu {
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private String indice;
    private int bienPlace = 0;

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
        indice = this.comparaison(attaquant.demandeCombinaison(), combinaison);
        attaquant.envoyerIndice(indice);
        if (MenuJeu.isDuel()){
            indice = this.comparaison(defenseur.demandeCombinaison(), combinaison);
            defenseur.envoyerIndice(indice);
        }
    }

    @Override
    public boolean estFin() {
        if (bienPlace == combinaison.length()) {
            return false;
        }
        return true;
    }

    private String comparaison(String combinaisonJoueur, String combinaisonATrouver) {
        bienPlace = 0;
        int present = 0;
        String indice;
        StringBuilder indiceTemp = new StringBuilder();
        for (int i = 0; i < combinaisonATrouver.length(); i++) {
            if (combinaisonJoueur.charAt(i) == combinaisonATrouver.charAt(i)) {
                bienPlace++;
                continue;
            }
            for (int j = 0; j < combinaisonJoueur.length(); j++) {
                if (combinaisonJoueur.charAt(j) == combinaisonATrouver.charAt(i)) {
                    present++;
                }
            }
        }
        indiceTemp.append(present + " présent, " + bienPlace + " bien placé");
        indice = indiceTemp.toString();
        return indice;
    }
}
