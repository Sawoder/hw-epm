package ru.sawoder.epam.seabattle.model.ship;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing {@link Direction} enum.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class ShipTypeTest {
    @Test
    public void getAllCountTest() {
        assertEquals(10, ShipType.getAllCount());
    }
}
