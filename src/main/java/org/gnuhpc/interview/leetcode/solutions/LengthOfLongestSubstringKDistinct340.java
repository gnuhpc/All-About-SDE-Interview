package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class LengthOfLongestSubstringKDistinct340 {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() == 0) return 0;
        int[] map = new int[256];
        int start = 0, end = 0, maxLen = Integer.MIN_VALUE, counter = 0;

        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] == 0) counter++;
            map[c1]++;
            end++;

            while (counter > k) {
                final char c2 = s.charAt(start);
                if (map[c2] == 1) counter--;
                map[c2]--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }


    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstringKDistinct("aba", 1));
    }


}
