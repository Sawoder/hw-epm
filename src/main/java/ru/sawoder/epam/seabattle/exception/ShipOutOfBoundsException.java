package ru.sawoder.epam.seabattle.exception;

import ru.sawoder.epam.seabattle.model.ship.Direction;

/**
 * Thrown to indicate that a chosen direction incorrect for coordinate.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class ShipOutOfBoundsException extends SeaBattleException {
    public ShipOutOfBoundsException(Direction direction) {
        super("Coordinate out of bounds in direction " + direction.toString());
    }
}
