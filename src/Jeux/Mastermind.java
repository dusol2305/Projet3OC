package Jeux;

import Joueur.Joueur;
import Main.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Mastermind implements Jeu {
    private static Logger logger = LogManager.getLogger(Mastermind.class);
    private Joueur attaquant;
    private Joueur defenseur;
    private String combinaison;
    private String indice;
    boolean combinaisonValide = true;
    private int[] resultatComparaison = new int[2];

    private int mastermindTry;//contenue dans le fichier de propriétée
    private int mastermindColor;
    private int mastermindLengh;

    public Mastermind(Joueur attaquant, Joueur defenseur) {
        this.attaquant = attaquant;
        this.defenseur = defenseur;

        mastermindTry = Proprietee.mastermindTry;
        mastermindColor = Proprietee.mastermindColor;
        mastermindLengh = Proprietee.mastermindLengh;
    }

    @Override
    public void initialisation() {
        System.out.println("Nombre de couleur : " + mastermindColor);
        System.out.println("Taille de la combinaison : " + mastermindLengh);

        combinaisonValide = true;
        while (combinaisonValide) {
            combinaison = this.verificationCombinaison(defenseur.demandeCombinaisonAleatoire());
        }

        if (Main.devMod || Proprietee.devMod) {
            System.out.println("Combinaison à trouver : " + combinaison);
        }
    }

    @Override
    public void jouer() {
        StringBuilder indiceTemp = new StringBuilder();

        System.out.println("il reste " + mastermindTry + " essais");
        String combinaisonJoueur = null;

        combinaisonValide = true;
        while (combinaisonValide) {
            combinaisonJoueur = this.verificationCombinaison(attaquant.demandeCombinaison());
        }

        resultatComparaison = this.comparaison(combinaisonJoueur, combinaison);

        indiceTemp.append(resultatComparaison[0] + " présent, " + resultatComparaison[1] + " bien placé\n");
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

    private String verificationCombinaison(String combinaison) {
        int validColor = mastermindColor - 1;
            combinaisonValide = false;
            if (combinaison.length() != mastermindLengh){
                System.out.println("Taille de la combinaison incorrecte. Taille de la combinaison à entrer : " + mastermindLengh);
                combinaisonValide = true;
            }

            for (int i = 0; i< combinaison.length(); i++){
                if (combinaison.charAt(i) > mastermindColor+48 || combinaison.charAt(i) < '0'){
                    System.out.println("Couleur incorrecte. Couleurs de la combinaison à entrer cmprise entre 0 et " + validColor);
                    combinaisonValide = true;
                }
            }
        return combinaison;
    }
}