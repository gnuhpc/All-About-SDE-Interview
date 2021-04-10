package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/19
 */
public class CheckPermutation0102 {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] map = new int[1000];
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        for (int i = 0; i < arr1.length; i++) {
            map[arr1[i]]++;
            map[arr2[i]]--;
        }

        for (int n : map) {
            if (n != 0) return false;
        }

        return true;

    }
}
