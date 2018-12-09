import Jeux.Jeu;
import Jeux.Mastermind;
import Jeux.RechercheNb;
import Joueur.Humain;
import Joueur.IAmastermind;
import Joueur.IArechercheNb;

import java.util.Scanner;

public class Menu {
    boolean quitter = true;
    byte[] choix = {0, 0};

    public void selectionJeu() {
        int choixJeux;
        boolean choixValide = true;

        Scanner sc = new Scanner(System.in);

        while (choixValide) {
            System.out.println("Choix du jeux :\n1 : Mastermind\n2 : Recherche +/-\n3 : Quitter");
            choixJeux = sc.nextInt();
            switch (choixJeux) {
                case 1:
                    System.out.println("Selection Mastermind");
                    choix[0] = 1;
                    choixValide = false;
                    break;

                case 2:
                    System.out.println("Selection Recherche +/-");
                    choix[0] = 2;
                    choixValide = false;
                    break;

                case 3:
                    quitter = false;
                    choixValide = false;
                    break;

                default:
                    System.out.println("Veuillez choisir un nombre entre 1 et 2");
                    break;
            }
        }
    }

    public void selectionMode() {
        int choixMode;
        boolean choixValide = true;

        Scanner sc = new Scanner(System.in);

        while (choixValide) {
            System.out.println("Choix du mode :\n1 : Challenger\n2 : Défenseur\n3 : Duel");
            choixMode = sc.nextInt();
            switch (choixMode) {
                case 1:
                    System.out.println("Selection du mode challenger");
                    choix[1] = 1;
                    choixValide = false;
                    break;

                case 2:
                    System.out.println("Selection du mode défenseur");
                    choix[1] = 2;
                    choixValide = false;
                    break;

                case 3:
                    System.out.println("Selection du mode duel");
                    choix[1] = 3;
                    choixValide = false;
                    break;

                default:
                    System.out.println("Veuillez choisir un nombre entre 1, 2 et 3");
                    break;
            }
        }
    }

    public void lancementJeu() {

        switch (choix[0]) {
            case 1: // Selection de RechercheNb
                switch (choix[1]) { // choix du mode de RechercherNb
                    case 1:
                        Jeu mastermindChall = new Mastermind(new Humain(), new IAmastermind());
                        mastermindChall.initialisation();
                        while (mastermindChall.estFin(((Mastermind) mastermindChall).getIndice())) {
                            mastermindChall.jouer();
                        }
                        break;
                    case 2:
                        Jeu mastermindDef = new Mastermind(new IAmastermind(), new Humain());
                        mastermindDef.initialisation();
                        while (mastermindDef.estFin(((Mastermind) mastermindDef).getIndice())) {
                            mastermindDef.jouer();
                        }
                        break;
                    case 3:
                        // mode duel a creer
                        Jeu mastermindDuel = new Mastermind(new Humain(), new IAmastermind());
                        mastermindDuel.initialisation();
                        while (mastermindDuel.estFin(((Mastermind) mastermindDuel).getIndice())) {
                            mastermindDuel.jouer();
                        }
                        break;
                    default :
                        System.err.println("erreur dans la selection du mode");
                        break;
                }
                break;

            case 2: // Selection de Mastermind
                switch (choix[1]) { // choix du mode de Mastermind
                    case 1:
                        Jeu rechercheNbChall = new RechercheNb(new Humain(), new IArechercheNb());
                        rechercheNbChall.initialisation();
                        while (rechercheNbChall.estFin(((RechercheNb) rechercheNbChall).getIndice())) {
                            rechercheNbChall.jouer();
                        }
                        break;
                    case 2:
                        Jeu rechercheNbDef = new RechercheNb(new IArechercheNb(), new Humain());
                        rechercheNbDef.initialisation();
                        while (rechercheNbDef.estFin(((RechercheNb) rechercheNbDef).getIndice())) {
                            rechercheNbDef.jouer();
                        }
                        break;
                    case 3:
                        // mode duel a creer
                        Jeu rechercheNbDuel = new RechercheNb(new Humain(), new IArechercheNb());
                        rechercheNbDuel.initialisation();
                        while (rechercheNbDuel.estFin(((RechercheNb) rechercheNbDuel).getIndice())) {
                            rechercheNbDuel.jouer();
                        }
                        break;
                }
                break;
            default:

                break;
        }
    }
}
