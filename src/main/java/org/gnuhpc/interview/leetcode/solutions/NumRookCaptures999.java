package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/3/26
 */

public class NumRookCaptures999 {
    private int rookPosx;
    private int rookPosy;

    public int numRookCaptures(char[][] board) {
        int res = 0;
        final int SIZE = 8;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 'R') {
                    rookPosx = i;
                    rookPosy = j;
                }
            }
        }

        //向上
        for (int i = rookPosy; i >= 0; i--) {
            if (board[rookPosx][i] == 'p') {
                res++;
                break;
            } else if (board[rookPosx][i] == 'B') break;
        }

        //向下
        for (int i = rookPosy; i < SIZE; i++) {
            if (board[rookPosx][i] == 'p') {
                res++;
                break;
            } else if (board[rookPosx][i] == 'B') break;
        }

        //向左
        for (int i = rookPosx; i >= 0; i--) {
            if (board[i][rookPosy] == 'p') {
                res++;
                break;
            } else if (board[i][rookPosy] == 'B') break;
        }

        //向右
        for (int i = rookPosx; i < SIZE; i++) {
            if (board[i][rookPosy] == 'p') {
                res++;
                break;
            } else if (board[i][rookPosy] == 'B') break;
        }

        return res;
    }
}
