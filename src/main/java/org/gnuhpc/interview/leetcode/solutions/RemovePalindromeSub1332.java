package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/30
 */
public class RemovePalindromeSub1332 {
    public int removePalindromeSub(String s) {
        if ("".equals(s)) return 0;
        if (s.equals(new StringBuilder(s).reverse().toString())) return 1;
        return 2;
    }
}
