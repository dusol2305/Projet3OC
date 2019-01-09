package Jeux;

import Joueur.Joueur;
import Main.Main;

public class Mastermind implements Jeu {
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private static String indice;
    private int essaisMastermind = 15;
    private static int bienPlace = 0;
    private static int present = 0;


    public Mastermind(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;
    }

    @Override
    public void initialisation() {
        combinaison = defenseur.demandeCombinaisonAleatoire();
        if (Main.devMod) {
            System.out.println("Combinaison à trouver : " + combinaison);
        }
    }

    @Override
    public void jouer() {
        String combinaisonJoueur = attaquant.demandeCombinaison();
        indice = this.comparaison(combinaisonJoueur, combinaison);
        attaquant.envoyerIndice(indice);
    }

    @Override
    public boolean estFin() {
        if (essaisMastermind > 0) {
            if (bienPlace == combinaison.length()) {
                attaquant.affichageResultatPartie(true);
                return false;
            }
        } else {
            attaquant.affichageResultatPartie(false);
            return false;
        }
        return true;
    }

    public static String comparaison(String combinaisonProposée, String combinaisonATrouver) {
        bienPlace = 0;
        present = 0;
        boolean[] antiDoublon = new boolean[combinaisonATrouver.length()];
        String indice;
        StringBuilder indiceTemp = new StringBuilder();

        for (int i = 0; i < antiDoublon.length; i++) {
            antiDoublon[i] = true;
        }

        for (int i = 0; i < combinaisonATrouver.length(); i++) {
            if (combinaisonProposée.charAt(i) == combinaisonATrouver.charAt(i)) {
                bienPlace++;
                antiDoublon[i] = false;
            }
        }

        for (int i = 0; i < combinaisonATrouver.length(); i++) {
            if (antiDoublon[i] == true) {
                for (int j = 0; j < combinaisonATrouver.length(); j++) {
                    if (i != j && antiDoublon[j] == true && combinaisonATrouver.charAt(i) == combinaisonATrouver.charAt(j)) {
                        antiDoublon[j] = false;
                    }
                }
            }
        }

        for (int i = 0; i < combinaisonATrouver.length(); i++){
            if (antiDoublon[i] == true){
                for (int j = 0; j < combinaisonProposée.length(); j++){
                    if (i != j && combinaisonATrouver.charAt(i) == combinaisonProposée.charAt(j)){
                        present++;
                    }
                }
            }
        }

        indiceTemp.append(present + " présent, " + bienPlace + " bien placé");
        indice = indiceTemp.toString();
        return indice;
    }

    public static int getBienPlace() {
        return bienPlace;
    }

    public static int getPresent() {
        return present;
    }
}
