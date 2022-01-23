package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2019/10/4
 */
/*
The right answer must satisfy two conditions:
1. the large rectangle area should be equal to the sum of small rectangles
2. count of all the points should be even, and that of all the four corner points should be one
 */
public class IsRectangleCover391 {
    Set<Integer> set;

    public boolean isRectangleCover(int[][] rectangles) {
        long area = 0;
        set = new HashSet();

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (int[] rec : rectangles) {
            int x1 = rec[0], y1 = rec[1], x2 = rec[2], y2 = rec[3];
            area += (long) (x2 - x1) * (y2 - y1);

            if (x1 < minX || y1 < minY) {
                minX = x1;
                minY = y1;
            }
            if (x2 > maxX || y2 > maxY) {
                maxX = x2;
                maxY = y2;
            }

            put(hashKey(x1, y1));
            put(hashKey(x1, y2));
            put(hashKey(x2, y1));
            put(hashKey(x2, y2));
        }

        if (area != ((long) (maxX - minX) * (maxY - minY))) return false;
        return set.size() == 4 && set.contains(hashKey(minX, minY)) &&
                set.contains(hashKey(minX, maxY)) &&
                set.contains(hashKey(maxX, minY)) &&
                set.contains(hashKey(maxX, maxY));

    }

    private void put(int key) {
        if (!set.add(key)) set.remove(key);
    }

    //TODO 二维的方法
    private int hashKey(int x, int y) {
        return x * 100001 + y;
    }
}
