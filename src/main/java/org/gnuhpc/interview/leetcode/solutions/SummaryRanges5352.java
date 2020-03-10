package org.gnuhpc.interview.leetcode.solutions;

import java.util.Map;
import java.util.TreeMap;

// add by tina
public class SummaryRanges5352 {
    TreeMap<Integer, Integer> tm;

    /**
     * Initialize your data structure here.
     */
    public SummaryRanges5352() {
        tm = new TreeMap<>();
    }

    public void addNum(int val) {
        if (tm.containsKey(val)) return;
        Integer low = tm.lowerKey(val);
        Integer high = tm.higherKey(val);
        if (low != null && high != null && tm.get(low) + 1 == val && val + 1 == high) {
            tm.put(low, tm.get(high));
            tm.remove(high);
        } else if (low != null && tm.get(low) + 1 >= val)
            tm.put(low, Math.max(tm.get(low), val));
        else if (high != null && val + 1 == high) {
            tm.put(val, tm.get(high));
            tm.remove(high);
        } else {
            tm.put(val, val);
        }

    }

    public int[][] getIntervals() {
        int[][] res = new int[tm.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : tm.entrySet()) {
            res[i][0] = entry.getKey();
            res[i][1] = entry.getValue();
            i++;
        }
        return res;
    }

}
