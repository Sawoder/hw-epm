package ru.sawoder.epam.task03;

import org.jetbrains.annotations.NotNull;

/**
 * Class for char matrix operations.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class CharMatrix {
    /**
     * The string of all angles of the char matrix.
     *
     * @param array 2D array of chars
     * @return String of all angles of array
     */
    public String getAllAngles(@NotNull char[][] array) {
        int i = array.length - 1;
        int j = array[0].length - 1;
        StringBuilder sb = new StringBuilder();
        return sb.append(array[0][0])
                .append(array[0][j])
                .append(array[i][0])
                .append(array[i][j]).toString();
    }

    /**
     * Get the string by char matrix with specified boundaries.
     *
     * @param array 2D array of chars
     * @param row number of the row of the array
     * @param from from which element
     * @param to to which element
     * @return String of sub char array
     */
    public String substringFromCharArray(@NotNull char[][] array, int row, int from, int to) {
        return String.valueOf(array[row], from, to - from);
    }

    /**
     * Get traversal char matrix by star pattern.
     *
     * @param array 2D array of chars 5 x 5
     * @param isDefaultTraversal How to traversal the array
     *                           true: By row
     *                           false: By column
     * @throws IllegalArgumentException Matrix must contains 5 rows and 5 columns
     * @return String of star traversal
     */
    public String getStarTraversalElements(@NotNull char[][] array, boolean isDefaultTraversal) {
        if (array.length != 5 && array[0].length != 5) {
            throw new IllegalArgumentException("Matrix must be 5 x 5.");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (isEven(i) == isEven(j)) {
                    sb.append(isDefaultTraversal ?
                            array[i][j] :
                            array[j][i]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Get string by each even element with traversal by row.
     *
     * @param array 2D array of chars
     * @return String of even elements of array
     */
    public String getEvenRowElements(@NotNull char[][] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (isEven(j)) {
                    sb.append(array[i][j]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Get string by each odd element with traversal by column.
     *
     * @param array 2D array of chars
     * @return String of odd elements of array
     */
    public String getOddColumnElements(@NotNull char[][] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (!isEven(j)) {
                    sb.append(array[j][i]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Checks the argument for even.
     *
     * @param number Any number
     * @return The number even or not.
     */
    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
