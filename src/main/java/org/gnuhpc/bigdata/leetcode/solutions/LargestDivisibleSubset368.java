package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.*;

public class LargestDivisibleSubset368 {
    TreeSet<Long>            mem     = new TreeSet();
    long                     max     = 0;
    List<Integer>            ret     = new ArrayList<>();
    Map<Long, List<Integer>> visited = new HashMap<>();

    public List helper(long cur) {
        if (cur > max || !mem.contains(cur)) return null;
        if (visited.containsKey(cur)) return visited.get(cur);
        List list = new ArrayList<>();
        long i = 2; // the factor for the next multiple of current value
        while (mem.ceiling(cur * i) != null) { // no more multiple
            // use O(log N) to find next multiple
            long next = mem.ceiling(cur * i);
            if (next % cur == 0) {
                List temp = helper(next);
                if (temp != null && temp.size() > list.size())
                    list = new ArrayList(temp);
            }
            i = (next / cur) + 1; // increase the factor based on the gap.
        }
        list.add((int) cur);
        visited.put(cur, list);
        return list;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return ret;
        int len = nums.length;
        if (len == 1) {
            ret.add(nums[0]);
            return ret;
        }
        for (int num : nums) {
            mem.add((long) num);
        }
        max = mem.last();
        for (int num : nums) {
            List temp = helper((long) num);
            if (temp != null && temp.size() > ret.size())
                ret = new ArrayList(temp);
        }
        return ret;
    }

}
