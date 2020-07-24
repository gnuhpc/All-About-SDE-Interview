package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/6/28
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        int[] map = new int[128];

        char[] chars = s.toCharArray();

        for (char c : chars) {
            map[c]++;
        }


        int res = 0;


        for (int n : map) {
            if (n % 2 == 0) res += n;
            else {
                res += (n - 1);
            }
        }
        return res < s.length() ? res + 1 : res; //放一个奇数在中间
    }
}
