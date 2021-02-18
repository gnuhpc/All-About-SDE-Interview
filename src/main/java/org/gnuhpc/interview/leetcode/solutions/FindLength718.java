package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FindLength718 {
    public int findLength(int[] A, int[] B) {
        int max = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /*
    Method2: 暴力
     */

    public int findLength2(int[] A, int[] B) {
        int sum = 0;
        int max = 0;
        int start = 0;
        for (int i = 0, j = 0; i < A.length && j < A.length; i++, j++) {
            if (A[i] == B[j]) {
                sum++;
                max = Math.max(max, sum);
            } else {
                sum = 0;
            }

            if (j == A.length - 1) {
                j = start;
                start++;
                i = -1;
                sum = 0;
            }
            if (A.length - start <= max)
                break;
        }

        sum = 0;
        start = 0;
        for (int i = 0, j = 0; i < A.length && j < A.length; i++, j++) {
            if (B[i] == A[j]) {
                sum++;
                max = Math.max(max, sum);
            } else {
                sum = 0;
            }

            if (j == A.length - 1) {
                j = start;
                start++;
                i = -1;
                sum = 0;
            }
            if (A.length - start <= max)
                break;
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
    }
}
