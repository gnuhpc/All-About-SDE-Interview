package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CheckSubarraySum523 {
    /*
    Method1: 前缀和
     */

    public boolean checkSubarraySum(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        for (int i = 0; i < preSum.length; i++) {
            for (int j = i + 2; j < preSum.length; j++) {
                int temp = preSum[j] - preSum[i];
                if ((temp == 0 && k == 0) || (k != 0 && temp % k == 0))
                    return true;
            }
        }
        return false;
    }


    /*
    Method2： 前缀和+HashSet
    重写下题目：定义presum[i]为数组到i的前缀和，在数组中找到两个index i, j，使得presum[j] - prefix[i] = m * k, m是一个整数并且j - i >= 2

经过推理题目可以简化成：找到两个index i, j，使得presum[j] % k = presum[i] % k 并且j - i >= 2

我们在一遍遍历的过程中，遍历到每个index j时，假如presum[j] % k 已经在之前出现过，并且之前出现的位置i满足j - i >= 2，就可以直接返回true。

作者：lrc0100
链接：https://leetcode-cn.com/problems/continuous-subarray-sum/solution/java-zai-hashmapde-ji-chu-shang-xiao-xiao-you-hua-/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */


    public boolean checkSubarraySum2(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        if (k == 0) {
            for (int i = 0; i < nums.length; ++i) {
                //至少有两个连续0
                if (i + 1 < nums.length && nums[i] + nums[i + 1] == 0) {
                    return true;
                }
            }
            return false;
        }
        Set<Integer> dp = new HashSet<>();
        int preSumRem = 0;
        for (int num : nums) {
            int prev = preSumRem;
            preSumRem = (preSumRem + num) % k;
            if (dp.contains(preSumRem)) {
                return true;
            }
            dp.add(prev);
        }

        return false;
    }


}
