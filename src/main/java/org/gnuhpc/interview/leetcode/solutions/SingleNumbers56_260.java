package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

/**
 * Copyright gnuhpc 2020/4/28
 */
public class SingleNumbers56_260 {

    /*
    异或就是存放两数互异的点位
    假设两个只出现一次的数为a、b，那么将数组中所有数异或一次，最后结果就是c = a^b
因为a与b不一样，所以c肯定至少有1位是不为0的，我们找到最右边开始第一个1 记为h
再次遍历数组，用h和数组元素“与”操作为0还是为1区分开这两个数；
不过不用担心其他出现两次的数被分开，影响结果，他们&h结果是一样的，会被分到一起 x^x = 0会消掉。 最后得到的就是 a、b
     */

    // 假设结果数为A B
    public int[] singleNumbers(int[] nums) {
        int a = 0;
        int b = 0;
        int c = 0; // 用来记录 两个只出现一次的数 a^b
        for (int i = 0; i < nums.length; i++) {
            c = c ^ nums[i];
        }
        int h = 1;
        while ((c & h) == 0) {
            h = h << 1;
        }
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & h) == 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }
        return new int[]{a, b};
    }


    @Test
    public void test() {
        Utils.printArray(singleNumbers(new int[]{4, 1, 4, 6}));
    }
}
