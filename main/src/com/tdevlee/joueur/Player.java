package com.tdevlee.joueur;

public interface Player {

    /**
     * Ask the combination to the player.
     * @return the combination entered by the player
     */
    String askCombination();

    /**
     * Ask a secret combination to find.
     * @return the combination entered by the player
     */
    String askSecretCombination();

    /**
     * Display the clue in the console.
     * @param clue clue returned from the game
     */
    void displayClue(String clue);

    /**
     * Dislpay the game result.
     * @param win set to true if the player win
     */
    void displayGameResult(boolean win);
}
