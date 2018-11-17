package ru.sawoder.epam.seabattle.exception;

/**
 *  Thrown to indicate that a direction has been accessed with an
 *  illegal text in string. The string not 'x', 'y' or 'z'.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class DirectionFormatException extends SeaBattleException {
    public DirectionFormatException() {
        super("Direction must be 'x', 'y' or 'z'");
    }
}
