package ru.sawoder.epam.seabattle.model;

/**
 * State for each cell of the board.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public enum StateCell {
    EMPTY,
    SHIP,
    ZONE,
    HIT,
    MISS;

    /**
     * Type conversion to character
     *
     * @return ASCII symbol
     */
    public char getASCII() {
        switch (this) {
            case EMPTY:
                return '.';
            case SHIP:
                return '#';
            case ZONE:
                return '*';
            case HIT:
                return '!';
            case MISS:
                return 'X';
        }
        return '.';
    }
}
