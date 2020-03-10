package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 2019/10/6
 */
public class TicTacToe348 {

    int size;
    int[][] h, v, d;

    /**
     * Initialize your data structure here.
     */
    public TicTacToe348(int n) {

        h = new int[2][n];
        v = new int[2][n];
        d = new int[2][2];
        size = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {

        h[player - 1][row] += 1;
        v[player - 1][col] += 1;

        if (row == col)
            d[player - 1][0] += 1;
        if (row + col == size - 1)
            d[player - 1][1] += 1;

        if (h[player - 1][row] == size || v[player - 1][col] == size || d[player - 1][0] == size ||
                d[player - 1][1] == size)
            return player;
        else
            return 0;
    }
}
