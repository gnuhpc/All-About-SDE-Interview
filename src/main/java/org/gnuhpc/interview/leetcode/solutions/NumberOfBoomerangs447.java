package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs447 {
    private int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }

    public int numberOfBoomerangs(int[][] points) {
        if (points == null) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> disCount = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int distance = getDistance(points[i], points[j]);
                int count = disCount.getOrDefault(distance, 0);
                disCount.put(distance, count + 1);
            }

            //选择计算
            for (int count : disCount.values()) {
                ans += count * (count - 1);
            }
        }
        return ans;
    }
}
