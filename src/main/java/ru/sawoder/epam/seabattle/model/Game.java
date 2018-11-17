package ru.sawoder.epam.seabattle.model;

import ru.sawoder.epam.seabattle.controller.BoardController;
import ru.sawoder.epam.seabattle.exception.DirectionFormatException;
import ru.sawoder.epam.seabattle.exception.ShipCollisionException;
import ru.sawoder.epam.seabattle.exception.ShipOutOfBoundsException;
import ru.sawoder.epam.seabattle.model.ship.Battleship;
import ru.sawoder.epam.seabattle.model.ship.Cruiser;
import ru.sawoder.epam.seabattle.model.ship.Destroyer;
import ru.sawoder.epam.seabattle.model.ship.Direction;
import ru.sawoder.epam.seabattle.model.ship.Ship;
import ru.sawoder.epam.seabattle.model.ship.ShipFactory;
import ru.sawoder.epam.seabattle.model.ship.ShipType;
import ru.sawoder.epam.seabattle.model.ship.Submarine;
import ru.sawoder.epam.seabattle.view.BoardView;
import ru.sawoder.epam.seabattle.view.EnemyBoardView;
import ru.sawoder.epam.seabattle.view.PlayerBoardView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * General logic for start the game.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class Game {
    private final int PLAYERS_COUNT = 2;
    private ShipFactory factory;
    private boolean[] isPlayer;
    private Board[] boards;
    private BoardController[] controllers;

    public Game(int size, boolean... isPlayer) {
        if (isPlayer.length != PLAYERS_COUNT) {
            throw new IllegalArgumentException();
        }
        this.boards = new Board[PLAYERS_COUNT];
        this.isPlayer = isPlayer;
        this.controllers = new BoardController[PLAYERS_COUNT];

        BoardView playerView = new PlayerBoardView();
        BoardView enemyView = new EnemyBoardView();
        for (int i = 0; i < PLAYERS_COUNT; i++) {
            Board board = new Board(size);
            board.setView(isPlayer[i] ? playerView : enemyView);
            boards[i] = board;
        }
        controllers[0] = new BoardController(boards[1]);
        controllers[1] = new BoardController(boards[0]);

        this.factory = ShipFactory.factory(builder -> {
            builder.add(ShipType.SUBMARINE, Submarine::new);
            builder.add(ShipType.DESTROYER, Destroyer::new);
            builder.add(ShipType.CRUISER, Cruiser::new);
            builder.add(ShipType.BATTLESHIP, Battleship::new);
        });
    }

    /**
     * Main method for control the game.
     * Setup boards for each player.
     * Initialize their ships and the start the game.
     */
    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < boards.length; i++) {
                System.out.println("PLAYER " + (i + 1) + " SETUP");
                for (ShipType type : ShipType.values()) {
                    for (int count = 0; count < type.getCount(); count++) {
                        Ship ship = factory.create(type);
                        try {
                            if (isPlayer[i]) {
                                System.out.println(type.toString() + " #" + (count + 1));
                                System.out.println("X Y Z");
                                String[] tmpCoords = reader.readLine().split(" ");
                                System.out.println("Direction (x | y | z)");
                                Direction direction = Direction.getDirectionByString(reader.readLine());
                                ship.initCoordinates(Integer.parseInt(tmpCoords[0]),
                                        Integer.parseInt(tmpCoords[1]),
                                        Integer.parseInt(tmpCoords[2]),
                                        direction);

                            } else {
                                int[] randomCoords = getRandomCoordinates(boards[i].getSize());
                                ship.initCoordinates(randomCoords[0],
                                        randomCoords[1],
                                        randomCoords[2],
                                        getRandomDirection());
                            }
                            boards[i].addShip(ship);
                            if (isPlayer[i]) {
                                boards[i].printBoard();
                            }
                        } catch (NumberFormatException | DirectionFormatException | ArrayIndexOutOfBoundsException
                                | ShipCollisionException | ShipOutOfBoundsException e) {
                            if (isPlayer[i]) {
                                System.err.println(e.getMessage());
                            }
                            count--;
                        }
                    }
                }
            }
            System.out.println();
            boolean isFinish = false;
            while (!isFinish) {
                for (int i = 0; i < boards.length; i++) {
                    if (controllers[i].getEnemyAliveShips() == 0) {
                        isFinish = true;
                        break;
                    }
                    System.out.println("ENEMY SHIPS REMAINING = " + controllers[i].getEnemyAliveShips());
                    if (isPlayer[i]) {
                        System.out.println("ATTACK X Y Z");
                        String[] tmpCoords = reader.readLine().split(" ");
                        try {
                            if (controllers[i].attack(Integer.parseInt(tmpCoords[0]),
                                    Integer.parseInt(tmpCoords[1]),
                                    Integer.parseInt(tmpCoords[2]))) {
                                i--;
                                continue;
                            }
                        System.out.println("PRESS BUTTON FOR NEXT PLAYER");
                        reader.readLine();
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | IOException e) {
                            System.err.println(e.getMessage());
                            i--;
                        }
                    } else {
                        System.out.println("COMPUTER ATTACK");
                        int[] randomCoords = getRandomCoordinates(boards[i].getSize());
                        if (controllers[i].attack(randomCoords[0], randomCoords[1], randomCoords[2])) {
                            i--;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets random array of three elements by stream.
     *
     * @param size size of board
     * @return three elements in the form of an array
     */
    private int[] getRandomCoordinates(int size) {
        return new Random().ints(3, 0, size).toArray();
    }

    /**
     * Gets random direction.
     *
     * @return random direction by number from 0 to 2 included.
     */
    private Direction getRandomDirection() {
        switch (new Random().nextInt(3)) {
            case 0:
                return Direction.X_LINE;
            case 1:
                return Direction.Y_LINE;
            case 2:
                return Direction.Z_LINE;
        }
        return null;
    }
}
