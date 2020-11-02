package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/31
 */
public class GetLeastNumbers40 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        int[] count = new int[10001];
        for (int i : arr) {
            count[i]++;
        }

        int[] res = new int[k];
        int idx = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0 && idx < k) {
                res[idx] = i;
                idx++;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }
}
