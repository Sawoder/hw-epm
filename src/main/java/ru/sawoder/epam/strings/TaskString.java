package ru.sawoder.epam.strings;

/**
 * TaskString description.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class TaskString {
    private String task;

    public TaskString(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    /**
     * 9.13
     *
     * @return third char from string
     */
    public char getThirdChar() {
        return task.charAt(2);
    }

    /**
     * 9.14
     *
     * @return last char from string
     */
    public char getLastChar() {
        return task.charAt(task.length() - 1);
    }

    /**
     * 9.15
     *
     * @param k index of string
     * @return kth char
     */
    public char getKthChar(int k) {
        return task.charAt(k - 1);
    }

    /**
     * 9.64
     *
     * @return count of near neighbors
     */
    public int countSameNeighbors() {
        int count = 0;
        for (int i = 0; i < task.length() - 1; i++) {
            if (task.charAt(i) == task.charAt(i + 1)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 9.100
     *
     * @return new string with swapped second and fifth element
     */
    public String swapSecondAndFifthChar() {
        char[] chars = task.toCharArray();
        char tmp = chars[1];
        chars[1] = chars[4];
        chars[4] = tmp;
        return new String(chars);
    }

    /**
     * Home Work
     * @return reversed each word in the string
     */
    public String reverseEachWord() {
        String[] strings = task.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(new StringBuilder(strings[i]).reverse());
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Home Work
     *
     * @return reverse string
     */
    public String reverseString() {
        return new StringBuilder(task).reverse().toString();
    }
}
