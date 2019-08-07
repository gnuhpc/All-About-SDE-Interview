package org.gnuhpc.bigdata.leetcode;

public class SetZeroes73 {
    //状态机方法：中间状态是将要变为0，等所有遍历后把中间状态向前实质改为0
    public void setZeroes(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;
        int MODIFIED = 0; //对此其在不在数组中后边要check一遍

        while (checkExist(MODIFIED, matrix)){
            MODIFIED-=1024; //随便给个偏移量就行
        }


        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                //找到等于 0 的位置
                if (matrix[r][c] == 0) {
                    // 将需要变成 0 的行和列改为之前定义的数字
                    // 如果是 0 不要管，因为我们要找 0 的位置
                    for (int k = 0; k < col; k++) {
                        if (matrix[r][k] != 0) {
                            matrix[r][k] = MODIFIED;
                        }
                    }
                    for (int k = 0; k < row; k++) {
                        if (matrix[k][c] != 0) {
                            matrix[k][c] = MODIFIED;
                        }
                    }
                }
            }
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // 将是定义的数字的位置变成 0
                if (matrix[r][c] == MODIFIED) {
                    matrix[r][c] = 0;
                }
            }
        }
    }

    private boolean checkExist(int modified, int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int r=0 ;r<row;r++){
            for (int c = 0; c < col; c++) {
                if (matrix[r][c]==modified){
                    return true;
                }
            }
        }

        return false;
    }
}
