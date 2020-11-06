package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/21
 */
public class CalculateTime1165 {
    public int calculateTime(String keyboard, String word) {
        int count = 0;
        char[] keys = keyboard.toCharArray();
        char[] chars = word.toCharArray();

        int[] map = new int[200];
        int i = 0;
        for (char c : keys) {
            map[c - 'a'] = i++;
        }

        int index = 0;
        for (char c : chars) {
            count += Math.abs(map[c - 'a'] - index);
            index = map[c - 'a'];
        }
        return count;
    }

}
