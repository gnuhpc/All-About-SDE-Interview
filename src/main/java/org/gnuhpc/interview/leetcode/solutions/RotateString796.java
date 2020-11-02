package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/11/1
 */
public class RotateString796 {
    public boolean rotateString(String A, String B) {
        if (A != null && B == null) return false;
        if (B != null && A == null) return false;
        if (A.length() != B.length()) return false;

        String pattern = A + A;
        return pattern.indexOf(B) != -1;

    }
}
