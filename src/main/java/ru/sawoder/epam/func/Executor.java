package ru.sawoder.epam.func;

@FunctionalInterface
public interface Executor<T extends Number> {
    T multiply(T element);
}
