package org.gnuhpc.bigdata.leetcode;

/**
 * 本题题意及example容易让人误解，
 * 题目意思是问能否到达以及超过n-1位置（不是刚好到达）
 * 故本题的思路类似于贪心算法
 */

public class JumpGame55 {
    // 一开始以为是刚好到达，所以采用递归回溯法。结果提交即报超时。
    // 即便将条件改为超过即满足，也报超时
    // 说明递归不可用
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        if(nums.length == 1) return true;
        return help(nums,nums.length,0,0);
    }

    public boolean help(int[] nums, int n, int index, int step){
        int newindex = index+step;
        if(newindex >= n-1) return true;
        //else if(newindex>=n) return false;
        /*else{
            for(int i = 1; i<=nums[newindex];i++){
                if(help(nums,n,newindex,i)) return true;
            }
        }*/

        else{
            for(int i = 1; i<=nums[newindex];i++){
                if(help(nums,n,newindex,i)) return true;
            }
        }
        return false;
    }

    public boolean canJump2(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        if(nums.length == 1 ) return true;

        int reach = 0;
        int n = nums.length;
        for(int i = 0; i<n;i++){
            if(i<=reach && reach<n-1) // i<=reach表示当前位置能到达
                reach = Math.max(reach,i+nums[i]);
            if(reach >= n-1) return true;
        }
        return false;
    }

    // version 1: Dynamic Programming
    // 这个方法，复杂度是 O(n^2) 可能会超时，但是依然需要掌握。
    // 思路就是借助hash表，表示位置i是否可到达
    // 外层循环遍历，确定n-1是否可到达，里层判断从0开始是否可到达i
    public boolean canJump3(int[] A) {
        boolean[] can = new boolean[A.length];
        can[0] = true;

        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (can[j] && j + A[j] >= i) {
                    can[i] = true;
                    break;
                }
            }
        }

        return can[A.length - 1];
    }

}
