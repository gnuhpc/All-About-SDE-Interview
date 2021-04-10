package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

//LC 54和59是同一个题目，解法一致
public class GenerateMatrix59 {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        boolean[][] seen = new boolean[n][n];
        //顺时针二维坐标转换表示
        int[][] dr = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        int r = 0, c = 0, di = 0;

        int len = n * n;

        for (int i = 1; i <= len; i++) {
            ans[r][c] = i;
            seen[r][c] = true;
            //cr和cc都是可能的步骤
            int cr = r + dr[di][0];
            int cc = c + dr[di][1];
            //如果cr、cc是valid的，那么他们就是下一步的坐标
            if (0 <= cr && cr < n && 0 <= cc && cc < n && !seen[cr][cc]) {
                r = cr;
                c = cc;
            }
            //如果不是，则重新计算下一步的坐标
            else {
                di = (di + 1) % 4;
                r += dr[di][0];
                c += dr[di][1];
            }
        }
        return ans;//如果要数组则用stream：.stream().mapToInt(i -> i).toArray()
    }

    @Test
    public void test() {
        Utils.print2DArray(generateMatrix(3));
    }
}
