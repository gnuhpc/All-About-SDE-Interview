package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 2019/10/7
 */
public class PhoneDirectory379 {
    private int[] numbers;

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectory379(int maxNumbers) {
        numbers = new int[maxNumbers];
        for (int i = 0; i < maxNumbers; i++) {
            numbers[i] = i;
        }
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        int res = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != -1) {
                res = i;
                numbers[i] = -1;
                break;
            }
        }

        return res;
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        return numbers[number] != -1;
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        numbers[number] = number;
    }
}
