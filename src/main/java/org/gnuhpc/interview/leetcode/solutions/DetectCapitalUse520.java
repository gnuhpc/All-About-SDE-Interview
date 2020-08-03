package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/3
 */
public class DetectCapitalUse520 {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) return false;
        char[] arr = word.toCharArray();
        if (word.length() == 1) return true;

        boolean hasUpper = false;
        boolean hasLower = false;

        if (isLowerCase(arr[0])) hasLower = true;

        for (int i = 1; i < arr.length; i++) {
            if (isUpperCase(arr[i])) {
                if (hasUpper && hasLower) return false;
                else hasUpper = true;
            } else {
                hasLower = true;
            }
        }

        return !(hasUpper & hasLower);
    }

    private boolean isLowerCase(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    private boolean isUpperCase(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }
}
