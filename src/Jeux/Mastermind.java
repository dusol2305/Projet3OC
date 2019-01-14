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

    private int mastermindTry;//contenue dans le fichier de propriétée
    private int mastermindColor;

    private static int bienPlace = 0;
    private static int present = 0;


    public Mastermind(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;

        mastermindTry = Propriétée.mastermindTry;
        mastermindColor = Propriétée.mastermindColor;
    }

    @Override
    public void initialisation() {
        combinaison = defenseur.demandeCombinaisonAleatoire();
        System.out.println("Taille de la combinaison : " + mastermindTry);
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
        present = 0;
        bienPlace = 0;
        int[] resultatComparaison = this.comparaison(combinaisonJoueur, combinaison);

        indiceTemp.append("present " + resultatComparaison[0] + " bien placé" + resultatComparaison[1]);
        indice = indiceTemp.toString();
        attaquant.envoyerIndice(indice);
        mastermindTry--;
    }

    @Override
    public boolean estFin() {
        if (mastermindTry > 0) {
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
        return bienPlace;
    }

    public static int getPresent() {
        return present;
    }
}
