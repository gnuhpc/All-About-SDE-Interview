package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class SearchMatrix74 {
    /*
    Method1 : 首先二分找出row，然后再在这个row内部二分找出target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int row = findRow(matrix, target);

        if (row == -1) return false;

        return findTargetWithInRow(matrix[row], target);
    }

    private boolean findTargetWithInRow(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            int midVal = nums[mid];
            if (midVal == target) end = mid;
            else if (midVal > target) end = mid;
            else start = mid;
        }

        if (nums[start] == target) return true;
        if (nums[end] == target) return true;
        else return false;
    }

    private int findRow(int[][] matrix, int target) {
        int start = 0;
        int end = matrix.length - 1;

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            int midVal = matrix[mid][0];

            if (midVal == target) end = mid;
            else if (midVal > target) end = mid;
            else start = mid;
        }

        if (matrix[start][0] <= target && matrix[end][0] > target) return start;
        if (matrix[end][0] <= target && target <= matrix[end][matrix[0].length - 1]) return end;
        else return -1;
    }

    /*
    Method2 : 直接二分，进行二维数组flat压平后的坐标转换
    matrix-> { 1,3,5,7,10,11,16,20,23...}
    x'=0 -> (0,0)
    x'=1 -> (0,1)
    x'=2 -> (0,2)
    x'=3 -> (0,3)
    x'=4 -> (1,0)

    => x = x'/n, y = x'%n where n = matrix[0].length，即列数
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int n = matrix[0].length;

        int start = 0;
        int end = matrix.length * matrix[0].length-1;

        while (start + 1 < end){
            int mid = (end-start)/2 + start;
            int midVal = matrix[mid/n][mid%n];

            if (midVal == target) end = mid;
            else if( midVal > target) end = mid;
            else start = mid;
        }

        if (matrix[start/n][start%n] == target) return true;
        if (matrix[end/n][end%n] == target) return true;
        else return false;
    }

    /*
    Method3 : 利用单调上升特性, 从右上角开始向左和向下找
     */

    public boolean searchMatrix3(int[][] matrix, int target) {
        if(matrix.length==0) return false;
        int x = 0;
        int y = matrix[0].length-1;
        while(x<matrix.length && y>=0) {
            if(matrix[x][y]>target) {
                y--;
            } else if(matrix[x][y]<target) {
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
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };

        System.out.println(searchMatrix(matrix,50));

    }
}
