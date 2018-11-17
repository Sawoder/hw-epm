package ru.sawoder.epam.seabattle.model.ship;

import java.util.function.Supplier;

@FunctionalInterface
public interface Builder {
    void add(ShipType name, Supplier<Ship> supplier);
}
