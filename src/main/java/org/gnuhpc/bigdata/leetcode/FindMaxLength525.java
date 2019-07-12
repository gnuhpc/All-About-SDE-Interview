package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FindMaxLength525 {
    //Method1: 暴力解法，超时，不过遍历所有子数组的方法值得理解
    public int findMaxLength(int[] nums) {
        int maxlen = 0;
        for (int start = 0; start < nums.length; start++) {
            int zeroes = 0, ones = 0;
            for (int end = start; end < nums.length; end++) {
                if (nums[end] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if (zeroes == ones) {
                    maxlen = Math.max(maxlen, end - start + 1);
                }
            }
        }
        return maxlen;
    }

    //Method2 : memorization
    /*
    将原数组的0全部变为-1 则问题等价于“元素值总和为0的连续数组”
    接着遍历数组 记录当前的前缀和的值
    若该前缀和的值已出现过
    则说明标记中的下标到当前扫描的下标的这段数组的总和值是为0的
    Sum[a,b] == Sum[a,c] => Sum[b.c]=0 -> max(c-b, res)
     */

    public int findMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == 0 && i > res) {
                res = i + 1;
            }
            if (map.containsKey(sum)) {
                res = Math.max(i - map.get(sum), res);
            } else {
                map.put(sum, i);
            }
        }
        return res;

    }

    @Test
    public void test(){
        findMaxLength2(new int[]{0,1,0,1,1,1,0,0,0});
    }

}
