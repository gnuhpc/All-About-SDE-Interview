package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2022/1/7
 */
public class MaxDepth1614 {
    public int maxDepth(String s) {
        int depth = 0;
        int max = 0;
        int idx = -1;
        while (++idx < s.length()) {
            char c = s.charAt(idx);
            if (c == '(') {
                depth++;
                max = Math.max(max, depth);
            }
            if (c == ')') {
                depth--;
            }
        }
        return max;
    }
}
