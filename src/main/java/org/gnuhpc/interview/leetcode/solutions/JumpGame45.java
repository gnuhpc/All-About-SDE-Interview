package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * 采用递归的做法，发现超时 80/92
 */
public class JumpGame45 {
    /*
    Method1: DFS LTE了
     */
    private int minJump = Integer.MAX_VALUE;

    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 0;
        help(nums, 0, 0, 0);
        return minJump;
    }

    public void help(int[] nums, int index, int step, int jumps) {
        int n = nums.length;
        int newindex = index + step;
        if (newindex >= n - 1) {
            minJump = Math.min(jumps, minJump);
        } else {
            //剪枝
            if (jumps >= minJump) return;
            for (int i = 1; i <= nums[newindex]; i++) { //挨个穷尽
                help(nums, newindex, i, jumps + 1);
            }
        }
    }

    /*
    Method2: 贪心
     */
    //链接：https://leetcode-cn.com/problems/jump-game-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-10/
    public int jump2(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) { //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


    @Test
    public void test() {
        System.out.println(jump2(new int[]{2, 3, 1, 1, 4, 2, 1}));
    }

}
