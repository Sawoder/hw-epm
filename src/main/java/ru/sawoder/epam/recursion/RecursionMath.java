package ru.sawoder.epam.recursion;

/**
 * For finds decisions math functions using recursion.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class RecursionMath {
    /**
     * 10.41
     * Gets factorial of a number.
     *
     * @param number Positive number for finds factorial
     * @return Factorial of number
     */
    public int calcFactorial(int number) {
        if (number < 2) {
            return 1;
        }
        return number * calcFactorial(number - 1);
    }

    /**
     * 10.42
     * Gets power of a number.
     *
     * @param base Number raises to the power of {@code exponent}
     * @param exponent Positive power
     * @return Power of number
     */
    public double pow(double base, int exponent) {
        if (exponent < 1) {
            throw new IllegalArgumentException("Argument must be positive");
        }

        if (exponent == 1) {
            return base;
        }
        return base * pow(base, exponent - 1);
    }
}
