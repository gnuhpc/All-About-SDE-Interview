package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-9-5
 */
public class CountBinarySubstrings696 {
    private int num = 0;

    public int countBinarySubstrings(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1))
                count(s, i, i + 1);
        }
        return num;
    }

    private void count(String s, int i, int j) {
        while (i >= 0 && j < s.length()) {
            num++;
            i--;
            j++;
            if (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(i + 1) && s.charAt(j) == s.charAt(j - 1))
                continue;
            else
                break;
        }
    }


    @Test
    public void test() {
        System.out.println(countBinarySubstrings("10101"));
    }
}
