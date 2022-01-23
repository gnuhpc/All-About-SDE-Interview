package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2021/12/30
 */
public class IsNStraightHand846 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> hashmap = new HashMap<>();
        for (int i : hand)
            hashmap.put(i, hashmap.getOrDefault(i, 0) + 1);

        for (int i : hand) {
            if (hashmap.get(i) > 0) {
                for (int j = 0; j < groupSize; j++) {
                    if (hashmap.get(i + j) == null)
                        return false;
                    hashmap.put(i + j, hashmap.get(i + j) - 1);
                }
            }
        }
        return true;

    }
}
