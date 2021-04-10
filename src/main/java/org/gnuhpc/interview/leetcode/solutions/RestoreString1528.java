package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/22
 */
public class RestoreString1528 {
    public String restoreString(String s, int[] indices) {
        char[] arr = s.toCharArray();
        char[] res = new char[arr.length];
        for (int i = 0; i < indices.length; i++) {
            res[indices[i]] = arr[i];
        }
        return new String(res);
    }
}
