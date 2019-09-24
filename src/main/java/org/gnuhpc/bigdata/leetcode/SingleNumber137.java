package org.gnuhpc.bigdata.leetcode;

public class SingleNumber137 {
    public int singleNumber(int[] nums) {
        int[] bit = new int[32];

        for (int j = 0; j < 32; j++) {
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i] >> j;
                bit[j] += num & 1;
            }
        }

        int result = 0;
        for (int i = 31; i >= 0; i--) {
            result <<= 1;
            result += bit[i] % 3;
        }
        return result;
    }

   // 链接：https://leetcode-cn.com/problems/single-number-ii/solution/java-yi-dong-yi-jie-xiao-lu-gao-by-spirit-9-8/
}
