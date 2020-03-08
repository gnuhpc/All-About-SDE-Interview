package org.gnuhpc.bigdata.leetcode.solutions;

public class SetZeroes73 {
    //状态机方法：中间状态是将要变为0，等所有遍历后把中间状态向前实质改为0
    public void setZeroes(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;
        int MODIFIED = 0; //对此其在不在数组中后边要check一遍

        while (checkExist(MODIFIED, matrix)) {
            MODIFIED -= 1024; //随便给个偏移量就行
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

    //避免中间状态的数字在里面
    private boolean checkExist(int modified, int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == modified) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 这道题中说的空间复杂度为O(mn)的解法自不用多说，直接新建一个和matrix等大小的矩阵，然后一行一行的扫，只要有0，就将新建的矩阵的对应行全赋0，行扫完再扫列，然后把更新完的矩阵赋给matrix即可，这个算法的空间复杂度太高。将其优化到O(m+n)的方法是，用一个长度为m的一维数组记录各行中是否有0，用一个长度为n的一维数组记录各列中是否有0，最后直接更新matrix数组即可。这道题的要求是用O(1)的空间，那么我们就不能新建数组，我们考虑就用原数组的第一行第一列来记录各行各列是否有0.
     * <p>
     * - 先扫描第一行第一列，如果有0，则将各自的flag设置为true
     * - 然后扫描除去第一行第一列的整个数组，如果有0，则将对应的第一行和第一列的数字赋0
     * - 再次遍历除去第一行第一列的整个数组，如果对应的第一行和第一列的数字有一个为0，则将当前值赋0
     * - 最后根据第一行第一列的flag来更新第一行第一列
     * - 自己画个图，很清晰
     */
    public void setZeroes2(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean empty_row0 = false;
        boolean empty_col0 = false;
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0) {
                empty_row0 = true;
                break;
            }
        }

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                empty_col0 = true;
                break;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (empty_row0) {
            for (int i = 0; i < cols; i++) {
                matrix[0][i] = 0;
            }
        }

        if (empty_col0) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
