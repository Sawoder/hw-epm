package ru.sawoder.epam.task05;

@FunctionalInterface
public interface Executor<T extends Number> {
    T multiply(T element);
}
