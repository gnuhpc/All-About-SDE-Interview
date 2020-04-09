package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2019/9/19
 */
public class SpiralOrder54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        //顺时针二维坐标转换表示
        int[][] dr = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        int r = 0, c = 0, di = 0;

        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            //cr和cc都是可能的步骤
            int cr = r + dr[di][0];
            int cc = c + dr[di][1];
            //如果cr、cc是valid的，那么他们就是下一步的坐标
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
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
        System.out.println(spiralOrder(new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        }));
    }
}
