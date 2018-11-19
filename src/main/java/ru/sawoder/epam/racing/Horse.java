package ru.sawoder.epam.racing;

import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Horse description.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class Horse implements Callable<Horse>, Comparable<Horse> {
    private int id;
    private double startSpeed;
    private double accelerate;
    private double speed;
    private int place;

    public Horse(int id) {
        this.id = id;
        double[] randomArray = new Random().doubles(2, 1, 2).toArray();
        this.startSpeed = randomArray[0];
        this.accelerate = randomArray[1];
        this.speed = startSpeed;
        this.place = 0;
    }

    public int getId() {
        return id;
    }

    public int getPlace() {
        return place;
    }

    @Override
    public Horse call() {
        Race race = Race.getInstance();
        int currentDistance = race.getDistance();
        while (currentDistance > 0) {
            currentDistance -= speed + accelerate / 2;
            speed += startSpeed + accelerate;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (Race.class) {
            place = race.getPlace();
            race.setPlace(race.getPlace() + 1);
            return this;
        }
    }

    @Override
    public int compareTo(@NotNull Horse o) {
        return this.place - o.place;
    }

    @Override
    public String toString() {
        return "Horse " + id + " place " + place;
    }
}
