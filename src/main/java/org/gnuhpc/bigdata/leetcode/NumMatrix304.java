package org.gnuhpc.bigdata.leetcode;

public class NumMatrix304 {

    //TODO 矩阵求和的一个套路,就是减切出来这个面积
    // 具体实施上， shift by 1 to add 0
    private int[][] summatrix;

    public NumMatrix304(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        summatrix = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                summatrix[r + 1][c + 1] = summatrix[r + 1][c] + summatrix[r][c + 1] + matrix[r][c] - summatrix[r][c];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return summatrix[row2 + 1][col2 + 1] - summatrix[row1][col2 + 1] - summatrix[row2 + 1][col1] + summatrix[row1][col1];
    }

}
