package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/10/31
 */
public class HasAlternatingBits693 {
    public boolean hasAlternatingBits(int n) {
        int pre = n & 1;
        n >>>= 1;
        while (n != 0) {
            int now = n & 1;
            if (now == pre)
                return false;
            pre = now;
            n >>>= 1;
        }
        return true;
    }
}
