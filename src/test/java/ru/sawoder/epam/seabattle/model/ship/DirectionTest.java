package ru.sawoder.epam.seabattle.model.ship;

import org.junit.Test;
import ru.sawoder.epam.seabattle.exception.DirectionFormatException;

import static org.junit.Assert.*;

/**
 * Testing {@link Direction} enum.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class DirectionTest {
    @Test
    public void getChangingCoordinateTest() {
        Direction direction = Direction.X_LINE;
        assertEquals(0, direction.getChangingCoordinate(0, -1, -1));
        direction = Direction.Y_LINE;
        assertEquals(15, direction.getChangingCoordinate(5, 15, 6));
        direction = Direction.Z_LINE;
        assertEquals(1, direction.getChangingCoordinate(1, 1, 1));
    }

    @Test
    public void getDirectionByStringTest() throws DirectionFormatException {
        assertEquals(Direction.X_LINE, Direction.getDirectionByString("x"));
        assertEquals(Direction.Y_LINE, Direction.getDirectionByString("y"));
        assertEquals(Direction.Z_LINE, Direction.getDirectionByString("z"));
    }

    @Test(expected = DirectionFormatException.class)
    public void getDirectionByStringTest_Exception() throws DirectionFormatException {
        Direction.getDirectionByString("");
    }
}
