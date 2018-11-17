package ru.sawoder.epam.seabattle.exception;

/**
 * Thrown to indicate that a ship has been trying to get access to the occupied zone.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class ShipCollisionException extends SeaBattleException {
    public ShipCollisionException() {
        super("The ship in boundaries another ship");
    }
}
