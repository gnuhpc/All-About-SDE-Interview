package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/11/5
 */
public class CanMakeArithmeticProgression1520 {
    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length <= 1) return true;
        Arrays.sort(arr);
        int dif = arr[1] - arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] != dif) return false;
        }

        return true;
    }
}
