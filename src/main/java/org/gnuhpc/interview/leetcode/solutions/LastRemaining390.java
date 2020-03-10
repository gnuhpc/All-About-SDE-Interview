package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-8-22
 */
public class LastRemaining390 {

    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }

    //链接：https://leetcode-cn.com/problems/elimination-game/solution/xiao-chu-you-xi-xiang-jie-by-yueyue_projects/

    @Test
    public void test() {
        System.out.println(lastRemaining(24));
    }
}
