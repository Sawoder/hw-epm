package ru.sawoder.epam.seabattle.model;

import ru.sawoder.epam.seabattle.exception.ShipCollisionException;
import ru.sawoder.epam.seabattle.model.ship.Ship;
import ru.sawoder.epam.seabattle.view.BoardView;

import java.util.ArrayList;
import java.util.List;

/**
 * Board for interaction with ships in three dimensions.
 *
 * @see Board#view how board will be printed.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class Board {
    private int size;
    private List<Ship> ships;
    private Cell[][][] field;
    private BoardView view;

    public Board(int size) {
        this.size = size;
        this.ships = new ArrayList<>();
        this.field = new Cell[size][size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    this.field[x][y][z] = new Cell(x, y, z);
                }
            }
        }
    }

    /**
     * If a ship gets hit then needs update board
     */
    public void updateShipsOnField() {
        for (Ship ship : ships) {
            for (int i = 0; i < ship.getLength(); i++) {
                Cell cell = ship.getParts()[i];
                if (cell.getState() == StateCell.SHIP && ship.getIsHit()[i]) {
                    this.field[cell.getX()][cell.getY()][cell.getZ()].setState(StateCell.HIT);
                }
            }
        }
    }

    /**
     * Trying to add new ship at the board and the list.
     * If not collision then create a zone around the ship.
     *
     * @param ship new instance without link on this board
     * @throws ShipCollisionException thrown if ship has same zone that exist ships
     */
    public void addShip(Ship ship) throws ShipCollisionException {
        if (hasCollision(ship)) {
            throw new ShipCollisionException();
        }
        this.ships.add(ship);
        createZone(ship);
    }

    /**
     * Get count of alive ships on the board.
     *
     * @return amount of alive ships.
     */
    public int getAliveShipsCount() {
        int count = 0;
        for (Ship ship : ships) {
            if (!ship.shipIsDestroyed()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Trying to attack and if success then return the ship.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return ship from list
     */
    public Ship getAttackOnShip(int x, int y, int z) {
        for (Ship ship : ships) {
            for (int i = 0; i < ship.getParts().length; i++) {
                if (ship.getParts()[i].getX() == x
                        && ship.getParts()[i].getY() == y
                        && ship.getParts()[i].getZ() == z) {
                    ship.registryHit(i);
                    return ship;
                }
            }
        }
        return null;
    }

    /**
     * Prints board by {@link BoardView}
     */
    public void printBoard() {
        view.printBoard(this);
    }

    public int getSize() {
        return size;
    }

    public Cell[][][] getField() {
        return field;
    }

    public void setView(BoardView view) {
        this.view = view;
    }

    /**
     * Around a ship needs to create zone for indicate another ship that this zone occupied.
     * @param ship around which ship to create conscious
     */
    private void createZone(Ship ship) {
        for (int i = 0; i < ship.getParts().length; i++) {
            Cell shipCell = ship.getParts()[i];
            int x = shipCell.getX();
            int y = shipCell.getY();
            int z = shipCell.getZ();
            field[x][y][z].setState(StateCell.SHIP);
            for (int xx = x - 1; xx <= x + 1; xx++) {
                for (int yy = y - 1; yy <= y + 1; yy++) {
                    for (int zz = z - 1; zz <= z + 1; zz++) {
                        checkEmptyAndSetZone(xx, yy, zz);
                    }
                }
            }
        }
    }

    /**
     * Collision the ship with another ships.
     *
     * @param ship around which ship to checks zone
     * @return true if this ship has collision.
     */
    private boolean hasCollision(Ship ship) {
        for (int i = 0; i < ship.getParts().length; i++) {
            Cell shipCell = ship.getParts()[i];
            int x = shipCell.getX();
            int y = shipCell.getY();
            int z = shipCell.getZ();
            for (int xx = x - 1; xx <= x + 1; xx++) {
                for (int yy = y - 1; yy <= y + 1; yy++) {
                    for (int zz = z - 1; zz <= z + 1; zz++) {
                        if (xx == 0 && yy == 0 && zz == 0) {
                            continue;
                        }
                        if (checkBounds(xx) && checkBounds(yy) && checkBounds(zz)) {
                            if (field[xx][yy][zz].getState() != StateCell.EMPTY) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Bounds of out from board.
     *
     * @param coord any coordinate
     * @return true if check was successful
     */
    private boolean checkBounds(int coord) {
        return size - coord > 0 && coord > -1;
    }

    /**
     * Checks point on the {@code StateCell.EMPTY} and set {@code StateCell.ZONE}.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    private void checkEmptyAndSetZone(int x, int y, int z) {
        if (checkBounds(x) && checkBounds(y) && checkBounds(z)) {
            if (field[x][y][z].getState() == StateCell.EMPTY) {
                field[x][y][z].setState(StateCell.ZONE);
            }
        }
    }
}
