package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/12/6
 */
public class LongestLine562 {
    Map<String, Integer> memo = new HashMap<>();

    private int find(int[][] m, int i, int j, int dir) {
        String key = i + "_" + j + "_" + dir;
        if (memo.containsKey(key)) return memo.get(key);

        int len = 0;

        if (i >= m.length || j >= m[i].length || i < 0 || j < 0) {
            return 0;
        }

        if (m[i][j] == 0) {
            return 0;
        }
        int res = 0;
        switch (dir) {
            case 0:
                res = 1 + find(m, i + 1, j, 0);
                break;
            case 1:
                res = 1 + find(m, i, j + 1, 1);
                break;
            case 2:
                res = 1 + find(m, i + 1, j + 1, 2);
                break;
            default:
                res = 1 + find(m, i + 1, j - 1, 3);
        }

        memo.put(key, res);

        return res;
    }

    public int longestLine(int[][] M) {
        int max = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] == 1) {
                    max = Math.max(find(M, i, j, 0), max);
                    max = Math.max(find(M, i, j, 1), max);
                    max = Math.max(find(M, i, j, 2), max);
                    max = Math.max(find(M, i, j, 3), max);
                }
            }
        }
        return max;
    }
}
