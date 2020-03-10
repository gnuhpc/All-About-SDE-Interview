package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

public class CombinationSum4377 {
    public int combinationSum4(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return search(nums, target, memo);
    }

    private int search(int[] nums, int target, int[] memo) {
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += search(nums, target - num, memo);
            }
        }
        memo[target] = res;
        return res;
    }

    /**
     * 这里状态定义就是题目要求的，并不难，状态转移方程要动点脑子，也不难：
     * 状态转移方程：dp[i]= dp[i - nums[0]] + dp[i - nums[1]] + dp[i - nums[2]] + ... （当 [] 里面的数 >= 0）
     * 起始状态：dp[0] = 1,dp[0]表示组成0，一个数都不选就可以了，所以dp[0]=1
     * 再举一个具体的例子：nums=[1, 3, 4], target=7;
     * dp[7] = dp[6] + dp[4] + dp[3]
     * 即：7 的组合数可以由三部分组成，1 和 dp[6]，3 和 dp[4], 4 和dp[3];
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum42(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }
        int[] dp = new int[target + 1];
        //dp[0]表示组成0，一个数都不选就可以了，所以dp[0]=1
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }

        }
        return dp[target];
    }

    @Test
    public void test() {
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));
    }
}
