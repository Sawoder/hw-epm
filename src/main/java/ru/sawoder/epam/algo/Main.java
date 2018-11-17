package ru.sawoder.epam.algo;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Main for using {@link BinarySearch}.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class Main {
    public static void main(String[] args) {final int size = 100;
        for (Sort s : initSorts()) {
            int[] randomArray = getRandomArray(size);
            System.out.println("Random array");
            System.out.println(Arrays.toString(randomArray));
            s.sort(randomArray);
            System.out.println("Sorted array");
            System.out.println(Arrays.toString(randomArray));
        }
        System.out.println();

        int[] array = getRandomArray(size);
        Integer element = array[0];
        new QuickSort().sort(array);
        Integer[] targetArray = IntStream.of(array).boxed().toArray(Integer[]::new);
        BinarySearch binarySearch = new BinarySearch();
        System.out.println("Element " + element + " has index: " + binarySearch.search(element, targetArray));
        System.out.println(Arrays.toString(array));
    }

    private static Sort[] initSorts() {
        return new Sort[] {
                new BubbleSort(),
                new MergeSort(),
                new RadixSort(),
                new QuickSort()
        };
    }

    private static int[] getRandomArray(int size) {
        return new Random().ints(size, 0,1000).toArray();
    }
}
