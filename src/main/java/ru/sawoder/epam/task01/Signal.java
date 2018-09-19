package ru.sawoder.epam.task01;

/**
 * Enum type for signals of {@link TrafficLight}.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public enum Signal {
    RED("Red Signal", 2),
    YELLOW("Yellow Signal", 3),
    GREEN("Green Signal", 5);

    private final String signalTitle;

    /**
     * How long the signal is on.
     */
    private final int duration;

    Signal(String signalTitle, int duration) {
        this.signalTitle = signalTitle;
        this.duration = duration;
    }

    /**
     * Calculates the total running time of one cycle of signals.
     *
     * @return Returns the sum of {@code duration}.
     */
    public static int sumOfDuration() {
        int sum = 0;
        for (Signal signal : Signal.values()) {
            sum += signal.duration;
        }
        return sum;
    }

    public String getSignalTitle() {
        return signalTitle;
    }

    public int getDuration() {
        return duration;
    }
}
