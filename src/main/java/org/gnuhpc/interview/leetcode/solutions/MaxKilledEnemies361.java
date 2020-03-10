package org.gnuhpc.interview.leetcode.solutions;

public class MaxKilledEnemies361 {
    private int r;
    private int c;
    private int max = Integer.MIN_VALUE;
    private int[][] dr = new int[][]{
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        if (grid[0].length == 0) return 0;

        r = grid.length;
        c = grid[0].length;

        boolean noBlank = true;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '0') {
                    noBlank = false;
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        count += count(grid, i, j, k);
                    }
                    max = Math.max(max, count);
                }
            }
        }

        if (noBlank) return 0;

        return max;
    }

    private int count(char[][] grid, int x, int y, int k) {
        int count = 0;

        while (isValid(x, y)) {
            if (grid[x][y] == 'E') count++;
            if (grid[x][y] == 'W') break;
            x += dr[k][0];
            y += dr[k][1];
        }

        return count;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}
