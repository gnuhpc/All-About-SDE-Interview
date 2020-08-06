package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/5
 */
public class ReverseOnlyLetters917 {
    public String reverseOnlyLetters(String S) {
        char[] aoc = S.toCharArray();
        int l = 0;
        int r = aoc.length - 1;
        while (l < r) {
            if (!Character.isAlphabetic(aoc[l])) {
                l++;
            } else if (!Character.isAlphabetic(aoc[r])) {
                r--;
            } else {

                char temp = aoc[l];
                aoc[l] = aoc[r];
                aoc[r] = temp;
                l++;
                r--;
            }
        }
        return String.valueOf(aoc);
    }
}
