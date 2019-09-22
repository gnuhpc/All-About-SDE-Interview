package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.set.unionfindset.QuickUnion;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2019/9/22
 */
public class MaxAreaOfIsland695 {
    //TODO 二维DFS 模板
    private int[][] dr = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int r = grid.length;
        int c = grid[0].length;
        int max = 0;

        boolean[][] visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    max = Math.max(max, dfs(grid, i, j, visited));
                }
            }
        }

        return max;
    }

    private int dfs(int[][] grid, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        int res = 1;
        for (int i = 0; i < dr.length; i++) {
            int newX = x + dr[i][0];
            int newY = y + dr[i][1];
            if ((newX >= 0 && newX < grid.length) && (newY >= 0 && newY < grid[0].length)) {
                if (grid[newX][newY] == 1 && !visited[newX][newY]) {
                    res += dfs(grid, newX, newY, visited);
                }
            }
        }

        return res;
    }

    /*
    Method2: 并查集
     */

    public int maxAreaOfIsland2(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int r = grid.length;
        int c = grid[0].length;
        QuickUnion qu = new QuickUnion(r * c);
        boolean isAllZero = true;
        for (int x = 0; isAllZero && x < r; x++) {
            for (int y = 0; isAllZero && y < c; y++) {
                if (grid[x][y] != 0) {
                    isAllZero = false;
                }
            }
        }

        if (isAllZero) return 0;

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (grid[x][y] == 1) {
                    //四个方向进行操作
                    for (int k = 0; k < 4; k++) {
                        int newX = x + dr[k][0];
                        int newY = y + dr[k][1];

                        if (newX >= 0 && newX < r && newY >= 0 && newY < c) {
                            if (grid[newX][newY] == 1) {
                                qu.union(x * c + y, newX * c + newY);
                            }
                        }
                    }
                }
            }
        }

        return qu.maxCount();
    }

    @Test
    public void test() {
        System.out.println(maxAreaOfIsland2(new int[][]{
                {1, 1, 0},
                {0, 1, 1},
                {1, 1, 1}
        }));
        System.out.println(maxAreaOfIsland2(new int[][]{
                {0}
        }));
        System.out.println(maxAreaOfIsland2(new int[][]{
                {1, 0},
                {1, 0},
                {1, 1}
        }));
    }
}
