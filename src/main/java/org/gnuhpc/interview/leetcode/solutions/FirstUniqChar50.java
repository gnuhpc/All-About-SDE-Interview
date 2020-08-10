package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/8
 */
public class FirstUniqChar50 {
    public char firstUniqChar(String s) {
        int[] count = new int[256];
        char[] chars = s.toCharArray();
        for (char c : chars)
            count[c]++;
        for (char c : chars) {
            if (count[c] == 1)
                return c;
        }
        return ' ';
    }
}
