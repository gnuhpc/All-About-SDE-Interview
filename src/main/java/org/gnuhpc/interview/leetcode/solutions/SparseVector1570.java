package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class SparseVector1570 {
    private final Map<Integer, Integer> map;

    SparseVector1570(int[] nums) {
        map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; ++i) {
            int x = nums[i];
            if (x != 0) {
                map.put(i, x);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector1570 vec) {
        int product = 0;
        Map<Integer,Integer> m1 = map.size() < vec.map.size() ? map : vec.map;
        Map<Integer,Integer> m2 = m1 == map ? vec.map : map;
        for (Map.Entry<Integer,Integer> p : m1.entrySet()) {
            Integer k = p.getKey();
            Integer v;
            if ((v = m2.get(k)) != null) {
                product += p.getValue() * v;
            }
        }
        return product;
    }
}
