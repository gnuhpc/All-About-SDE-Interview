package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 2020/8/5
 */
public class ReverseParentheses1190 {
    public String reverseParentheses(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; ) {
            if (Character.isLetter(s.charAt(i))) {
                res.append(s.charAt(i));//普通字母就添加
                i++;
            } else//遇到'('就递归
            {
                int j = i + 1, cnt = 1;
                for (; j < n && cnt > 0; j++) {
                    if (s.charAt(j) == '(') cnt++;
                    else if (s.charAt(j) == ')') cnt--;
                    if (cnt == 0) break;
                }
                res.append(new StringBuilder(reverseParentheses(s.substring(i + 1, j))).reverse().toString());
                i = j + 1;//上面有个++
            }
        }
        return res.toString();
    }


    @Test
    public void test() {
        System.out.println(reverseParentheses("(u(love)i)"));
    }
}
