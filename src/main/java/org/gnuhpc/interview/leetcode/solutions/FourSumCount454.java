package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class FourSumCount454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> record = new HashMap<>();
        int res = 0;
        for (int i : C) {
            for (int j : D) {
                if (record.containsKey(i + j)) {
                    record.put(i + j, record.get(i + j) + 1);
                } else {
                    record.put(i + j, 1);
                }
            }
        }

        for (int i : A) {
            for (int j : B) {
                if (record.containsKey(0 - i - j)) {
                    res += record.get(0 - i - j);
                }
            }
        }

        return res;

    }
}
