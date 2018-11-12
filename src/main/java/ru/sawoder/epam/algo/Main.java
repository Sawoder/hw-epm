package ru.sawoder.epam.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Main for using {@link BinarySearch}.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int[] tmp = Arrays.asList(strings).stream().mapToInt(Integer::parseInt).toArray();
            Integer[] targetArray = IntStream.of(tmp).boxed().toArray(Integer[]::new);

            BinarySearch binarySearch = new BinarySearch();
            System.out.print("Element for find: ");
            int element = Integer.parseInt(reader.readLine());
            System.out.println("Element has index: " + binarySearch.search(element, targetArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
