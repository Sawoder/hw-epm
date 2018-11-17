package ru.sawoder.epam.seabattle.model.ship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing {@link ShipFactory} class.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class ShipFactoryTest {
    private ShipFactory factory;

    @Before
    public void init() {
        factory = ShipFactory.factory(builder -> {
            builder.add(ShipType.SUBMARINE, Submarine::new);
            builder.add(ShipType.DESTROYER, Destroyer::new);
            builder.add(ShipType.CRUISER, Cruiser::new);
            builder.add(ShipType.BATTLESHIP, Battleship::new);
        });
    }

    @Test
    public void createSubmarineTest() {
        Ship ship = factory.create(ShipType.SUBMARINE);
        assertTrue(ship instanceof Submarine);
    }

    @Test
    public void createDestroyerTest() {
        Ship ship = factory.create(ShipType.DESTROYER);
        assertTrue(ship instanceof Destroyer);
    }

    @Test
    public void createCruiserTest() {
        Ship ship = factory.create(ShipType.CRUISER);
        assertTrue(ship instanceof Cruiser);
    }

    @Test
    public void createBattleshipTest() {
        Ship ship = factory.create(ShipType.BATTLESHIP);
        assertTrue(ship instanceof Battleship);
    }
}
