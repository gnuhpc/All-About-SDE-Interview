package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 2020/1/5
 */
public class CheckInclusion567 {
    public boolean checkInclusion(String s1, String s2) {
        int[] map = new int[128];

        for (char c : s1.toCharArray()) {
            map[c]++;
        }

        int left = 0, right = 0, total = s1.length();
        char[] chS = s2.toCharArray();

        while (right < s2.length()) {
            if (map[chS[right++]]-- > 0) total--;

            while (total == 0) {
                if (right - left == s1.length()) {
                    return true;
                }

                if (map[chS[left++]]++ == 0) total++;
            }

        }

        return false;
    }

    @Test
    public void test() {
        System.out.println(checkInclusion("hello",
                                          "ooolleoooleh"));
        System.out.println(checkInclusion("abcdxabcde",
                                          "abcdeabcdx"));
    }
}
