package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/3
 */
public class FindLUSlength521 {
    public int findLUSlength(String a, String b) {
        if (a.equals(b))
            return -1;
        return a.length() > b.length() ? a.length() : b.length();
    }
}
