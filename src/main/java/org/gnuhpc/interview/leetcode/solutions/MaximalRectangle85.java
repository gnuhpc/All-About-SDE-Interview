package org.gnuhpc.interview.leetcode.solutions;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MaximalRectangle85 {
    //https://pic.leetcode-cn.com/aabb1b287134cf950aa80526806ef4025e3920d57d237c0369ed34fae83e2690-image.png
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int[] heights = new int[matrix[0].length];
        int maxArea = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //对于每一层的每一个元素，计算这个cell向上至每一层的连续1个数
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }
            //然后用84题的方法计算从底层到这个层的
            int area = new LargestRectangleArea84().largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    /*
    Method2: DFS
     */
    int n, m;
    Map<String, Integer> memo;

    public int maximalRectangle2(char[][] matrix) {
        memo = new HashMap<>();
        n = matrix.length;
        if (n == 0) return 0;
        m = matrix[0].length;
        if (m == 0) return 0;
        int h = 1, w = 1;
        //k是第k行
        return dfs(h, w, matrix, 0);
    }


    public int dfs(int h, int w, char[][] matrix, int k) {
        String key = h + "_" + w + "_" + k;
        Integer res = memo.get(key);
        if (res != null) return res;
        for (int i = k; i <= n - h; i++) {
            for (int j = 0; j <= m - w; j++) {
                if (judge(h, w, matrix, i, j)) {
                    memo.put(key, Math.max(Math.max(h * w, dfs(h + 1, w, matrix, i)), dfs(h, w + 1, matrix, i)));
                    return memo.get(key);
                }
            }
        }
        return 0;
    }

    public boolean judge(int h, int w, char[][] matrix, int i, int j) {
        for (int a = i; a < i + h; a++) {
            for (int b = j; b < j + w; b++) {
                if (matrix[a][b] == '0')
                    return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        maximalRectangle(matrix);
    }
}
