package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.algorithm.search.QuickSelect;
import org.junit.Test;

import java.util.*;

public class MinTotalDistance296 {
    class Point {
        public int x;
        public int y;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public int distance;
    }

    private int distance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private List<Point> collectPoints(int[][] grid) {
        List<Point> res = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res.add(new Point(i, j, 0));
                }
            }
        }

        return res;
    }

    /*
    Method1: 暴力解法
     */
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int min = Integer.MAX_VALUE;
        List<Point> pList = collectPoints(grid);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int d = 0;
                for (Point p : pList) {
                    d += distance(p, new Point(i, j, 0));
                }

                min = Math.min(min, d);
            }
        }

        return min;
    }

    /*
    Method2: Quick select
     */
    public int minTotalDistance2(int[][] grid) {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    x.add(i);
                    y.add(j);
                }
            }
        }

        QuickSelect qs1 = new QuickSelect(x.stream().mapToInt(i -> i).toArray());
        QuickSelect qs2 = new QuickSelect(y.stream().mapToInt(i -> i).toArray());

        // get median of x[] and y[] using quick select //求中位数
        int mx = qs1.select(x.size() / 2 + 1);
        int my = qs2.select(y.size() / 2 + 1);

        // calculate the total Manhattan distance
        int total = 0;
        for (int i = 0; i < x.size(); i++) {
            total += Math.abs(x.get(i) - mx) + Math.abs(y.get(i) - my);
        }
        return total;
    }

    @Test
    public void test() {
        System.out.println(minTotalDistance2(new int[][]{
                {1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}
        }));
        System.out.println(minTotalDistance2(new int[][]{
                {1, 0, 1}
        }));
    }
}
