package com.tdevlee.jeux;

public interface Game {

    /**
     * Initialization of the game.
     * The secret combination of the game is set here
     */
    void initialization();

    /**
     * Contain the playing movement.
     */
    void play();

    /**
     * Test if it's the end of the game.
     * @return a boolean value use tu determine the end of the game.
     */
    boolean isEnd();
}
