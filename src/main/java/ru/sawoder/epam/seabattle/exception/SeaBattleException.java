package ru.sawoder.epam.seabattle.exception;

/**
 * Basic sub exception for the sea battle.
 * Thrown to indicate a specific cause arising in the game.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class SeaBattleException extends Exception {
    public SeaBattleException(String message) {
        super(message);
    }
}
