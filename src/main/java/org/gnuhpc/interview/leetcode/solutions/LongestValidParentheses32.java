package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class LongestValidParentheses32 {
    /*
    Method1: 双向扫描
    链接：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     */
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    /*
    Method2: Stack , 比较巧妙的是记录了不匹配的index

     */
    public int longestValidParentheses2(String s) {
        int n = s.length();

        // Create a stack and push -1 as initial index to it.
        Deque<Integer> stack = new LinkedList<>();
        //栈顶是最左边的一个(的index
        stack.push(-1);

        // Initialize res
        int res = 0;

        // Traverse all characters of given string
        for (int i = 0; i < n; i++) {
            // If opening bracket, push index of it
            if (s.charAt(i) == '(')
                stack.push(i);

            else // If closing bracket, i.e.,s[i] = ')'
            {
                // Pop the previous opening bracket's index
                stack.pop();

                // Check if this length formed with base of
                // current valid substring is more than max
                // so far
                if (!stack.isEmpty())
                    res = Math.max(res, i - stack.peek());

                    // If stack is empty 说明起点不对

                else
                    return Math.max(res, longestValidParentheses(s.substring(i + 1)));


            }
        }

        return res;
    }


    @Test
    public void test() {
        System.out.println(longestValidParentheses2(")()())"));
    }
}
