package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/11/14
 */
public class Tictactoe1275 {
    public String tictactoe(int[][] moves) {
        int[][] ans = new int[3][3];
        for (int i = 0; i < moves.length; i++)
            ans[moves[i][0]][moves[i][1]] = i % 2 == 0 ? 1 : 2;
        if (isVictory(ans, 1))
            return "A";
        else if (isVictory(ans, 2))
            return "B";
        return moves.length < 9 ? "Pending" : "Draw";
    }

    public static boolean isVictory(int[][] ans, int player) {
        return ans[0][0] == ans[0][1] && ans[0][1] == ans[0][2] && ans[0][2] == player
                || ans[1][0] == ans[1][1] && ans[1][1] == ans[1][2] && ans[1][2] == player
                || ans[2][0] == ans[2][1] && ans[2][1] == ans[2][2] && ans[2][2] == player
                || ans[0][0] == ans[1][0] && ans[1][0] == ans[2][0] && ans[2][0] == player
                || ans[0][1] == ans[1][1] && ans[1][1] == ans[2][1] && ans[2][1] == player
                || ans[0][2] == ans[1][2] && ans[1][2] == ans[2][2] && ans[2][2] == player
                || ans[0][0] == ans[1][1] && ans[1][1] == ans[2][2] && ans[2][2] == player
                || ans[0][2] == ans[1][1] && ans[1][1] == ans[2][0] && ans[2][0] == player;
    }
}
