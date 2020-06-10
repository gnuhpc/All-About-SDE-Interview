package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

public class SearchMatrix240 {
    /*
    Method1 : 二分法，找出最后一个可能存在target的行，然后向上对每行进行二分查找
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int row = findRow(matrix, target);

        if (row >= 0) return true;
        else {
            row = -(row + 1);
            row--;
        }

        for (int i = row; i >= 0; i--) {
            if (findTargetWithInRow(matrix[i], target)) {
                return true;
            }
        }

        return false;
    }

    private boolean findTargetWithInRow(int[] nums, int target) {
        return Arrays.binarySearch(nums, target) >= 0;
    }

    private int findRow(int[][] matrix, int target) {
        int[] row = new int[matrix.length];
        for (int i = 0; i < row.length; i++) {
            row[i] = matrix[i][0];
        }

        return Arrays.binarySearch(row, target);
    }

    /*
    Method2 : 右上角划范围的方法 和74一模一样
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int x = 0;
        int y = matrix[0].length - 1;
        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] > target) {
                y--;
            } else if (matrix[x][y] < target) {
                x++;
            } else {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println(searchMatrix(matrix, 5));
        System.out.println(searchMatrix(matrix, 20));

    }
}
