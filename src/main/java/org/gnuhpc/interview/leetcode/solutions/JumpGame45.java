package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * 采用递归的做法，发现超时 80/92
 */
public class JumpGame45 {
    private int minJump = Integer.MAX_VALUE;

    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 0;
        help(nums, nums.length, 0, 0, 0);
        return minJump;
    }

    public void help(int[] nums, int n, int index, int step, int jumps) {
        int newindex = index + step;
        if (newindex >= n - 1) {
            minJump = Math.min(jumps, minJump);
            return;
        } else {
            //剪枝
            if (jumps >= minJump) return;
            for (int i = 1; i <= nums[newindex]; i++) {
                help(nums, n, newindex, i, jumps + 1);
            }
        }
        return;
    }


    public int jump2(int[] nums) {
        int n = nums.length;
        int ret = 0;
        int last = 0;
        int curr = 0;
        for (int i = 0; i < n; ++i) {
            if (i > last) {
                last = curr;
                ++ret;
            }
            curr = Math.max(curr, i + nums[i]);
        }

        return ret;
    }

    public int jump4(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) { //遇到边界，就说明这个部分已经遍历完了，最大值也找到了，
                // 就按照最大值更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public int jump5(int[] nums) {
        int position = nums.length - 1; //要找的位置
        int steps = 0;
        while (position != 0) { //是否到了第 0 个位置
            for (int i = 0; i < position; i++) {
                if (nums[i] + i >= position) {
                    position = i; //更新要找的位置
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }


    @Test
    public void test() {
        System.out.println(jump4(new int[]{2, 3, 1, 1, 4, 2, 1}));
    }


}
