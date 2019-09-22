package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;

public class MaxSubArrayLen {
    public int maxSubArrayLen(int[] nums, int k) {
        // Write your code here
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) max = i + 1; //特殊情况
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            // 只保留第一个sum及index，这样sum-k能取到更大的区间
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }
}
