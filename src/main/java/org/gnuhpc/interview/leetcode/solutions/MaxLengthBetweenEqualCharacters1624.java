package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/21
 */
public class MaxLengthBetweenEqualCharacters1624 {
    public int maxLengthBetweenEqualCharacters(String s) {
        int ans = -1;

        for (int i = 0; i < 26; i++) {
            ans = Math.max(ans, s.lastIndexOf(i + 'a') - s.indexOf(i + 'a') - 1);
        }
        return ans;
    }
}
