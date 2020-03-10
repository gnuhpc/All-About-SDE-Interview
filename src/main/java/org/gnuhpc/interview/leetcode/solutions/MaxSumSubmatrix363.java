package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.TreeSet;

public class MaxSumSubmatrix363 {
    //https://www.programcreek.com/2016/08/maximum-sum-of-subarray-close-to-k/
    //https://www.youtube.com/watch?v=-FgseNO-6Gk

    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int result = Integer.MIN_VALUE;

        for (int L = 0; L < n; L++) {
            int[] each = new int[m];
            for (int l = L; l >= 0; l--) {

                for (int r = 0; r < m; r++) {
                    each[r] += matrix[r][l];
                }

                result = Math.max(result, getLargestSumCloseToK(each, k));
            }
        }

        return result;
    }

    public int getLargestSumCloseToK(int[] arr, int k) {
        int sum = 0;
        TreeSet<Integer> set = new TreeSet<>();
        int result = Integer.MIN_VALUE;
        set.add(0);

        for (int n : arr) {
            sum += n;
            set.add(sum);
        }

        for (Integer s : set) {
            Integer ceiling = set.ceiling(s - k);
            if (ceiling != null) {
                result = Math.max(result, s - ceiling);
            }
        }

        return result;
    }

    @Test
    public void test() {
        System.out.println(getLargestSumCloseToK(new int[]{1, 0, 1}, 2));
    }

}
