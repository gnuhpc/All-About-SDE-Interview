package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

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



    //Method4: 递归+记忆化搜索，最后一个case跑不过去。。。
    private int[] mem;
    public boolean canJump4(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        if(nums.length == 1) return true;

        mem = new int[nums.length];
        mem[mem.length-1] = 2;
        return robot(nums, 0);

    }

    private boolean robot(int[] nums, int start){
        if(mem[start] == 2) return true;
        if(mem[start] == 1) return false;

        if(start == nums.length-1) {
            mem[start] = 1;
            return true;
        }

        int startSteps = nums[start];
        if(start == nums.length -2) {
            if(startSteps>0){
                mem[start] = 2;
                return true;
            } else {
                mem[start] = 1;
                return false;
            }
        }

        int scope = Math.min(start+1+startSteps, nums.length);
        for(int i = start+1; i < scope;i++){
            if(robot(nums, i)){
                mem[start] = 2;
                return true;
            }
        }

        return false;
    }

    //Method5: 倒推
    public boolean canJump5(int[] nums) {
        //标注最后一个至少要到达的位置
        int lastPos = nums.length -1;
        //从后往前看能不能到达的下一个是什么
        for(int i = nums.length -1 ;i >=0 ;i--){
            if(i+nums[i] >= lastPos){
                lastPos = i;
            }
        }

        //最后能到达的最后一个为开头元素即为True
        return lastPos == 0;
    }

    @Test
    public void test(){
        System.out.println(canJump4(new int[]{3,2,1,0,4}));
    }

}
