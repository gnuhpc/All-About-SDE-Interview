package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/9/14
 */
public class KWeakestRows1377 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        List<Integer>[] bucket = new ArrayList[n + 1];

        for (int row = 0; row < m; row++) {
            int score = 0;
            for (int col = 0; col < n; col++) {
                score += mat[row][col];
            }

            if (bucket[score] == null) {
                bucket[score] = new ArrayList<>();
            }
            bucket[score].add(row);
        }

        int[] res = new int[k];


        for (int i = 0, j = 0; i <= n && j < k; i++) {
            List<Integer> b = bucket[i];
            if (b == null) continue;

            for (Integer row : b) {
                res[j] = row;
                j++;
                if (j == k) break;
            }
        }


        return res;
    }
}
