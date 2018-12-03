package Jeux;

import Joueur.Joueur;

public class RechercheNb implements Jeu {
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private String indice = "4455";

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
        indice = this.comparaison(attaquant.demandeCombinaison(getIndice()), combinaison);
    }

    @Override
    public boolean estFin(String indice) {
        int i = indice.length() - 1;
        while (i > -1) {
            if (indice.charAt(i) == '=') {
                i--;
            } else {
                return true;
            }
        }
        attaquant.affichageResultatPartie(true);
        return false;
    }

    public String getIndice() {
        return indice;
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