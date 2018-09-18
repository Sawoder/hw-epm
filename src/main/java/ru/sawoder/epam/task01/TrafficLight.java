package ru.sawoder.epam.task01;

/**
 * Specifies the signal color for a certain minute.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */

public class TrafficLight {
    private int currentMinute;

    /**
     * Enum type that indicates the {@link Signal} of a traffic light.
     */
    private Signal currentSignal;

    /**
     * Creates a new {@code TrafficLight} instance by converting the given
     * minute number into a signal.
     */
    public TrafficLight(int currentMinute) {
        setCurrentMinute(currentMinute);
    }

    /**
     * Sets a new current minute and calculate a new signal.
     *
     * @param   currentMinute Must be greater or equal than 0.
     * @throws  IllegalArgumentException
     *          If the {@code currentMinute} argument is less than 0.
     */
    public void setCurrentMinute(int currentMinute) {
        if (currentMinute < 0) {
            throw new IllegalArgumentException("the current minute can't be less than 0");
        }
        this.currentMinute = currentMinute;
        calculateCurrentSignal();
    }

    public int getCurrentMinute() {
        return currentMinute;
    }

    public Signal getCurrentSignal() {
        return currentSignal;
    }

    /**
     * Calculate a new signal by the {@code currentMinute} used in the module.
     * Uses module by the current minute to determine the signal from any time.
     *
     * <p>Calculates a total {@link Signal#getSumOfGlowTime()} of the all signals
     * and calculates the signal through the remainder of the division.</p>
     *
     * <p>Checks {@code glowTime} of each signal
     * and summarized with each previous.</p>
     */
    private void calculateCurrentSignal() {
        int remainder = currentMinute % Signal.getSumOfGlowTime();
        if (remainder < Signal.RED.getGlowTime()) {
            currentSignal = Signal.RED;
        } else if (remainder < Signal.RED.getGlowTime() + Signal.YELLOW.getGlowTime()) {
            currentSignal = Signal.YELLOW;
        } else {
            currentSignal = Signal.GREEN;
        }
    }
}
