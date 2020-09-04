package org.gnuhpc.interview.misc;

import org.junit.Test;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/4/29
 */
public class TestCase {
    @Test
    public void test() {
        System.out.println(solution(2, 3, new int[]{0, 0, 1, 1, 2}));
    }

    public String solution(int U, int L, int[] C) {
        int cols = C.length;
        int[][] res = new int[2][cols];
        Arrays.fill(res[0], -1);
        Arrays.fill(res[1], -1);

        int len = 0;
        for (int i = 0; i < cols; i++) {
            if (C[i] == 2) {
                res[0][i] = 1;
                res[1][i] = 1;
                U--;
                L--;
                len++;
            } else if (C[i] == 0) {
                res[0][i] = 0;
                res[1][i] = 0;
                len++;
            }
        }

        if (len != cols) {
            int[] idxes = new int[cols - len];
            int j = 0;
            for (int i = 0; i < cols; i++) {
                if (C[i] == 1) idxes[j++] = i;
            }

            boolean result = dfs(U, L, res, idxes, 0);

            if (!result) return "IMPOSSIBLE";
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : res) {
            for (int n : row) sb.append(n).append(",");
        }

        return sb.substring(0, sb.length() - 1);
    }

    private boolean dfs(int U, int L, int[][] res, int[] idxes, int start) {

        if (start == idxes.length) {
            return U == 0 && L == 0;
        }
        if (U < 0 || L < 0) {
            return false;
        }
        boolean res1 = false;
        boolean res2 = false;
        for (int i = start; i < idxes.length; i++) {
            res[0][idxes[start]] = 1;
            res[1][idxes[start]] = 0;
            res1 = dfs(U - 1, L, res, idxes, start + 1);
            if (res1) break;
            res[0][idxes[start]] = -1;
            res[1][idxes[start]] = -1;

            res[0][idxes[start]] = 0;
            res[1][idxes[start]] = 1;
            res2 = dfs(U, L - 1, res, idxes, start + 1);
            if (res2) break;
            res[0][idxes[start]] = -1;
            res[1][idxes[start]] = -1;
        }

        return res1 || res2;
    }
}
