package org.gnuhpc.bigdata.leetcode;

//UNDONE
public class MaxSumOfThreeSubarrays689 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] ans = new int[3];
        int[][] dp = new int[4][nums.length + 1];
        int[][] pi = new int[4][nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            dp[0][i] = 0;
        }
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }
        for (int i = 1; i < 4; i++) {
            for (int j = k; j <= nums.length; j++) { // j: interval end, 1-index based due to sequence dp
                //do not choose j as one of the intervals end
                if (j > 0) {
                    dp[i][j] = dp[i][j - 1];
                    pi[i][j] = pi[i][j - 1];
                }
                int temp = preSum[j] - preSum[j - k] + dp[i - 1][j - k];
                if (temp > dp[i][j]) {
                    dp[i][j] = temp;
                    pi[i][j] = j - k + 1;// interval starting point, 1-index based
                }
            }
        }
        ans[2] = pi[3][nums.length] - 1;
        ans[1] = pi[2][ans[2]] - 1;//ans[2] 0-index based next interval starting point, last interval end point should be up to ans[2] - 1, change to 1-index based ans[2] - 1 + 1
        ans[0] = pi[1][ans[1]] - 1;
        return ans;
    }
}