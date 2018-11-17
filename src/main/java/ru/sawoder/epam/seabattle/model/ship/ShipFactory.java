package ru.sawoder.epam.seabattle.model.ship;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Factory Kit for created dynamic ship types get their instances.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public interface ShipFactory {

    /**
     * Creates an instance of the given type.
     *
     * @param name representing enum of an object type to be created.
     * @return new instance of a requested class implementing {@link Ship} interface.
     */
    Ship create(ShipType name);

    /**
     * Creates factory - placeholder for specified {@link Builder}s.
     *
     * @param consumer for the new builder to the factory.
     * @return factory with specified {@link Builder}s
     */
    static ShipFactory factory(Consumer<Builder> consumer) {
        Map<ShipType, Supplier<Ship>> map = new HashMap<>();
        consumer.accept(map::put);
        return name -> map.get(name).get();
    }
}
