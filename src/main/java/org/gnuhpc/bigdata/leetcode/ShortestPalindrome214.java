package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-8-15
 */
public class ShortestPalindrome214 {
    public String shortestPalindrome(String s) {
        int i = 0, n = s.length();
        //TODO 回文从左到右
        for (int j = n - 1; j >= 0; --j) {
            if (s.charAt(i) == s.charAt(j)) ++i;
        }
        if (i == n) return s;
        String rem = s.substring(i);
        String rem_rev = new StringBuilder(rem).reverse().toString();
        return rem_rev + shortestPalindrome(s.substring(0, i)) + rem;
    }



    @Test
    public void test() {
        System.out.println(shortestPalindrome("babad"));
        System.out.println(shortestPalindrome("aacecaaa"));
        System.out.println(shortestPalindrome("adcba"));
        System.out.println(shortestPalindrome("abcd"));
        System.out.println(shortestPalindrome(""));
        System.out.println(shortestPalindrome("abb"));
    }
}
