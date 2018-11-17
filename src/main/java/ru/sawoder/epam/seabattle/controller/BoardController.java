package ru.sawoder.epam.seabattle.controller;

import ru.sawoder.epam.seabattle.model.Board;
import ru.sawoder.epam.seabattle.model.StateCell;
import ru.sawoder.epam.seabattle.model.ship.Ship;
import ru.sawoder.epam.seabattle.view.BoardView;
import ru.sawoder.epam.seabattle.view.EnemyBoardView;

/**
 * Controller for basic operations with enemy {@link Board}.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class BoardController {
    private Board enemyBoard;
    private BoardView view;

    public BoardController(Board enemyBoard) {
        this.enemyBoard = enemyBoard;
        this.view = new EnemyBoardView();
    }

    /**
     * Try to causing damage a enemy ship using coordinates.
     * If state cell be:
     * {@code StateCell.SHIP} then {@link Board#getAttackOnShip(int, int, int)} is invoked and board is updated.
     * {@code StateCell.MISS} then return as success attack for repeat step.
     * Else {@code StateCell.MISS} is set at this point.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return true if the attack was successful
     */
    public boolean attack(int x, int y, int z) {
        boolean isSuccessAttack = false;
        StateCell state = enemyBoard.getField()[x][y][z].getState();
        if (state == StateCell.SHIP) {
            System.out.println("HIT!");
            Ship enemyShip = enemyBoard.getAttackOnShip(x, y, z);
            if (enemyShip.shipIsDestroyed()) {
                System.out.println("THE SHIP HAS BEEN DESTROYED!");
            }
            enemyBoard.updateShipsOnField();
            isSuccessAttack = true;
        } else if (state == StateCell.MISS || state == StateCell.HIT){
            System.out.println("THIS CELL WAS ATTACK AGAIN!");
            isSuccessAttack = true;
        } else {
            System.out.println("MISS!");
            enemyBoard.getField()[x][y][z].setState(StateCell.MISS);
        }
        view.printBoard(enemyBoard);
        return isSuccessAttack;
    }

    /**
     * Get count of alive ships on the enemy field.
     *
     * @return {@link Board#getAliveShipsCount()}
     */
    public int getEnemyAliveShips() {
        return enemyBoard.getAliveShipsCount();
    }
}
