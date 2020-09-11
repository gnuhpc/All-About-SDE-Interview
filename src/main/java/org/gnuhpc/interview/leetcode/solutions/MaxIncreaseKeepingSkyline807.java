package org.gnuhpc.interview.leetcode.solutions;

public class MaxIncreaseKeepingSkyline807 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int ans = 0;
        int len = grid.length;
        //求每行和每列的最大值
        int [] rowMax = new int[len];
        int [] columnMax = new int [len];
        for (int i = 0; i < len; i++){
            for (int j = 0; j < len; j++) {
                if(grid[i][j] > rowMax[i])
                    rowMax[i] = grid[i][j];
                if(grid[i][j] > columnMax[j])
                    columnMax[j] = grid[i][j];
            }
        }
        //更新可增加的大小
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                ans += Math.min(rowMax[i],columnMax[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
