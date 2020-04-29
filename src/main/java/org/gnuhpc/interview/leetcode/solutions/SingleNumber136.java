package org.gnuhpc.interview.leetcode.solutions;

public class SingleNumber136 {
    /*
    异或运算和乘法一样，位置和运算顺序不影响最后结果：a^b^c = b^c^a
    两个相同的数做异或运算结果为零：a^a = 0
    任何数和零做异或结果还是这个数本身：a^0 = a
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            result ^= nums[i];
        }

        return result;
    }
}
