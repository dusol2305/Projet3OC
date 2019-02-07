package com.tdevlee.jeux;

import com.tdevlee.joueur.AIMastermind;
import com.tdevlee.joueur.AISearchNumber;
import com.tdevlee.joueur.Human;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Show the menu and run the game.
 * Contains the methods to select game and game mode and run the game.
 */
public class GameMenu {
    private static Logger logger = LogManager.getLogger(GameMenu.class);
    private boolean quit = true;
    private static byte[] choice = {0, 0};

    /**
     * Select the wanted game
     */
    public void gameSelection() {
        int gameSelection;
        boolean validChoice = true;

        Scanner sc = new Scanner(System.in);

        while (validChoice) {
            System.out.println("Choix du jeux :\n1 : Mastermind\n2 : Recherche +/-");
            gameSelection = sc.nextInt();
            switch (gameSelection) {
                case 1:
                    System.out.println("Selection Mastermind");
                    choice[0] = 1;
                    validChoice = false;
                    break;

                case 2:
                    System.out.println("Selection Recherche +/-");
                    choice[0] = 2;
                    validChoice = false;
                    break;

                default:
                    System.out.println("Veuillez choisir un nombre entre 1 et 2");
                    break;
            }
        }
    }

    /**
     * Select the game mode
     */
    public void modeSelection() {
        int mode;
        boolean validChoice = true;

        Scanner sc = new Scanner(System.in);

        while (validChoice) {
            System.out.println("Choix du mode :\n1 : Challenger\n2 : Défenseur\n3 : Duel");
            mode = sc.nextInt();
            switch (mode) {
                case 1:
                    System.out.println("Selection du mode challenger");
                    choice[1] = 1;
                    validChoice = false;
                    break;

                case 2:
                    System.out.println("Selection du mode défenseur");
                    choice[1] = 2;
                    validChoice = false;
                    break;

                case 3:
                    System.out.println("Selection du mode duel");
                    choice[1] = 3;
                    validChoice = false;
                    break;

                default:
                    System.out.println("Veuillez choisir un nombre entre 1, 2 et 3");
                    break;
            }
        }
    }

    /**
     * Start the selected game in the selected mode.
     */
    public void gameStart() {

        switch (choice[0]) {
            case 1: // Selection de Mastermind
                switch (choice[1]) { // choice du mode de Mastermind
                    case 1:
                        logger.info("Lancement mastermind challenger");
                        Game mastermindChall = new Mastermind(new Human(), new AIMastermind());
                        mastermindChall.initialization();
                        while (!mastermindChall.isEnd()) {
                            mastermindChall.play();
                        }
                        break;
                    case 2:
                        logger.info("Lancement mastermind defenseur");
                        Game mastermindDef = new Mastermind(new AIMastermind(), new Human());
                        mastermindDef.initialization();
                        while (!mastermindDef.isEnd()) {
                            mastermindDef.play();
                        }
                        break;
                    case 3:
                        logger.info("Lancement mastermind duel");
                        Game mastermindDuel1 = new Mastermind(new Human(), new AIMastermind());
                        Game mastermindDuel2 = new Mastermind(new AIMastermind(), new Human());
                        mastermindDuel1.initialization();
                        mastermindDuel2.initialization();
                        while (!mastermindDuel1.isEnd() && mastermindDuel2.isEnd()) {
                            System.out.println("\nTour du joueur");
                            mastermindDuel1.play();
                            System.out.println("\nTour de l'IA");
                            mastermindDuel2.play();
                        }
                        break;
                    default:
                        System.err.println("erreur dans la selection du mode");
                        logger.error("Erreur lors de la selection du mode de jeux");
                        break;
                }
                break;

            case 2: // Selection de RechercheNB
                switch (choice[1]) { // choice du mode de RechercheNB
                    case 1:
                        logger.info("Lancement rechercheNB challenger");
                        Game rechercheNbChall = new SearchNumber(new Human(), new AISearchNumber());
                        rechercheNbChall.initialization();
                        while (!rechercheNbChall.isEnd()) {
                            rechercheNbChall.play();
                        }
                        break;
                    case 2:
                        logger.info("Lancement rechercheNB defenseur");
                        Game rechercheNbDef = new SearchNumber(new AISearchNumber(), new Human());
                        rechercheNbDef.initialization();
                        while (!rechercheNbDef.isEnd()) {
                            rechercheNbDef.play();
                        }
                        break;
                    case 3:
                        logger.info("Lancement rechercheNB duel");
                        Game rechercheNbDuel1 = new SearchNumber(new Human(), new AISearchNumber());
                        Game rechercheNbDuel2 = new SearchNumber(new AISearchNumber(), new Human());
                        rechercheNbDuel1.initialization();
                        rechercheNbDuel2.initialization();
                        while (!rechercheNbDuel1.isEnd() && !rechercheNbDuel2.isEnd()) {
                            System.out.println("\nTour du joueur");
                            rechercheNbDuel1.play();
                            System.out.println("\nTour de l'IA");
                            rechercheNbDuel2.play();
                        }
                        break;
                }
                break;
            default:
                System.err.println("erreur dans la selection du jeu");
                logger.error("Erreur lors de la selection du jeux");
                break;
        }
    }

    /**
     * Run the game and ask what to do at the end of it.
     */
    public void run() {
        byte endGameChoice = 0;
        boolean vaildChoice = true;
        while (quit) {
            this.gameSelection();
            this.modeSelection();
            this.gameStart();
            while (vaildChoice) {
                System.out.println("Que souhaitez vous faire ?\n1 : Rejouer au même jeu\n2 : Jouer à un autre jeu\n3 : Quitter");
                Scanner sc = new Scanner(System.in);

                try {
                    endGameChoice = sc.nextByte();
                } catch (InputMismatchException ex){
                    logger.warn("Saisie du nombre de fin de partie invalide");
                }

                switch (endGameChoice) {
                    case 1:
                        this.gameStart();
                        break;
                    case 2:
                        vaildChoice = false;
                        break;
                    case 3:
                        quit = false;
                        vaildChoice = false;
                        logger.info("Fermeture de l'application");
                        break;
                    default:
                        System.out.println("Veuillez choisir entre 1 et 3");
                        break;
                }
            }
            vaildChoice = true;
        }
    }
}
