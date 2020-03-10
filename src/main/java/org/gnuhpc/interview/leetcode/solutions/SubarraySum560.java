package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SubarraySum560 {
    /*
    Method1 :presum O(n^2)
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构造前缀和
        int[] presum = new int[n + 1];
        presum[0] = 0;
        for (int i = 0; i < n; i++)
            presum[i + 1] = presum[i] + nums[i];

        int res = 0;
        // 穷举所有子数组

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (presum[j + 1] - presum[i] == k) {
                    res++;
                }
            }
        }

        return res;
    }

    /*
    TwoSum思路--hash + presum
     */
    public int subarraySum2(int[] nums, int k) {
        // Edge cases
        if (nums.length == 0) return 0;

        // hashmap + preSum
        /*
            1. Hashmap<sum[0,i - 1], frequency>
            //下面是很通用的一个模板 preSum = 总Sum - 区间Sum
            2. sum[i, j] = sum[0, j] - sum[0, i - 1]    --> sum[0, i - 1] = sum[0, j] - sum[i, j]
                   k           sum      hashmap-key     -->  hashmap-key  =  sum - k
            3. now, we have k and sum.
                  As long as we can find a sum[0, i - 1], we then get a valid subarray
                 which is as long as we have the hashmap-key,  we then get a valid subarray
            4. Why don't memo.put(sum[0, i - 1], 1) every time ?
                  if all numbers are positive, this is fine
                  if there exists negative number, there could be preSum frequency > 1
        */
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int result = 0;
        //preSum是0出现了1次
        map.put(0, 1);
        for (int cur : nums) {
            sum += cur;
            if (map.containsKey(sum - k))  // there exist a key, that [hashmap-key  =  sum - k]
                result += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    @Test
    public void test() {
        subarraySum(new int[]{1, 1, 1}, 2);
    }

}
/* From https://www.jiuzhang.com/solution/subarray-sum/ 是具体求一个位置而不是计数，此时HashMap的定义有变化
// 这个案例中相当于上一题中k=0的情况
  public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        int len = nums.length;

        ArrayList<Integer> ans = new ArrayList<Integer>();
        // <PrefixSum, position>
        HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();

        memo.put(0, -1);

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];

            if (memo.containsKey(sum)) {
                ans.add(memo.get(sum) + 1);//起始位置
                ans.add(i);//结束位置
                return ans;
            }

            memo.put(sum, i);
        }

        return ans;
    }


 */
