package ru.sawoder.epam.algo;

/**
 * BubbleSort.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class BubbleSort implements Sort {
    /**
     * Method sort array using swap compare element by element
     * @param arr input array
     */
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
