package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/4/4
 */
public class NumRabbits781 {
    public int numRabbits(int[] answers) {
        if (answers.length == 0) return 0;
        if (answers.length == 1) return answers[0] + 1;

        int[] map = new int[1000];

        for (int answer : answers) {
            map[answer]++;
        }
        int res = map[0];
        for (int i = 1; i < map.length; i++) {
            if (map[i] == 0) continue;
            int round = (int) Math.ceil((double) map[i] / (i + 1));
            res += round * (i + 1);
        }

        return res;
    }
}
