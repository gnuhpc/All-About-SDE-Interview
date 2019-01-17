package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.isKthBitSet;
import static org.gnuhpc.bigdata.leetcode.utils.Utils.setBit;

public class GameOfLife289 {
    public void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int state = board[i][j];
                int lives = getNumberOfLives(board,i,j);

                if (isNextLive(state,lives)){
                    board[i][j] = setBit(board[i][j],1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] >>= 1 ;
            }
        }
    }

    private int getNumberOfLives(int[][] board, int x, int y) {
        int n = board.length;
        int m = board[0].length;
        int res = 0;
        //TODO 二维数组越界判断
        for (int i = Math.max(0,x-1); i <= Math.min(x+1,n-1); i++) {
            for (int j = Math.max(0,y-1); j <= Math.min(y+1, m-1); j++) {
                if (!(i==x && j==y) && isKthBitSet(board[i][j],0)){
                    res++;
                }
            }
        }

        return res;
    }

    private boolean isNextLive(int state, int lives) {
        if ((
                (lives==2 || lives==3) && state ==1) ||
                (lives == 3)
                ){
            return true;
        } else {
            return false;
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
