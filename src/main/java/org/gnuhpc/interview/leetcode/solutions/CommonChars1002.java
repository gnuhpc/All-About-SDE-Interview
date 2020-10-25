package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/10/14
 */
public class CommonChars1002 {
    public List<String> commonChars(String[] A) {
        int[] arr = new int[26];
        Arrays.fill(arr, Integer.MAX_VALUE);
        int[] tmp = new int[26];
        for (int i = 0; i < A.length; i++) {
            String str = A[i];
            Arrays.fill(tmp, 0);
            for (int j = 0; j < str.length(); j++) {
                tmp[str.charAt(j) - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                arr[j] = Math.min(arr[j], tmp[j]);
            }
        }
        List<String> res = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < arr[i]; j++) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }
}
