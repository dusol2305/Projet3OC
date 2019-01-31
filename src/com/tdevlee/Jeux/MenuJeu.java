package com.tdevlee.Jeux;

import com.tdevlee.Joueur.Humain;
import com.tdevlee.Joueur.IAmastermind;
import com.tdevlee.Joueur.IArechercheNb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MenuJeu {
    private static Logger logger = LogManager.getLogger(MenuJeu.class);
    private boolean quitter = true;
    private static byte[] choix = {0, 0};

    public void selectionJeu() {
        int choixJeux;
        boolean choixValide = true;

        Scanner sc = new Scanner(System.in);

        while (choixValide) {
            System.out.println("Choix du jeux :\n1 : Mastermind\n2 : Recherche +/-");
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
            case 1: // Selection de Mastermind
                switch (choix[1]) { // choix du mode de RechercherNb
                    case 1:
                        Jeu mastermindChall = new Mastermind(new Humain(), new IAmastermind());
                        mastermindChall.initialisation();
                        while (mastermindChall.estFin()) {
                            mastermindChall.jouer();
                        }
                        break;
                    case 2:
                        Jeu mastermindDef = new Mastermind(new IAmastermind(), new Humain());
                        mastermindDef.initialisation();
                        while (mastermindDef.estFin()) {
                            mastermindDef.jouer();
                        }
                        break;
                    case 3:
                        Jeu mastermindDuel1 = new Mastermind(new Humain(), new IAmastermind());
                        Jeu mastermindDuel2 = new Mastermind(new IAmastermind(), new Humain());
                        mastermindDuel1.initialisation();
                        mastermindDuel2.initialisation();
                        while (mastermindDuel1.estFin() && mastermindDuel2.estFin()) {
                            mastermindDuel1.jouer();
                            mastermindDuel2.jouer();
                        }
                        break;
                    default:
                        System.err.println("erreur dans la selection du mode");
                        break;
                }
                break;

            case 2: // Selection de RechercheNB
                switch (choix[1]) { // choix du mode de Mastermind
                    case 1:
                        Jeu rechercheNbChall = new RechercheNb(new Humain(), new IArechercheNb());
                        rechercheNbChall.initialisation();
                        while (!rechercheNbChall.estFin()) {
                            rechercheNbChall.jouer();
                        }
                        break;
                    case 2:
                        Jeu rechercheNbDef = new RechercheNb(new IArechercheNb(), new Humain());
                        rechercheNbDef.initialisation();
                        while (!rechercheNbDef.estFin()) {
                            rechercheNbDef.jouer();
                        }
                        break;
                    case 3:
                        Jeu rechercheNbDuel1 = new RechercheNb(new Humain(), new IArechercheNb());
                        Jeu rechercheNbDuel2 = new RechercheNb(new IArechercheNb(), new Humain());
                        rechercheNbDuel1.initialisation();
                        rechercheNbDuel2.initialisation();
                        while (!rechercheNbDuel1.estFin() && !rechercheNbDuel2.estFin()) {
                            System.out.println("Tour du joueur");
                            rechercheNbDuel1.jouer();
                            System.out.println("Tour de l'IA");
                            rechercheNbDuel2.jouer();
                        }
                        break;
                }
                break;
            default:
                System.err.println("erreur dans la selection du jeu");
                break;
        }
    }

    public void run() {
        byte choixDeFin;
        boolean choixValide = true;
        while (quitter) {
            this.selectionJeu();
            this.selectionMode();
            this.lancementJeu();
            while (choixValide) {
                System.out.println("Que souhaitez vous faire ?\n1 : Rejouer au même jeu\n2 : Jouer à un autre jeu\n3 : Quitter");
                Scanner sc = new Scanner(System.in);
                choixDeFin = sc.nextByte();
                switch (choixDeFin) {
                    case 1:
                        this.lancementJeu();
                        break;
                    case 2:
                        choixValide = false;
                        break;
                    case 3:
                        quitter = false;
                        choixValide = false;
                        break;
                    default:
                        System.out.println("Veuillez choisir entre 1 et 3");
                        break;
                }
            }
            choixValide = true;
        }
    }
}
