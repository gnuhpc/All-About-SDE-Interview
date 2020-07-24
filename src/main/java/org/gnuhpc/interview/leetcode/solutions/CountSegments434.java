package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/6/30
 */
public class CountSegments434 {
    public int countSegments(String s) {
        if ("".equals(s.trim())) {
            return 0;
        }
        int count = 0;
        for (int x = 0; x < s.trim().length() - 1; x++) {
            if (s.charAt(x) != ' ' && s.charAt(x + 1) == ' ') {
                count++;
            }
        }
        return count + 1;
    }
}
