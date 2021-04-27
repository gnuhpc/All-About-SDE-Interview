package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/4/27
 */
public class Decode1720 {
    public int[] decode(int[] encoded, int first) {
        int[] arr = new int[encoded.length + 1];
        arr[0] = first;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = encoded[i - 1] ^ arr[i - 1];
        }
        return arr;
    }
}
