package Jeux;

import Joueur.Joueur;
import Main.Main;

import java.sql.Array;
import java.util.*;

public class Mastermind implements Jeu {
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private static String indice;
    private static int[] resultatComparaison = new int[2];

    private int mastermindTry;//contenue dans le fichier de propriétée
    private int mastermindColor;

    public Mastermind(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;

        mastermindTry = Propriétée.mastermindTry;
        mastermindColor = Propriétée.mastermindColor;
    }

    @Override
    public void initialisation() {
        combinaison = defenseur.demandeCombinaisonAleatoire();
        System.out.println("Nombre de couleur : " + mastermindColor);
        if (Main.devMod) {
            System.out.println("Combinaison à trouver : " + combinaison);
        }
    }

    @Override
    public void jouer() {
        StringBuilder indiceTemp = new StringBuilder();

        System.out.println("il reste " + mastermindTry + " essais");
        String combinaisonJoueur = attaquant.demandeCombinaison();
        resultatComparaison = this.comparaison(combinaisonJoueur, combinaison);

        indiceTemp.append(resultatComparaison[0] + " present / " + resultatComparaison[1] + " bien place\n");
        indice = indiceTemp.toString();
        attaquant.envoyerIndice(indice);
        mastermindTry--;
    }

    @Override
    public boolean estFin() {
        if (mastermindTry > 0) {
            if (resultatComparaison[1] == combinaison.length()) {
                attaquant.affichageResultatPartie(true);
                return false;
            }
        } else {
            attaquant.affichageResultatPartie(false);
            return false;
        }
        return true;
    }

    public static int[] comparaison(String combinaisonProposee, String combinaisonATrouver) {
        int present = 0;
        int bienPlace = 0;
        int[] indice = new int[2];

        Set<Character> combinaisonProposeeSansDoublon = new HashSet<>();
        for (int i = 0; i < combinaisonProposee.length(); i++) {
            combinaisonProposeeSansDoublon.add(combinaisonProposee.charAt(i));
        }

        for (int i = 0; i < combinaisonATrouver.length(); i++) {
            if (combinaisonProposee.charAt(i) == combinaisonATrouver.charAt(i)) {
                bienPlace++;
                combinaisonProposeeSansDoublon.remove(combinaisonATrouver.charAt(i));

            }
        }

        for (int i = 0; i < combinaisonATrouver.length(); i++) {
            if (combinaisonProposeeSansDoublon.contains(combinaisonATrouver.charAt(i))) {
                present++;
                combinaisonProposeeSansDoublon.remove(combinaisonATrouver.charAt(i));
            }
        }

        indice[0] = present;
        indice[1] = bienPlace;
        return indice;
    }

    public static int getBienPlace() {
        return resultatComparaison[1];
    }

    public static int getPresent() {
        return resultatComparaison[0];
    }
}
