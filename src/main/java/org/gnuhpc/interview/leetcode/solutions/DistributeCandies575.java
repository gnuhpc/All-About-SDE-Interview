package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/8/10
 */
public class DistributeCandies575 {
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(set.size(), candies.length / 2);
    }
}
