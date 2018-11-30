import Jeux.Jeu;
import Jeux.RechercheNb;
import Joueur.Humain;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*int choixJeux;
        boolean choixValide = true;

        Affichage affichage = new Affichage();
        Scanner sc = new Scanner(System.in);

        while (choixValide) {
            Affichage.selectionJeu();
            choixJeux = Sc.nextInt();
            switch (choixJeux) {
                case 1:
                    System.out.println("Lancement Mastermind");
                    break;
                case 2:
                    System.out.println("Lancement Recherche +/-");
                    RechercheNb RechercheNb = new RechercheNb();
                    break;
                default:
                    System.out.println("Veuillez choisir un nombre entre 1 et 2");
                    break;
            }
        }*/
        Jeu jeu = new RechercheNb(new Humain(), new Humain());
        jeu.initialisation();
        while (jeu.estFin(((RechercheNb) jeu).getIndice())) {
            jeu.jouer();
        }
    }
}
