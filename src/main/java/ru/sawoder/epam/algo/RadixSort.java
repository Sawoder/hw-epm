package ru.sawoder.epam.algo;

import java.util.Arrays;

/**
 * RadixSort description.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class RadixSort implements Sort {
    @Override
    public void sort(int[] arr) {
        int m = getMax(arr);
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    private int getMax(int arr[]) {
        int mx = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > mx) {
                mx = arr[i];
            }
        }
        return mx;
    }

    private void countSort(int arr[], int exp) {
        int n = arr.length;
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        for (i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}
