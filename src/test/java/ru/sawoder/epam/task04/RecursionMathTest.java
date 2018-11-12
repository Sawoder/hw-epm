package ru.sawoder.epam.task04;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing {@link RecursionMath} class.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class RecursionMathTest {
    private RecursionMath recursionMath;

    @Before
    public void setUp() {
        recursionMath = new RecursionMath();
    }

    @Test
    public void calcFactorialTest() {
        assertEquals(1, recursionMath.calcFactorial(-1));
        assertEquals(1, recursionMath.calcFactorial(-10));
        assertEquals(2, recursionMath.calcFactorial(2));
        assertEquals(720, recursionMath.calcFactorial(6));
        assertEquals(120, recursionMath.calcFactorial(5));
        assertEquals(109641728, recursionMath.calcFactorial(19));
    }

    @Test
    public void powTest() {
        double delta = 0.0000001;
        assertEquals(1, recursionMath.pow(1, 10), delta);
        assertEquals(1024, recursionMath.pow(2, 10), delta);
        assertEquals(27, recursionMath.pow(3, 3), delta);
        assertEquals(9.261, recursionMath.pow(2.1, 3), delta);
        assertEquals(4, recursionMath.pow(4, 1), delta);
        assertEquals(16807, recursionMath.pow(7, 5), delta);
        assertEquals(8.7181004, recursionMath.pow(1.542, 5), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void powTest_IllegalArgumentExceptionThrown() {
        recursionMath.pow(10, -1);
    }
}
