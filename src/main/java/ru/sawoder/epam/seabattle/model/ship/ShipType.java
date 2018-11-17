package ru.sawoder.epam.seabattle.model.ship;

/**
 * Types of ship with their count on a board.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public enum ShipType {
    BATTLESHIP(1),
    CRUISER(2),
    DESTROYER(3),
    SUBMARINE(4);

    private int count;

    ShipType(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    /**
     * Get count of ships at one board.
     *
     * @return amount of count each ship
     */
    public static int getAllCount() {
        int count = 0;
        for (ShipType type : ShipType.values()) {
            count += type.getCount();
        }
        return count;
    }
}
