package ru.sawoder.epam.seabattle.view;

import ru.sawoder.epam.seabattle.model.Board;

/**
 * Basic realization view for print a board.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public abstract class BasicBoardView implements BoardView {
    /**
     * Print each layer of {@link Board#field} with condition.
     *
     * @param board current board for print.
     */
    @Override
    public void printBoard(Board board) {
        for (int x = 0; x < board.getSize(); x++) {
            System.out.println("Layer X: " + x);
            for (int y = 0; y < board.getSize(); y++) {
                for (int z = 0; z < board.getSize(); z++) {
                    useStateCondition(board, x, y, z);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    protected abstract void useStateCondition(Board board, int x, int y, int z);
}
