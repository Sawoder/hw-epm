package ru.sawoder.epam.recursion;

/**
 * For finds decisions functions using recursion for natural numbers.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class RecursionNaturalNumber {
    /**
     * Gets sum of digits from the number
     *
     * @param number Positive number
     * @return Sum of digits
     */
    public int sumOfNumber(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Argument must be positive");
        }

        if (number < 10) {
            return number;
        }
        return number % 10 + sumOfNumber(number / 10);
    }

    /**
     * Gets count of digits from the number
     *
     * @param number Positive number
     * @return Sum of digits
     */
    public int countOfNumber(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Argument must be positive");
        }

        if (number < 10) {
            return 1;
        }
        return 1 + countOfNumber(number / 10);
    }
}
