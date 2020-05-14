package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/5/11
 */
public class MajorityElement1710 {
    public int majorityElement(int[] nums) {
        //首先找到这个数组中出现最多的元素。 TODO O(N)/O(1)找到出现次数最多的值的方法
        int cur = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == cur) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                cur = nums[i];
                count = 1;
            }
        }
        //遍历一遍结果就是找到出现次数最多的元素，判断是不是满足题目中大于一半的条件。
        int curNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == cur) {
                curNum++;
            }
        }
        if (curNum > nums.length / 2) {
            return cur;
        }
        return -1;
    }
}
