package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class MaximalRectangle85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        //对于每一层的每一个元素，计算这个cell向上至每一层的连续1个数
        int[] heights = new int[matrix[0].length];
        int maxArea = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //每一层从上到下开始计算leetcode84的最大面积
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }
            int area = new LargestRectangleArea84().largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
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
