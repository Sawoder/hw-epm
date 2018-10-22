package ru.sawoder.epam.task05;

/**
 * Main description.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class Main {
    public static void main(String[] args) {
        Executor<Integer> integerExecutor = element -> element * element;
        Executor<Double> doubleExecutor = element -> element * 2.1d;
        Executor<Long> longExecutor = element -> element << 2;

        System.out.println(integerExecutor.multiply(23));
        System.out.println(doubleExecutor.multiply(12.1d));
        System.out.println(longExecutor.multiply(1024L));
    }
}
