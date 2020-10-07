package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/9/20
 */
public class ShortestCompletingWord784 {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        String lower = licensePlate.toLowerCase();
        int[] map = new int[26];

        char[] arr = lower.toCharArray();
        for (char c : arr) {
            if (c >= 'a' && c <= 'z') map[c - 'a']++;
        }

        boolean[] res = new boolean[words.length];
        int i = 0;
        for (String word : words) {
            if (isContain(Arrays.copyOf(map, map.length), word)) {
                res[i] = true;
            }
            i++;
        }

        int min = Integer.MAX_VALUE;
        int idx = -1;
        for (i = 0; i < res.length; i++) {
            if (res[i]) {
                if (words[i].length() < min) {
                    min = words[i].length();
                    idx = i;
                }
            }
        }

        return words[idx];
    }

    private boolean isContain(int[] map, String word) {

        char[] wArr = word.toCharArray();

        for (char c : wArr) {
            if (map[c - 'a'] > 0) map[c - 'a']--;
        }

        for (int n : map) {
            if (n != 0) return false;
        }

        return true;
    }
}
