package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class GetSum371 {
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }


    @Test
    public void test() {
        System.out.println(getSum(-1, 2));
    }
}
