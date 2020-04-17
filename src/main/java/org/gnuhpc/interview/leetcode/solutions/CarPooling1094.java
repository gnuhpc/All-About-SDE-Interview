package org.gnuhpc.interview.leetcode.solutions;

import java.util.TreeMap;

/**
 * Copyright gnuhpc 2020/4/16
 */
public class CarPooling1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> map = new TreeMap();
        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);//到点上车
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);//到点下车
        }
        int persons = 0;
        for (int k : map.keySet()) {
            persons += map.get(k);
            if (persons > capacity) return false;
        }
        return true;
    }
}
