package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/22
 */
public class GameLCP01 {
    public int game(int[] guess, int[] answer) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if (answer[i] == guess[i]) {
                count++;
            }
        }
        return count;
    }
}
