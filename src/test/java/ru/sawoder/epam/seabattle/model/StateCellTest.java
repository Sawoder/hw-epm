package ru.sawoder.epam.seabattle.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing {@link StateCell} enum.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class StateCellTest {
    @Test
    public void getASCIITest() {
        assertEquals('.', StateCell.EMPTY.getASCII());
        assertEquals('#', StateCell.SHIP.getASCII());
        assertEquals('*', StateCell.ZONE.getASCII());
        assertEquals('!', StateCell.HIT.getASCII());
        assertEquals('X', StateCell.MISS.getASCII());
    }
}
