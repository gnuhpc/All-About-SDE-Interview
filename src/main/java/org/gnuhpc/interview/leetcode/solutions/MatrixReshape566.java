package org.gnuhpc.interview.leetcode.solutions;

public class MatrixReshape566 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(nums == null|| r == 0 || c ==0) return null;
        int n = nums.length;
        int m = nums[0].length;

        if(n*m < r*c){
            return nums;
        }

        int[][] res = new int[r][c];

        int x=0, y =0;

        for(int i = 0;i <n;i++){
            for(int j = 0;j<m;j++){
                res[x][y] = nums[i][j];
                y+=1;
                if(y==c) {
                    y=0;
                    x++;
                }
            }
        }

        return res;
    }
}
