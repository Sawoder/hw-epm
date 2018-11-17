package ru.sawoder.epam.seabattle;

import ru.sawoder.epam.seabattle.model.Game;

/**
 * For start the game.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class Application {
    public static void main(String[] args) {
        Game game = new Game(10, true, false);
        game.start();
    }
}
