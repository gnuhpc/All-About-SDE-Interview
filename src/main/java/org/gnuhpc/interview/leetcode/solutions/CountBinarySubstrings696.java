package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-9-5
 */
public class CountBinarySubstrings696 {
    public int countBinarySubstrings(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            int left = i - 1, right = i;
            char leftChar = chars[left], rightChar = chars[right];
            if (leftChar == rightChar)
                continue;
            while (left >= 0 && right < s.length() && chars[left] == leftChar && chars[right] == rightChar) {
                left--;
                right++;
                result++;
            }
        }
        return result;
    }


    @Test
    public void test() {
        System.out.println(countBinarySubstrings("10101"));
    }
}
