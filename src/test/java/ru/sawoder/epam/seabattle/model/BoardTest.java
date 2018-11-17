package ru.sawoder.epam.seabattle.model;

import org.junit.Before;
import org.junit.Test;
import ru.sawoder.epam.seabattle.exception.SeaBattleException;
import ru.sawoder.epam.seabattle.exception.ShipCollisionException;
import ru.sawoder.epam.seabattle.model.ship.Direction;
import ru.sawoder.epam.seabattle.model.ship.Ship;
import ru.sawoder.epam.seabattle.model.ship.Submarine;

import static org.junit.Assert.*;

/**
 * Testing {@link Board} class.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class BoardTest {
    private Board board;

    @Before
    public void setup() throws SeaBattleException {
        board = new Board(10);
        Ship ship = new Submarine();
        ship.initCoordinates(0, 0, 0, Direction.X_LINE);
        board.addShip(ship);
    }

    @Test
    public void updateShipsOnFieldTest() {
        assertEquals(StateCell.SHIP, board.getField()[0][0][0].getState());
        board.getAttackOnShip(0, 0, 0);
        board.updateShipsOnField();
        assertEquals(StateCell.HIT, board.getField()[0][0][0].getState());
    }

    @Test
    public void addShipTest() throws SeaBattleException {
        assertEquals(1, board.getAliveShipsCount());
        Ship ship = new Submarine();
        ship.initCoordinates(5, 5, 5, Direction.X_LINE);
        board.addShip(ship);
        assertEquals(2, board.getAliveShipsCount());
    }

    @Test(expected = ShipCollisionException.class)
    public void addShipTest_Exception() throws SeaBattleException {
        Ship tmp = new Submarine();
        tmp.initCoordinates(0, 2, 0, Direction.X_LINE);
        board.addShip(tmp);
    }

    @Test
    public void getAliveShipsCountTest() throws SeaBattleException {
        assertEquals(1, board.getAliveShipsCount());
        for (int i = 1; i < 4; i++) {
            Ship ship = new Submarine();
            ship.initCoordinates(0, 0, i * 3, Direction.X_LINE);
            board.addShip(ship);
            assertEquals(i + 1, board.getAliveShipsCount());
        }
    }

    @Test
    public void getAttackOnShipTest() {
        assertEquals(1, board.getAttackOnShip(0, 0, 0).getLength());
        assertNull(board.getAttackOnShip(0, 0, 1));
    }
}
