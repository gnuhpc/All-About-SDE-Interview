package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/3
 */
public class DetectCapitalUse520 {
    public boolean detectCapitalUse(String word) {
        int len = word.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            char s = word.charAt(i);
            if (s >= 'A' && s <= 'Z') {
                count++;
            }
        }
        return count == len || count == 0
                || (count == 1 && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z');
    }
}
