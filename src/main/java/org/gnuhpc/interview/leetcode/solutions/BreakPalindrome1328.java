package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/6
 */
public class BreakPalindrome1328 {
    public String breakPalindrome(String palindrome) {
        char[] arr = palindrome.toCharArray();
        if (arr.length == 1 || palindrome.equals("") || palindrome == null) return "";
        int mid = arr.length / 2;

        for (int i = 0; i < mid; i++) {
            if (arr[i] != 'a') {
                arr[i] = 'a';
                return String.valueOf(arr);
            }
        }
        arr[arr.length - 1] = 'b';

        return String.valueOf(arr);
    }
}
