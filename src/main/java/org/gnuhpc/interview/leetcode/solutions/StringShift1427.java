package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/25
 */
public class StringShift1427 {
    public String stringShift(String s, int[][] shift) {
        int move = 0;
        for (int[] sh : shift) {
            if (sh[0] == 0) {
                move -= sh[1];
            } else {
                move += sh[1];
            }
        }
        move = move < 0 ? ((move % s.length()) + s.length()) % s.length() : move % s.length();

        return s.substring(s.length() - move) + s.substring(0, s.length() - move);
    }
}
