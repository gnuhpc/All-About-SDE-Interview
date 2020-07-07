package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 2020/7/4
 */
public class ScoreOfParentheses856 {
    public int scoreOfParentheses(String S) {
        char[] arr = S.toCharArray();
        return helper(arr, 0, arr.length);
    }

    private int helper(char[] arr, int start, int end) {
        if (end - start == 2) return 1;

        int toMatch = 1, idx = start + 1;
        //找到最后一个匹配的括号
        while (idx < end && toMatch > 0) {
            char cc = arr[idx];
            if (cc == '(') toMatch++; //记录左右括号匹配个数
            if (cc == ')') toMatch--;
            idx++;
        }


        if (idx == end)
            return 2 * helper(arr, start + 1, idx - 1);
        else {
            return helper(arr, start, idx) + helper(arr, idx, end);
        }
    }

    @Test
    public void test() {
        System.out.println(scoreOfParentheses("(()(()))"));
    }
}
