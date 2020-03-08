package org.gnuhpc.bigdata.leetcode.solutions;

/**
 * Copyright gnuhpc 2019/9/22
 */
public class ArrangeCoins441 {
    public int arrangeCoins(int n) {
        int l = 1, r = n;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (sum(mid) == n) {
                return mid;
            }
            else if (sum(mid) > n) {
                r = mid;
            }
            else {
                l = mid;
            }
        }
        return sum(r) <= n ? r : l;
    }

    private long sum(long k) {
        return k * (k + 1) / 2;
    }
}
