package org.gnuhpc.bigdata.leetcode;

public class NumMatrix304 {
    int[][] summatrix;
    public NumMatrix304(int[][] matrix) {
        if(matrix==null || matrix.length==0)
            return;
        summatrix = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(i==0 && j==0)
                    summatrix[i][j] = matrix[i][j];
                else if(i==0)
                    summatrix[i][j] = summatrix[i][j-1] + matrix[i][j];
                else if(j==0)
                    summatrix[i][j] = summatrix[i-1][j] + matrix[i][j];
                else
                    summatrix[i][j] = summatrix[i-1][j] + summatrix[i][j-1] - summatrix[i-1][j-1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(row1==0 && col1==0)
            return summatrix[row2][col2];
        else if(row1==0)
            return summatrix[row2][col2] - summatrix[row2][col1-1];
        else if(col1==0)
            return summatrix[row2][col2] - summatrix[row1-1][col2];
        else
            return summatrix[row2][col2] - summatrix[row1-1][col2] - summatrix[row2][col1-1] + summatrix[row1-1][col1-1];
    }

}
