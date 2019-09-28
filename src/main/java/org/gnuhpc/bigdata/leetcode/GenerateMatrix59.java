package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

//LC 54和59是同一个题目，解法则是两种做法， 54是准下一步坐标计算方法，59是不对退回来的做法，54效率更高。
public class GenerateMatrix59 {
    public int[][] generateMatrix(int n) {
        if (n == 0) return null;
        int[][] res = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int di = 0;
        int x = 0, y = 0;

        for (int i = 1; i <= n * n; i++) {
            if (!isValid(x, y, n, visited)) {
                //发现不对退回来
                x -= dr[di];
                y -= dc[di];

                //然后再往前走
                di = (di + 1) % 4;
                x += dr[di];
                y += dc[di];
            }
            System.out.println("x=" + x + " ,y=" + y);
            res[x][y] = i;
            visited[x][y] = true;
            //能往前走就往前走
            x += dr[di];
            y += dc[di];
        }

        return res;
    }

    private boolean isValid(int x, int y, int n, boolean[][] visited) {
        return (x >= 0 && x < n) && (y >= 0 && y < n) && !visited[x][y];
    }

    @Test
    public void test() {
        Utils.print2DArray(generateMatrix(3));
    }
}
