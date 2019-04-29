package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//很通用的一个模板
public class SubarraySum560 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);   //key: preSum, value: frequency
        int count = 0;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            if (preSum.containsKey(curSum - k))
                count += preSum.get(curSum - k);
            // it means there is some sum value v between 0 and x, which makes sum of array [x + 1 to i] == k
            // the frequency is the number of x
            preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        }
        return count;
    }

    @Test
    public void test(){
        subarraySum(new int[]{0,0,0,0,0,0,0,0,0,0},0);
    }

}
/* From https://www.jiuzhang.com/solution/subarray-sum/ 是具体求一个位置而不是计数，此时HashMap的定义有变化
  public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here

        int len = nums.length;

        ArrayList<Integer> ans = new ArrayList<Integer>();
        // <PrefixSum, position>
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, -1);

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];

            if (map.containsKey(sum)) {
                ans.add(map.get(sum) + 1);
                ans.add(i);
                return ans;
            }

            map.put(sum, i);
        }

        return ans;
    }


 */
