package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HasGroupsSizeX914 {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[1000];
        for (int val : deck) {
            count[val]++;
        }
        int tmp = -1;
        for (int val : count) {
            if (val == 0) {
                continue;
            }
            tmp = tmp == -1 ? val : gcd(tmp, val);
        }
        return tmp >= 2;
    }

    private int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }


    @Test
    public void test() {
        System.out.println(hasGroupsSizeX(new int[]{1, 1, 2, 2, 2, 2}));
        System.out.println(hasGroupsSizeX(new int[]{1, 1, 1, 2, 2, 2, 3, 3}));
    }
}
