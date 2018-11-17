package ru.sawoder.epam.seabattle.view;

import ru.sawoder.epam.seabattle.model.Board;
import ru.sawoder.epam.seabattle.model.StateCell;

/**
 * Enemy view for print another board without additional information.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class EnemyBoardView extends BasicBoardView {
    /**
     * Do not print ships and their zone.
     *
     * @param board Enemy board
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    @Override
    protected void useStateCondition(Board board, int x, int y, int z) {
        StateCell state = board.getField()[x][y][z].getState();
        if (state == StateCell.SHIP || state == StateCell.ZONE) {
            System.out.print(StateCell.EMPTY.getASCII() + " ");
        } else {
            System.out.print(state.getASCII() + " ");
        }
    }
}
