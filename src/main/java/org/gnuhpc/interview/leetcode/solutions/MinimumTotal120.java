package org.gnuhpc.interview.leetcode.solutions;

import java.util.List;

/**
 * Copyright gnuhpc 19-7-11
 */

public class MinimumTotal120 {
    //从上到下记忆化搜索 ，要掌握
    private int n;
    private int[][] minSum;
    private List<List<Integer>> triangle;

    private int search(int x, int y) {
        if (x >= n) {
            return 0;
        }

        if (minSum[x][y] != Integer.MAX_VALUE) {
            return minSum[x][y];
        }

        minSum[x][y] = Math.min(search(x + 1, y), search(x + 1, y + 1))
                + triangle.get(x).get(y);
        return minSum[x][y];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return -1;
        }
        if (triangle.get(0) == null || triangle.get(0).size() == 0) {
            return -1;
        }

        this.n = triangle.size();
        this.triangle = triangle;
        this.minSum = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minSum[i][j] = Integer.MAX_VALUE;
            }
        }

        return search(0, 0);
    }

    //从下到上动归
    public int minimumTotal2(int[][] triangle) {
        if (triangle == null || triangle.length == 0) {
            return -1;
        }
        if (triangle[0] == null || triangle[0].length == 0) {
            return -1;
        }

        // state: f[x][y] = minimum path value from x,y to bottom
        int n = triangle.length;
        int[][] f = new int[n][n];

        // initialize
        for (int i = 0; i < n; i++) {
            f[n - 1][i] = triangle[n - 1][i];
        }

        // bottom up
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle[i][j];
            }
        }

        // answer
        return f[0][0];
    }

    // add by tina,从上往下DP，二维DP标准模板写法，开一个二维数组[n][n]
    public int minimumTotal3(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0) == null || triangle.get(0).size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int sum[][] = new int[n][n];
        sum[0][0] = triangle.get(0).get(0);
        // 边界的处理
        for (int i = 1; i < n; i++) {
            sum[i][0] = triangle.get(i).get(0) + sum[i - 1][0];
            sum[i][i] = triangle.get(i).get(i) + sum[i - 1][i - 1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                sum[i][j] = triangle.get(i).get(j) + Math.min(sum[i - 1][j], sum[i - 1][j - 1]);
            }
        }

        int min = sum[n - 1][0];
        for (int k = 1; k < n; k++) {
            min = Math.min(min, sum[n - 1][k]);
        }
        return min;
    }

    // add by tina,从下往上DP，但是只用开一个一维数组[n+1]，比2改进
    public int minimumTotal4(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];//初始值为0
        for (int i = triangle.size() - 1; i >= 0; i--) {//n-1行开始
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    // 跟上面4思路一样，但是写法略有差异，体现在初始化处理上，相对更好理解一些
    public int minimumTotal5(List<List<Integer>> triangle) {
        int n = triangle.size();
        int sum = 0;
        int[] res = new int[triangle.size()];
        //第一遍，先把所有最后一行的数字加入res，
        for (int j = n - 1; j >= 0; j--) {
            res[j] = triangle.get(n - 1).get(j);
        }
        //第二遍，从底层开始，DP
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }

}
