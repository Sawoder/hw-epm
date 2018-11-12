package ru.sawoder.epam.task04;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing {@link RecursionNaturalNumber} class.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class RecursionNaturalNumberTest {
    private RecursionNaturalNumber recursionNaturalNumber;

    @Before
    public void setUp() {
        recursionNaturalNumber = new RecursionNaturalNumber();
    }

    @Test
    public void sumOfNumberTest() {
        assertEquals(1, recursionNaturalNumber.sumOfNumber(1));
        assertEquals(6, recursionNaturalNumber.sumOfNumber(123));
        assertEquals(10, recursionNaturalNumber.sumOfNumber(1234));
        assertEquals(36, recursionNaturalNumber.sumOfNumber(9999));
        assertEquals(3, recursionNaturalNumber.sumOfNumber(3));
        assertEquals(1, recursionNaturalNumber.sumOfNumber(10000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void sumOfNumberTest_IllegalArgumentExceptionThrown() {
        recursionNaturalNumber.sumOfNumber(0);
    }

    @Test
    public void countOfNumberTest() {
        assertEquals(1, recursionNaturalNumber.countOfNumber(1));
        assertEquals(4, recursionNaturalNumber.countOfNumber(1000));
        assertEquals(4, recursionNaturalNumber.countOfNumber(2435));
        assertEquals(9, recursionNaturalNumber.countOfNumber(987654321));
    }

    @Test(expected = IllegalArgumentException.class)
    public void countOfNumberTest_IllegalArgumentExceptionThrown() {
        recursionNaturalNumber.countOfNumber(0);
    }
}
