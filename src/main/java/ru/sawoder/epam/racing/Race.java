package ru.sawoder.epam.racing;

import java.util.Random;

/**
 * Race description.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class Race {
    private volatile int distance;
    private volatile int place;

    private static volatile Race instance;

    public static Race getInstance() {
        Race localInstance = instance;
        if (localInstance == null) {
            synchronized (Race.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Race();
                }
            }
        }
        return localInstance;
    }

    private Race() {
        this.distance = new Random().nextInt(10000);
        this.place = 1;
    }

    public int getDistance() {
        return distance;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
