package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/7/9
 */
public class Respace1713 {
    public int respace(String[] dictionary, String sentence) {
        int[] cache = new int[sentence.length()];
        Arrays.fill(cache, -1);
        return backtrack(dictionary, 0, sentence, cache);
    }

    private int backtrack(String[] dictionary, int index, String sentence, int[] cache) {
        if (index >= sentence.length()) return 0;
        if (cache[index] != -1) return cache[index];
        int min = sentence.length() - index;
        for (String s : dictionary) {
            int pos = sentence.indexOf(s, index);
            if (pos != -1) {
                min = Math.min(min, pos - index + backtrack(dictionary, pos + s.length(), sentence, cache));
            }
        }
        cache[index] = min;
        return min;
    }
}
