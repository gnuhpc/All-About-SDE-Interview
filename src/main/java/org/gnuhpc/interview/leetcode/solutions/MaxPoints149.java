package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Point;

public class MaxPoints149 {
    public int maxPoints(int[][] points) {
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
            a = new Point(points[i - 1][0], points[i - 1][1]);
            b = new Point(points[i][0], points[i][1]);
            double k = getSlop(a, b);
            int count = 2;
            for (int j = 0; j < n; j++) {
                if (j == i || j == i - 1) {
                    continue;
                }
                c = new Point(points[j][0], points[j][1]);
                if (a.equals(c) || b.equals(c) || k == getSlop(b, c)) {
                    count++;
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }

    private double getSlop(Point a, Point b) {
        if (a.x == b.x) {
            return Double.POSITIVE_INFINITY;
        } else {
            return ((double) a.y - b.y) / ((double) a.x - b.x);
        }
    }
}
