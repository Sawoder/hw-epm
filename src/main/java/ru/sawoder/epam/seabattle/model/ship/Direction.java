package ru.sawoder.epam.seabattle.model.ship;

import ru.sawoder.epam.seabattle.exception.DirectionFormatException;

/**
 * Sets direction a ship in an axis.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public enum Direction {
    X_LINE,
    Y_LINE,
    Z_LINE;

    /**
     * Trying to get coordinate that changes along the axis.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return coordinate that changes in this direction.
     */
    public int getChangingCoordinate(int x, int y, int z) {
        switch(this) {
            case X_LINE:
                return x;
            case Y_LINE:
                return y;
            case Z_LINE:
                return z;
        }
        return -1;
    }

    /**
     * Trying to parse string in direction.
     *
     * @param coord String with specified direction.
     * @return if success then returns direction else thrown an exception.
     * @throws DirectionFormatException {@link DirectionFormatException}
     */
    public static Direction getDirectionByString(String coord) throws DirectionFormatException {
        switch (coord.toLowerCase()) {
            case "x":
                return X_LINE;
            case "y":
                return Y_LINE;
            case "z":
                return Z_LINE;
        }
        throw new DirectionFormatException();
    }
}