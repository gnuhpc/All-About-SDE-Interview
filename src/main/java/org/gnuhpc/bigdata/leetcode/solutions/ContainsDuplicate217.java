package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ContainsDuplicate217 {
    public boolean containsDuplicate(int[] nums) {
        boolean result = false;
        Map<Integer, Integer> records = new HashMap<>();

        for (int i : nums) {
            records.put(i, records.getOrDefault(i, 0) + 1);
            if (records.get(i) > 1) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * 本题不需要用hashmap，只需要hashset即可
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            else set.add(nums[i]);
        }
        return false;

    }

}
