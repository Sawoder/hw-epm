package ru.sawoder.epam.racing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HorseRacing {
    public List<Horse> start(int count, int bet, int id) {
        ExecutorService executor = Executors.newFixedThreadPool(count);
        List<Future<Horse>> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Callable<Horse> callable = new Horse(i);
            Future<Horse> future = executor.submit(callable);
            list.add(future);
        }
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            try {
                horses.add(list.get(i).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        Collections.sort(horses);
        return horses;
    }

    public static void main(String[] args) {
        int money = 100;
        HorseRacing service = new HorseRacing();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (money > 0) {
                System.out.println("Count of horses");
                int count = Integer.parseInt(reader.readLine());
                System.out.println("Bet");
                String tmp = reader.readLine();
                int bet = Integer.parseInt(tmp);
                System.out.println("Horse id {0, " + (count - 1) + "}");
                tmp = reader.readLine();
                int id = Integer.parseInt(tmp);
                List<Horse> horses = service.start(count, bet, id);
                if (horses.get(0).getId() == id) {
                    money += bet;
                    System.out.println("You win! " + money);
                } else {
                    money -= bet;
                    System.out.println("You lose! " + money);
                }
                System.out.println("Places");
                for (Horse horse : horses) {
                    System.out.println(horse.getPlace() + ": " + "Horse " + horse.getId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
