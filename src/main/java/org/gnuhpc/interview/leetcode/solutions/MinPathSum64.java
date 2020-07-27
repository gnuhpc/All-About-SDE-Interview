package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

public class MinPathSum64 {
    /*
    Method : dfs + Memorization //将void改为返回值的dfs + memo
     */
    private int n, m;
    private int[][] mem;

    private int[][] dirs = new int[][]{
            {1, 0}, {0, 1}
    };

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        n = grid.length;
        m = grid[0].length;

        mem = new int[n][m];

        // Bug May happen: forget to initialize
        for (int[] row : mem) {
            Arrays.fill(row, -1);
        }

        return dfs(grid, 0, 0, mem);
    }

    public int dfs(int[][] grid, int x, int y, int[][] memory) {
        // 已经搜索过的点不需要重复搜索
        if (memory[x][y] != -1) {
            return memory[x][y];
        }

        if (x == n - 1 && y == m - 1) {
            return grid[x][y];
        }

        // 开始dfs 可能的路径,目前我们只有2种可能
        int min = Integer.MAX_VALUE;
        for (int[] d : dirs) {
            int newX = x + d[0];
            int newY = y + d[1];
            if (isValid(newX, newY)) {
                min = Math.min(min, dfs(grid, newX, newY, memory));
            }
        }
        // Record the memory
        memory[x][y] = grid[x][y] + min;

        return memory[x][y];
    }

    private boolean isValid(int x, int y) {
        return x < n && x >= 0 && y < m && y >= 0;
    }

    /*
    Method 2 : 2D DP
    设数组A[row][col],Min[i][j] = min(Min[i-1][j], Min[i][j-1]) +Sample[i][j];
    注意初始条件即可。
     */

    public int minPathSum2(int[][] grid) {
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        if (m == 0) return 0;

        int[][] dp = new int[n][m];

        dp[0][0] = grid[0][0];
        //第一列的初始条件
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        //第一行的初始条件
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        //除了第一列和第一行以外的格子
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }


    @Test
    public void test() {
        System.out.println(minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
    }
}
