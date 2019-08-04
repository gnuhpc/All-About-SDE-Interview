package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

public class GameOfLife289 {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                changeBorder(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
            }
        }

    }

    public void changeBorder(int[][] board, int x, int y) {
        int row = board.length;
        int col = board[0].length;

        int total = 0; // 这是记录该细胞周围活细胞的数量

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (i >= 0 && i < row && j >= 0 && j < col) {
                    if (board[i][j] > 0) {
                        total++;
                    }
                }
            }
        }
        //把即将死亡的 活细胞 置为 2
        if (board[x][y] > 0) {
            if (total < 2 || total > 3) {
                board[x][y] = 2;
            }
        }
        //把即将复活的 死细胞 置为 -1
        if (board[x][y] <= 0 && total == 3) {
            board[x][y] = -1;
        }

    }


    int[][] a;
    @Test
    public void test(){
        a = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        gameOfLife(a);
        Utils.print2DArray(a);

    }
}
