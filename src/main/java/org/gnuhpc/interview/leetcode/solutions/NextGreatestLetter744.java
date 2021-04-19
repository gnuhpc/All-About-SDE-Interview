package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/3/26
 */
public class NextGreatestLetter744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;

        for (int i = 0; i <= n; i++) {
            if (i < n) {
                if (letters[i] > target) return letters[i];
            } else {
                return letters[0];
            }
        }

        return 'a';
    }
}
