package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/3/13
 */
public class IsFlipedString0109 {
    public boolean isFlipedString(String s1, String s2) {
        return s1.equals(s2) || (s1.length() == s2.length() && (s1 + s1).contains(s2));
    }
}
