package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/31
 */
public class XorOperation1486 {
    public int xorOperation(int n, int start) {
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans ^= (start + i * 2);
        }
        return ans;
    }
}
