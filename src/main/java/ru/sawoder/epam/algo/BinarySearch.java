package ru.sawoder.epam.algo;

/**
 * Binary search with using generic method.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class BinarySearch {
    /**
     * Simple binary search algorithm with {@link Comparable} types.
     *
     * @param element What try to find
     * @param array Array with data
     * @param <T> {@link Comparable} type
     * @return index of array if element was found else return -1.
     */
    public <T extends Comparable<T>> int search(T element, T[] array) {
        int lower = 0;
        int higher = array.length - 1;

        while (lower <= higher) {
            int mid = lower + (higher - lower) / 2;

            if (element.compareTo(array[mid]) < 0) {
                higher = mid - 1;
            } else if (element.compareTo(array[mid]) > 0) {
                lower = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
