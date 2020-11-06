package org.gnuhpc.interview.leetcode.solutions;

import java.util.Map;
import java.util.TreeMap;

/**
 * Copyright gnuhpc 2020/4/16
 */
public class CarPooling1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> map = new TreeMap();
        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }
        int persons = 0;
        for (int value : map.values()) {
            persons += value;
            if(persons > capacity) return false;
        }
        return true;
    }

    public boolean carPooling2(int[][] trips, int capacity) {
        int stops[] = new int[1001];
        for (int[] t : trips) {
            stops[t[1]] += t[0];
            stops[t[2]] -= t[0];
        }
        for (int i = 0; i < stops.length; i++) {
            capacity -= stops[i];
            if (capacity < 0) return false;
        }
        return true;
    }
}
