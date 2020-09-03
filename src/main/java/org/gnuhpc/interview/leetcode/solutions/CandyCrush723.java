package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/3
 */
public class CandyCrush723 {
    public int[][] candyCrush(int[][] board) {
        int r = board.length, c = board[0].length;

        //标记，这个很有技巧，因为在同一个方块上可能横竖都能标记，横方向标记完了，纵方向标记的时候能知道之前的数值，因此使用负值，并进行绝对值判断
        while (true) {
            boolean crush = false;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    // check horizontal line
                    int v = Math.abs(board[i][j]);
                    if (v == 0) continue;
                    if (j + 2 < c && v == Math.abs(board[i][j + 1]) && v == Math.abs(board[i][j + 2])) {
                        crush = true;
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -v;
                    }
                    // check vertical line
                    if (i + 2 < r && v == Math.abs(board[i + 1][j]) && v == Math.abs(board[i + 2][j])) {
                        crush = true;
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -v;
                    }
                }
            }

            if (!crush) return board;

            // crush candy
            for (int j = 0; j < c; j++) {//一列一列来
                int id = r - 1;
                for (int i = r - 1; i >= 0; i--) {
                    if (board[i][j] > 0) {
                        board[id--][j] = board[i][j];
                    }
                }
                while (id >= 0) board[id--][j] = 0;//补零
            }
        }
    }
}
