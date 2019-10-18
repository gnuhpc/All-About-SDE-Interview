package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MaxSubArrayLen {
    public int maxSubArrayLen(int[] nums, int k) {
        int ans = 0;

        //相要的值 -- 和终止的对应位置
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            sums[i + 1] = sums[i] + nums[i];
            map.put(sums[i + 1] - k, i + 1);
        }

        for (int i = 0; i < n; ++i) {
            Integer j = map.get(sums[i]);
            if (j!=null){
                ans = Math.max(ans,j-i);
            }
        }

        return ans;
    }

    public int maxSubArrayLen2(int[] nums, int k) {
        int n = nums.length, ans = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();


        for (int i = 0; i < n; ++i) {
            map.putIfAbsent(sum, i);

            sum += nums[i];
            ans = Math.max(ans, i + 1 - map.getOrDefault(sum - k, i + 1));
        }

        return ans;
    }

    @Test
    public void test() {
        System.out.println(maxSubArrayLen(new int[]{1, 2, 3,2}, 6));
    }

}
