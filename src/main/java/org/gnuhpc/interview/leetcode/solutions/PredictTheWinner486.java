package org.gnuhpc.interview.leetcode.solutions;

public class PredictTheWinner486 {

    public boolean PredictTheWinner(int[] nums) {
        // 分别代表left和right左右游标
        int l = 0, r = nums.length - 1;
        // 分别代表玩家1和玩家2的总分数
        int s1 = 0, s2 = 0;
        boolean turn1 = true; // 当前是否该玩家1选择了，因为玩家1是先手，所以true
        return PredictTheWinnerHelp(nums, l, r, s1, s2, turn1);
    }

    private boolean PredictTheWinnerHelp(int[] nums, int l, int r, int s1, int s2, boolean turn1) {
        // 游标遍历完所有元素时，判断结果
        if (l > r) {
            return s1 >= s2; // 玩家1获胜的条件
        }

        if (turn1) {
            // 如果该玩家1选择了，那么选择左或者右，只要有一种情况能获胜即为获胜，所以用||
            return PredictTheWinnerHelp(nums, l + 1, r, s1 + nums[l], s2, !turn1) || PredictTheWinnerHelp(nums, l, r - 1, s1 + nums[r], s2, !turn1);
        } else {
            // 如果该玩家2选择了，那么只有当玩家2选择完左或者右都是玩家1获胜的情况下，玩家1才真正获胜，所有用&&
            return PredictTheWinnerHelp(nums, l + 1, r, s1, s2 + nums[l], !turn1) && PredictTheWinnerHelp(nums, l, r - 1, s1, s2 + nums[r], !turn1);
        }
    }

    /*
    也可以分别做memo，这里没有做
     */
    public boolean PredictTheWinner2(int[] nums) {
        int n = nums.length;
        return f(nums,0,n-1) >= s(nums,0,n-1);
    }
    //先发者取一个数后，变成后发者 ， 因为每个玩家的玩法都会使他的分数最大化 ，所以取最大值
    public int f(int[] nums , int l , int r){
        if(l == r) return nums[l];
        return Math.max(nums[l] + s(nums,l+1,r) , nums[r] + s(nums,l,r-1));
    }
    //后发者没得选，在先发者选完之后变成先发者，但先发者留给他的只会是最小的
    public int s(int[] nums , int l , int r){
        if(l == r) return 0;
        return Math.min(f(nums,l+1,r) , f(nums,l,r-1));
    }
}
