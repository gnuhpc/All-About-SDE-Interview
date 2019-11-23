package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * Copyright gnuhpc 2019/10/2
 */

//https://www.youtube.com/watch?v=LP7YQdT5eps
//TODO shuffle算法
//https://www.itcodemonkey.com/article/15641.html
public class Shuffle384 {
    private int[]  nums;
    private Random r;

    public Shuffle384(int[] nums) {
        this.nums = nums;
        r = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        if (nums == null) return null;
        int[] a = Arrays.copyOf(nums, nums.length);
        int n = a.length;
        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = n - 1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = r.nextInt(i + 1);

            // Swap arr[i] with the element at random index
            swap(a, i, j);
        }
        return a;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
