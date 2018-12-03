import Jeux.Jeu;
import Jeux.RechercheNb;
import Joueur.Humain;
import Joueur.IA;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int choixJeux;
        boolean choixValide = true;

        Scanner sc = new Scanner(System.in);

        while (choixValide) {
            System.out.println("Choix du jeux :\n1 : Mastermind\n2 : Recherche +/-\n3 : Quitter");
            choixJeux = sc.nextInt();
            switch (choixJeux) {
                case 1:
                    System.out.println("Lancement Mastermind");
                    break;

                case 2:
                    System.out.println("Lancement Recherche +/-");

                    Jeu jeu = new RechercheNb(new Humain(), new IA());
                    jeu.initialisation();
                    while (jeu.estFin(((RechercheNb) jeu).getIndice())) {
                        jeu.jouer();
                    }

                    break;

                case 3:
                    choixValide = false;
                    break;

                default:
                    System.out.println("Veuillez choisir un nombre entre 1 et 2");
                    break;
            }
        }
    }
}
