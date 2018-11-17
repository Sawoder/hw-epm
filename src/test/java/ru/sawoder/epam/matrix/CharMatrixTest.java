package ru.sawoder.epam.matrix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing {@link CharMatrix} class.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class CharMatrixTest {
    private char[][] array;
    private CharMatrix charMatrix;

    @Before
    public void setUpMatrix() {
        array = new char[][] {
                {'A', 'B', 'C', 'D', 'E'},
                {'F', 'G', 'H', 'I', 'J'},
                {'K', 'L', 'M', 'N', 'O'},
                {'P', 'Q', 'R', 'S', 'T'},
                {'U', 'V', 'W', 'X', 'Y'}
        };
        charMatrix = new CharMatrix();
    }

    @Test
    public void getAllAnglesTest() {
        assertEquals("AEUY", charMatrix.getAllAngles(array));
    }

    @Test
    public void substringFromCharArrayTest() {
        assertEquals("ABCDE", charMatrix.substringFromCharArray(array, 0, 0, 5));
        assertEquals("A", charMatrix.substringFromCharArray(array, 0, 0, 1));
        assertEquals("E", charMatrix.substringFromCharArray(array, 0, 4, 5));
        assertEquals("", charMatrix.substringFromCharArray(array, 0, 0, 0));
        assertEquals("Y", charMatrix.substringFromCharArray(array, 4, 4, 5));
        assertEquals("MN", charMatrix.substringFromCharArray(array, 2, 2, 4));
    }

    @Test
    public void getStarTraversalElementsTest() {
        assertEquals("ACEGIKMOQSUWY", charMatrix.getStarTraversalElements(array, true));
        assertEquals("AKUGQCMWISEOY", charMatrix.getStarTraversalElements(array, false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getStarTraversalElementsTest_IllegalArgumentExceptionThrown() {
        charMatrix.getStarTraversalElements(new char[][]{{'A'}}, true);
    }

    @Test
    public void getEvenRowElementsTest() {
        assertEquals("ACEFHJKMOPRTUWY", charMatrix.getEvenRowElements(array));
    }

    @Test
    public void getOddColumnElementsTest() {
        assertEquals("FPGQHRISJT", charMatrix.getOddColumnElements(array));
    }
}
