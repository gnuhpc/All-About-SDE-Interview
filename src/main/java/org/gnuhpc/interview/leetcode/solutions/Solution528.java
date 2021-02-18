package org.gnuhpc.interview.leetcode.solutions;

import java.util.Random;
import java.util.TreeMap;

/**
 * Copyright gnuhpc 2021/1/25
 */
public class Solution528 {

    private final int range;
    //(wsum, index)
    TreeMap<Integer, Integer> map;

    public Solution528(int[] w) {
        map = new TreeMap();
        map.put(w[0], 0);
        for (int i = 1; i < w.length; i++) {
            w[i] += w[i - 1];
            map.put(w[i], i);
        }

        this.range = map.lastKey();
    }

    public int pickIndex() {
        Random rand = new Random();
        int target = rand.nextInt(range);
        return map.higherEntry(target).getValue();
    }
}
