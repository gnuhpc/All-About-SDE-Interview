package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-8-22
 */
public class LastRemaining390 {

    /*
输入：n = 9
输出：6
解释：
arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
arr = [2, 4, 6, 8]
arr = [2, 6]
arr = [6]

输入：n = 6
输出：6
解释：
arr = [1, 2, 3, 4, 5, 6]
arr = [2, 4, 6]
arr = [4]


*/

    public int lastRemaining(int n) {
        if (n == 1) {
            return 1;
        }
        int size = n;// 表示当前整数列表中的数量；
        int step = 1; //表示两个有效整数之间的距离。
        int start = 1;//表示从左到右第一个有效的整数；
        boolean fromLeft = true; //本轮是否从左边开始消除

        while (size > 1) {
            if (fromLeft) {
                start = start + step;
            } else {
                start = (size % 2 == 0) ? start : start + step;
            }

            size = size / 2;
            step *= 2;
            fromLeft = !fromLeft;
        }

        return start;
    }


    @Test
    public void test() {
        System.out.println(lastRemaining(24));
    }
}
