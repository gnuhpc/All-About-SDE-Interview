package org.gnuhpc.interview.leetcode.solutions;

public class ReplaceElements1299 {
    public int[] replaceElements(int[] arr) {
        int[] res = new int[arr.length];
        int max = -1;

        for (int i = arr.length - 1; i >= 0; i--) {
            res[i] = max;
            max = arr[i] > max ? arr[i] : max;
        }

        return res;
    }
}
