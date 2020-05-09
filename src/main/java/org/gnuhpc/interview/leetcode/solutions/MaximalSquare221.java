package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class MaximalSquare221 {
    /*
    Method1: 暴力解法，固定左上角
     */


    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    // 遇到一个 1 作为正方形的左上角
                    maxSide = Math.max(maxSide, 1);
                    // 计算可能的最大正方形边长
                    int currentMaxSide = Math.min(rows - i, columns - j);
                    for (int k = 1; k < currentMaxSide; k++) {
                        // 判断新增的一行一列是否均为 1
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return maxSide * maxSide;
    }

    /*
    Method2: DFS
    链接：https://leetcode-cn.com/problems/maximal-square/solution/dfsbian-li-di-yi-shi-jian-mei-xiang-dao-dp-by-newc/
    正方形的边长从 1 开始，找到符合条件的就在当前 行 直接把正方形边长加一，继续找
     */
    int n, m;

    public int maximalSquare2(char[][] matrix) {
        n = matrix.length;
        if (n == 0) return 0;
        m = matrix[0].length;
        if (m == 0) return 0;
        int maLen = 1;
        return dfs(maLen, matrix, 0);
    }

    public int dfs(int maLen, char[][] matrix, int k) {
        for (int i = k; i <= n - maLen; i++) {
            for (int j = 0; j <= m - maLen; j++) {
                if (judge(maLen, matrix, i, j))
                    return Math.max(maLen * maLen, dfs(maLen + 1, matrix, i));
            }
        }
        return 0;
    }

    public boolean judge(int maLen, char[][] matrix, int i, int j) {
        if (maLen == 1 && matrix[i][j] == '1') return true;
        for (int w = i; w < i + maLen; w++) {
            for (int h = j; h < j + maLen; h++) {
                if (matrix[w][h] == '0')
                    return false;
            }
        }
        return true;
    }

    /*
    Method3: DP
     */
    public int maximalSquare3(char[][] a) {
        if (a.length == 0) return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i][j] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }

    @Test
    public void test() {
        System.out.println(maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }));
    }


}
