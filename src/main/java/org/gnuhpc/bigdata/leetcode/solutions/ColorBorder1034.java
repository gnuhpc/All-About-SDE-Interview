package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class ColorBorder1034 {
    int r, c;
    int[][]     dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    List<int[]> temp;

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        r = grid.length;
        c = grid[0].length;

        temp = new ArrayList<>();


        dfs(grid, r0, c0, grid[r0][c0]);

        for (int[] cor : temp) {
            grid[cor[0]][cor[1]] = -grid[cor[0]][cor[1]];
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] < 0)
                    grid[i][j] = color;
            }
        }

        return grid;
    }

    private void dfs(int[][] grid, int x, int y, int color) {

        grid[x][y] = -color;

        for (int[] d : dirs) {
            int newX = x + d[0];
            int newY = y + d[1];

            if (isValid(grid, newX, newY, color)) {
                dfs(grid, newX, newY, color);
            }
        }

        if (x != 0 && x != r - 1 && y != 0 && y != c - 1) {
            boolean flag = true;
            for (int[] d : dirs) {
                if (grid[x + d[0]][y + d[1]] != -color) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                temp.add(new int[]{x, y});
            }
        }
    }

    private boolean isValid(int[][] grid, int x, int y, int color) {
        return (x >= 0 && x < r && y >= 0 && y < c) && (grid[x][y] == color);
    }
}
