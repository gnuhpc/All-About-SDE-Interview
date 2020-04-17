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
    public int jump2(int[] nums) {
        int step=0;  //当前跳跃次数
        int dst=nums.length-1; //需要跳跃的最大索引位置
        int maxPos=0; //当前步骤能跳至的最远位置
        int start=0; //当前步骤的起始跳跃位置
        int end=0; //前一步骤能跳跃的最远位置
        while(maxPos < dst){ // 如果没跳跃处最大索引，继续循环
            for(; start<=end; start++){ //end为上次跳跃的最远索引位置
                maxPos=Math.max(maxPos, nums[start]+start); //更新当前步骤的最远位置
                if(maxPos>=dst) break; //如果当前最远位置已经大于需跳跃的最远位置，终止循环
            }
            end=maxPos; //更新下一步跳跃的遍历最远范围
            step++; //跳跃次数递增，进入下一次跳跃
        }
        return step;
    }

    @Test
    public void test() {
        System.out.println(jump2(new int[]{2, 3, 1, 1, 4, 2, 1}));
    }

}
