package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsNearbyDuplicate219 {
    //Force method. O(n^2)
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        boolean result = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && j - i <= k) {
                    return true;
                }
            }
        }

        return false;
    }

    //Method 1: Hash
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        boolean result = false;
        //数字-起始比较位置
        Map<Integer, Integer> records = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (records.containsKey(nums[i])) {
                if (i - records.get(nums[i]) <= k) {
                    return true;
                }
                else { //发现距离大于k，则更新起始比较位置,否则距离会越来越大
                    records.put(nums[i], i);
                }
            }
            else {
                records.put(nums[i], i);
            }
        }

        return false;
    }

    // Sliding Window
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            //窗口以外的，即使相等也没有用了，直接删掉即可
            if (i > k) set.remove(nums[i - k - 1]);
            if (!set.add(nums[i])) return true;
        }
        return false;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 1};
        int k = 3;

        containsNearbyDuplicate(nums, k);
    }
}
