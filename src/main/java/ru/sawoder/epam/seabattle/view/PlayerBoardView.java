package ru.sawoder.epam.seabattle.view;

import ru.sawoder.epam.seabattle.model.Board;
import ru.sawoder.epam.seabattle.model.StateCell;

/**
 * Player view for print full information about player's board.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class PlayerBoardView extends BasicBoardView {
    /**
     * Only do not miss shots another player.
     *
     * @param board Enemy board
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    @Override
    protected void useStateCondition(Board board, int x, int y, int z) {
        StateCell state = board.getField()[x][y][z].getState();
        if (state == StateCell.MISS) {
            System.out.print(StateCell.EMPTY.getASCII() + " ");
        } else {
            System.out.print(state.getASCII() + " ");
        }
    }
}
