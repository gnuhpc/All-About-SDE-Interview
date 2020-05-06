package org.gnuhpc.interview.leetcode.solutions;

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
    /*
    Method1 : 递归 LTE
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        if (nums.length == 1) return true;
        return help(nums, nums.length, 0, 0);
    }

    public boolean help(int[] nums, int n, int index, int step) {
        int newindex = index + step;
        if (newindex >= n - 1) return true;
        else {
            for (int i = 1; i <= nums[newindex]; i++) {
                if (help(nums, n, newindex, i)) return true;
            }
        }
        return false;
    }

    // Method2 : Dynamic Programming
    // 这个方法，复杂度是 O(n^2) 可能会超时，但是依然需要掌握。
    // 思路就是借助hash表，表示位置i是否可到达
    // 外层循环遍历，确定n-1是否可到达，里层判断从0开始是否可到达i
    public boolean canJump2(int[] A) {
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

    //Method3: 递归+记忆化搜索，最后一个case跑不过去。。。
    private int[] mem; //2可以，1 不可以 0为初始状态

    public boolean canJump3(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        if (nums.length == 1) return true;

        mem = new int[nums.length];
        mem[mem.length - 1] = 2;
        return robot(nums, 0);
    }

    private boolean robot(int[] nums, int startIdx) {
        if (mem[startIdx] == 2) return true;
        if (mem[startIdx] == 1) return false;

        if (startIdx == nums.length - 1) {
            mem[startIdx] = 1;
            return true;
        }

        int startSteps = nums[startIdx];
        if (startIdx == nums.length - 2) {
            if (startSteps > 0) {
                mem[startIdx] = 2;
                return true;
            } else {
                mem[startIdx] = 1;
                return false;
            }
        }

        int scope = Math.min(startIdx + 1 + startSteps, nums.length);
        for (int i = startIdx + 1; i < scope; i++) {
            if (robot(nums, i)) {
                mem[startIdx] = 2;
                return true;
            }
        }

        return false;
    }

    //Method4: 倒推
    /*
    如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
    可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
    如果可以一直跳到最后，就成功了。
     */
    public boolean canJump4(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) return false;
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }

    /*
    *******最好记，最好理解********
    Method5: 另一种倒退，贪心
    我们记录一个坐标代表当前可达的最后节点，这个坐标初始等于nums.length-1，
    然后我们每判断完是否可达，都向前移动这个坐标，直到遍历结束。
    如果这个坐标等于0，那么认为可达，否则不可达。
     */
    public boolean canJump5(int[] nums) {

        if (nums == null) {
            return false;
        }
        int lastPosition = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 逐步向前递推
            if (nums[i] + i >= lastPosition) {
                lastPosition = i;
            }
        }
        return lastPosition == 0;
    }

    @Test
    public void test() {
        System.out.println(canJump4(new int[]{3, 2, 1, 0, 4}));
    }

}
