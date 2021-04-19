package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/28
 */
public class MaxPower1466 {
    public int maxPower(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int size = 0;
        while (right <= s.length()) {
            if (right == s.length() || s.charAt(left) != s.charAt(right)) {
                size = (right - left) > size ? right - left : size;
                left = right;
            }
            right++;
        }
        return size;
    }
}
