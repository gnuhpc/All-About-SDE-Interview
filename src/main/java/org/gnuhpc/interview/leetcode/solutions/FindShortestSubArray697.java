package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/4/26
 */
public class FindShortestSubArray697 {
    public int findShortestSubArray(int[] nums) {
        if (nums.length == 1) return 1;

        Map<Integer, List<Integer>> map = new HashMap<>();

        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (map.containsKey(n)) {
                map.get(n).add(i);
                max = Math.max(map.get(n).size(), max);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(i);
                map.put(n, l);
                max = Math.max(l.size(), max);
            }
        }

        int res = nums.length;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> v = entry.getValue();
            if (v.size() == max) {
                res = Math.min(res, v.get(v.size() - 1) - v.get(0));
            }
        }

        return res + 1;
    }
}
