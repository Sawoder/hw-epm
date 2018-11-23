package ru.sawoder.epam.algo;

/**
 * QuickSort.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class QuickSort implements Sort {
    /**
     * Invoke {@link QuickSort#doSort(int[], int, int)} with full array.
     * @param arr input array
     */
    @Override
    public void sort(int[] arr) {
        int startIndex = 0;
        int endIndex = arr.length - 1;
        doSort(arr, startIndex, endIndex);
    }

    /**
     * Divide and sort part of array relative pivot
     * @param arr input array
     * @param start minimal index in input array
     * @param end maximal index in input array
     */
    private void doSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;
        int current = i - (i - j) / 2;
        while (i < j) {
            while (i < current && (arr[i] <= arr[current])) {
                i++;
            }
            while (j > current && (arr[current] <= arr[j])) {
                j--;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                if (i == current) {
                    current = j;
                } else if (j == current) {
                    current = i;
                }
            }
        }
        doSort(arr, start, current);
        doSort(arr, current + 1, end);
    }
}
