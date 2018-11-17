package ru.sawoder.epam.seabattle.model;

import java.util.Objects;

/**
 * Unit of the {@link Board}
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class Cell {
    private int x;
    private int y;
    private int z;
    private StateCell state;

    public Cell(int x, int y, int z) {
        this(x, y, z, StateCell.EMPTY);
    }

    public Cell(int x, int y, int z, StateCell state) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public StateCell getState() {
        return state;
    }

    public void setState(StateCell state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return getX() == cell.getX() &&
                getY() == cell.getY() &&
                getZ() == cell.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }
}
