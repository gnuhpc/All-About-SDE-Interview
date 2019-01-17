package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Point;

public class MaxPoints149 {
    public int maxPoints(Point[] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        int n = points.length;
        int max = 2;
        Point a, b, c;
        for (int i = 1; i < n; i++) {
            a = points[i - 1];
            b = points[i];
            double k = getSlop(a, b);
            int count = 2;
            for (int j = 0; j < n; j++) {
                if (j == i || j == i - 1) {
                    continue;
                }
                c = points[j];
                if (overlap(a, c) || overlap(b, c) || k == getSlop(b, c)) {
                    count++;
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }

    private boolean overlap(Point a, Point b) {
        return a.x == b.x && a.y == b.y;
    }

    private double getSlop(Point a, Point b) {
        if (a.x == b.x) {
            return Double.POSITIVE_INFINITY;
        } else {
            return ((double) a.y - b.y) / ((double) a.x - b.x);
        }
    }
}
