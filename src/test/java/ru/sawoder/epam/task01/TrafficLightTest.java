package ru.sawoder.epam.task01;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testing {@link TrafficLight} class.
 */
public class TrafficLightTest {
    /**
     * Test for the thrown IllegalArgumentException in constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentConstructorTest() {
        TrafficLight trafficLight = new TrafficLight(-1);
    }

    /**
     * Test for the thrown IllegalArgumentException in setter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentSetCurrentMinuteTest() {
        TrafficLight trafficLight = new TrafficLight(0);
        trafficLight.setCurrentMinute(-1);
    }

    /**
     * Test for checks logic in {@link TrafficLight#calculateCurrentSignal()}.
     */
    @Test
    public void calculateCurrentSignalTest() {
        TrafficLight trafficLight = new TrafficLight(0);
        Assert.assertEquals(Signal.RED, trafficLight.getCurrentSignal());
        trafficLight.setCurrentMinute(2);
        Assert.assertEquals(Signal.YELLOW, trafficLight.getCurrentSignal());
        trafficLight.setCurrentMinute(5);
        Assert.assertEquals(Signal.GREEN, trafficLight.getCurrentSignal());
    }
}
