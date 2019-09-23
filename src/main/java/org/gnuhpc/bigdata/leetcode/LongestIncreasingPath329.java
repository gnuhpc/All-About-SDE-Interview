package org.gnuhpc.bigdata.leetcode;

public class LongestIncreasingPath329 {
    public int longestIncreasingPath(int[][] matrix) {

        if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
            return 0;

        int max = 0, n = matrix.length, m = matrix[0].length;

        // create a cache matrix
        int[][] cache = new int[n][m];

        // dfs search on every element in matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(dfs(matrix, Integer.MIN_VALUE, i, j, n, m, cache), max);
            }
        }
        return max;
    }

    int dfs(int[][] matrix, int tail, int i, int j, int n, int m, int[][] cache) {

        // check boundary limits
        if (i < 0 || j < 0 || i >= n || j >= m)
            return 0;

        // check tail condition
        if (matrix[i][j] <= tail)
            return 0;

        // check into cache
        if (cache[i][j] != 0)
            return cache[i][j];

        // update tail
        tail = matrix[i][j];

        //TODO DFS 写法一
        // run dfs in all four directions
        int a = dfs(matrix, tail, i - 1, j, n, m, cache) + 1;
        int b = dfs(matrix, tail, i + 1, j, n, m, cache) + 1;
        int c = dfs(matrix, tail, i, j - 1, n, m, cache) + 1;
        int d = dfs(matrix, tail, i, j + 1, n, m, cache) + 1;

        // find max and update cache
        int max = Math.max(a, Math.max(b, Math.max(c, d)));
        cache[i][j] = max;

        return max;
    }

    /*
    写法二
     */

    //TODO 二维数组遍历方式
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath2(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if(cache[i][j] != 0) return cache[i][j];
        int max = 1;
        // TODO DFS写法二
        for(int[] dir: dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }


}
