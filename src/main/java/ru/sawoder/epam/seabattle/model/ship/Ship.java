package ru.sawoder.epam.seabattle.model.ship;

import ru.sawoder.epam.seabattle.model.Cell;
import ru.sawoder.epam.seabattle.model.StateCell;
import ru.sawoder.epam.seabattle.exception.ShipOutOfBoundsException;

/**
 * Abstract class with basic actions with ships.
 *
 * @see Ship#isHit keeping state each part of the ship.
 * @see Ship#parts keeping {@link Cell}s where ship located on the board.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public abstract class Ship {
    private int length;
    private boolean[] isHit;
    private Cell[] parts;
    private Direction direction;

    public Ship(int length) {
        this(length, null);
    }

    public Ship(int length, Direction direction) {
        this.length = length;
        this.isHit = new boolean[length];
        this.parts = new Cell[length];
        this.direction = direction;
    }

    /**
     * Trying to registry hit by index.
     *
     * @param index index of {@link Ship#isHit}
     */
    public void registryHit(int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("Index must be in boundaries isHit array");
        }

        this.isHit[index] = true;
    }

    /**
     * Checks all parts of {@link Ship#isHit}.
     * If all parts are false then ship is destroyed.
     *
     * @return true if ship is destroyed.
     */
    public boolean shipIsDestroyed() {
        for (int i = 0; i < isHit.length; i++) {
            if (!isHit[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Initialize coordinates and direction for current ship.
     * Set for each part {@link Ship#parts} new instance of {@link Cell}
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @param direction {@link Direction}
     * @throws ShipOutOfBoundsException if ship can't be placed there.
     */
    public void initCoordinates(int x, int y, int z, Direction direction) throws ShipOutOfBoundsException {
        if (direction.getChangingCoordinate(x, y, z) - (length - 1) < 0) {
            throw new ShipOutOfBoundsException(direction);
        }
        this.parts[0] = new Cell(x, y, z, StateCell.SHIP);
        for (int i = 1; i < length; i++) {
            switch (direction) {
                case X_LINE:
                    this.parts[i] = new Cell(x - i, y, z, StateCell.SHIP);
                    break;
                case Y_LINE:
                    this.parts[i] = new Cell(x, y - i, z, StateCell.SHIP);
                    break;
                case Z_LINE:
                    this.parts[i] = new Cell(x, y, z - i, StateCell.SHIP);
                    break;
            }
        }
    }

    public int getLength() {
        return length;
    }

    public boolean[] getIsHit() {
        return isHit;
    }

    public Cell[] getParts() {
        return parts;
    }
}
