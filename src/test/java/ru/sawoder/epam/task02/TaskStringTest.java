package ru.sawoder.epam.task02;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TaskStringTest description.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class TaskStringTest {
    private TaskString taskString;

    @Before
    public void setUp() {
        taskString = new TaskString("AaBbCcDDDhe");
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void getKthChar_InvalidKth_ExceptionThrown() {
        taskString.getKthChar(12);
    }

    @Test
    public void countSameNeighbors_CheckCount() {
        Assert.assertEquals(2, taskString.countSameNeighbors());
    }

    @Test
    public void swapSecondAndFifthChar_CheckSwap() {
        Assert.assertEquals("ACBbacDDDhe", taskString.swapSecondAndFifthChar());
    }
}
