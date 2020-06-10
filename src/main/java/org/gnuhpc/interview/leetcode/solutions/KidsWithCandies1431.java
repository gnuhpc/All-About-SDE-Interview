package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/6/1
 */
public class KidsWithCandies1431 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List list = new ArrayList<Boolean>(candies.length);
        int max = 0;
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] > max) {
                max = candies[i];
            }
        }
        for (int j = 0; j < candies.length; j++) {
            list.add(candies[j] + extraCandies >= max);
        }
        return list;
    }
}
