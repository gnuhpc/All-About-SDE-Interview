package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-8-15
 */
public class ShortestPalindrome214 {
    public String shortestPalindrome(String s) {
        String revs = new StringBuilder(s).reverse().toString();
        String res = s;
        for (int i = 0; i < s.length(); i++) {
            String temp = revs.substring(i);
            if (s.startsWith(temp)) {
                res = revs.substring(0, i) + s;
                break;
            }
        }
        return res;
    }

    @Test
    public void test() {
//        System.out.println(shortestPalindrome("aacecaaa"));
//        System.out.println(shortestPalindrome("babad"));
//        System.out.println(shortestPalindrome("adcba"));
//        System.out.println(shortestPalindrome("abcd"));
//        System.out.println(shortestPalindrome(""));
//        System.out.println(shortestPalindrome("abb"));
        System.out.println(shortestPalindrome("abcb"));
    }
}
