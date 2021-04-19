package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/21
 */
public class MergeAlternately5685 {
    public String mergeAlternately(String word1, String word2) {
        char[] res = new char[word1.length() + word2.length()];
        char[] array1 = word1.toCharArray();
        char[] array2 = word2.toCharArray();

        int j1 = 0, j2 = 0, i = 0;
        boolean isFirst = true;
        for (; i < res.length && j1 < array1.length && j2 < array2.length; i++) {
            if (isFirst) {
                res[i] = array1[j1++];
            } else {
                res[i] = array2[j2++];
            }
            isFirst = !isFirst;
        }

        if (j1 == array1.length) {
            while (j2 < array2.length) res[i++] = array2[j2++];
        } else {
            while (j1 < array1.length) res[i++] = array1[j1++];

        }

        return new String(res);
    }
}
