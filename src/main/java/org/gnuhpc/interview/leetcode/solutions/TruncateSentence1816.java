package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Copyright gnuhpc 2021/12/6
 */
public class TruncateSentence1816 {
    public String truncateSentence(String s, int k) {
        char[] sChars = s.toCharArray();
        int i = 0;
        for (; i < s.length(); i++) {
            if (sChars[i] == ' ') k--;
            if (k == 0) break;
        }

        return s.substring(0, i);
    }

    @Test
    public void test() {
        String s1 = "What is the solution to this problem";
        assertEquals(truncateSentence(s1, 4), "What is the solution");

        String s2 = "chopper is not a tanuki";
        assertEquals(truncateSentence(s2, 5), "chopper is not a tanuki");
    }
}
