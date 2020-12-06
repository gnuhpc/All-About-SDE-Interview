package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/12/5
 */
public class IsPossible659 {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> mapCount = new HashMap<>();
        Map<Integer, Integer> mapEndCount = new HashMap<>();

        for (int num : nums) {
            Integer val = mapCount.getOrDefault(num, 0) + 1;
            mapCount.put(num, val);
        }

        for (int num : nums) {
            if (!mapCount.containsKey(num) || mapCount.get(num) == 0) continue;
            mapCount.put(num, mapCount.get(num) - 1);
            if (mapEndCount.containsKey(num - 1) && mapEndCount.get(num - 1) > 0) {
                mapEndCount.put(num - 1, mapEndCount.getOrDefault(num - 1, 0) - 1);
                mapEndCount.put(num, mapEndCount.getOrDefault(num, 0) + 1);
            } else if (mapCount.containsKey(num + 1) && mapCount.get(num + 1) > 0 && mapCount.containsKey(num + 2) && mapCount.get(num + 2) > 0) {
                mapCount.put(num + 1, mapCount.get(num + 1) - 1);
                mapCount.put(num + 2, mapCount.get(num + 2) - 1);
                mapEndCount.put(num + 2, mapEndCount.getOrDefault(num + 2, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
