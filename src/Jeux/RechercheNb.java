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
        attaquant.affichageIndice(this.comparaison(attaquant.demandeCombinaison(), combinaison));
    }

    @Override
    public boolean estFin() {
        return true;
    }

    private String comparaison(String combinaisonJoueur, String combinaisonATrouver) {
        String indice;
        StringBuilder indiceTemp = new StringBuilder();
        for (int i = 0; i < combinaisonATrouver.length(); i++) {
            if (combinaisonJoueur.charAt(i) < combinaisonATrouver.charAt(i)) {
                indiceTemp.append("+");
            } else if (combinaisonJoueur.charAt(i) > combinaisonATrouver.charAt(i)) {
                indiceTemp.append("-");
            } else {
                indiceTemp.append("=");
            }
        }
        indice = indiceTemp.toString();
        return indice;
    }
}