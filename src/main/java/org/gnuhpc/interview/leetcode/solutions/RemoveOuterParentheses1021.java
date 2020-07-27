package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 2020/7/5
 */
public class RemoveOuterParentheses1021 {
    public String removeOuterParentheses(String S) {
        return helper(S, 0, S.length());
    }

    private String helper(String S, int start, int end) {
        if (start == end) return "";
        if (S.charAt(start) == '(' && S.charAt(end - 1) == ')' && end - start == 2) return "()";

        char[] arr = S.toCharArray();

        int toMatch = 0;

        int i = start;

        for (; i < end; i++) {
            char c = arr[i];
            if (c == '(') toMatch++;
            if (c == ')') toMatch--;
            if (toMatch == 0) {
                return S.substring(start + 1, i) + helper(S, i + 1, end);
            }
        }
        return "";
    }

    @Test
    public void test() {
        System.out.println(removeOuterParentheses("(()())(())"));
    }
}
