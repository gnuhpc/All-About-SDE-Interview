package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/4/25
 */
public class MaxNumberOfBalloons1189 {
    public int maxNumberOfBalloons(String text) {
        String balloon = "balloon";
        char[] arrWord = balloon.toCharArray();
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for (char c : arrWord) map1[c - 'a']++;
        char[] arr = text.toCharArray();
        for (char c : arr) map2[c - 'a']++;

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (map1[i] == 0) continue;
            if (map1[i] > map2[i]) return 0;
            res = Math.min(res, map2[i] / map1[i]);
        }
        return res;
    }
}
