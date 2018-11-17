package ru.sawoder.epam.seabattle.controller;

import org.junit.Before;
import org.junit.Test;
import ru.sawoder.epam.seabattle.exception.SeaBattleException;
import ru.sawoder.epam.seabattle.model.Board;
import ru.sawoder.epam.seabattle.model.StateCell;
import ru.sawoder.epam.seabattle.model.ship.Destroyer;
import ru.sawoder.epam.seabattle.model.ship.Direction;
import ru.sawoder.epam.seabattle.model.ship.Ship;

import static org.junit.Assert.*;

/**
 * Testing {@link BoardController} class.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class BoardControllerTest {
    private BoardController controller;

    @Before
    public void setup() throws SeaBattleException {
        Board enemyBoard = new Board(5);
        Ship ship = new Destroyer();
        ship.initCoordinates(4, 4, 4, Direction.Z_LINE);
        enemyBoard.addShip(ship);
        enemyBoard.getField()[0][0][0].setState(StateCell.MISS);
        enemyBoard.getField()[0][0][1].setState(StateCell.ZONE);
        enemyBoard.getField()[0][0][2].setState(StateCell.HIT);
        enemyBoard.getField()[0][0][3].setState(StateCell.EMPTY);
        controller = new BoardController(enemyBoard);
    }

    @Test
    public void attackTest() {
        assertTrue(controller.attack(0, 0, 0));
        assertFalse(controller.attack(0, 0, 1));
        assertTrue(controller.attack(0, 0, 2));
        assertFalse(controller.attack(0, 0, 3));
        assertTrue(controller.attack(4, 4, 4));
        assertTrue(controller.attack(4, 4, 3));
        assertTrue(controller.attack(4, 4, 3));
    }

    @Test
    public void getEnemyAliveShipsTest() {
        assertEquals(1, controller.getEnemyAliveShips());
        controller.attack(4, 4, 4);
        assertEquals(1, controller.getEnemyAliveShips());
        controller.attack(4, 4, 3);
        assertEquals(0, controller.getEnemyAliveShips());
    }
}
