package org.gnuhpc.bigdata.leetcode.solutions;

public class LongestIncreasingPath329 {
    //二维数组遍历方式
    public  int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int[][] cache;
    private int     m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j));
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int x, int y) {
        if (cache[x][y] != 0) return cache[x][y];
        int max = 1;

        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (isValid(x, y, newX, newY, matrix)) {
                max = Math.max(max, 1 + dfs(matrix, newX, newY));
            }
        }
        cache[x][y] = max;
        return max;
    }

    public boolean isValid(int x, int y, int newX, int newY, int[][] matrix) {
        return (newX >= 0 && newX < m && newY >= 0 && newY < n) && matrix[newX][newY] > matrix[x][y];
    }
}
