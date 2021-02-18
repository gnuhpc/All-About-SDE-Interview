package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2021/1/18
 */
public class IsUnique0101 {
    public boolean isUnique(String astr) {
        char[] arr = astr.toCharArray();
        Arrays.sort(arr);
        for (int i = 0, j = 1; j < arr.length; i++, j++) {
            if (arr[i] == arr[j]) return false;

        }

        return true;
    }
}
