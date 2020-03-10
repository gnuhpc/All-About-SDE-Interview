package org.gnuhpc.interview.leetcode.utils;

import java.util.Comparator;
import java.util.Objects;

/**
 * Created_By: stefanie
 * Date: 14-11-15
 * Time: 下午4:47
 */
public class Point {
    public int x;
    public int y;

    public static final Comparator<Point> sortX = new compareX();
    public static final Comparator<Point> sortY = new compareY();

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) return false;
        Point point = (Point) obj;
        return point.x == x && point.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public float[] getFloat() {
        return new float[]{x, y};
    }

    private static class compareX implements Comparator<Point> {
        //comparator for comparing x-coordinates
        public int compare(Point o1, Point o2) {
            if (o1.x < o2.x) return -1;
            if (o1.x > o2.x) return +1;
            return 0;
        }
    }

    private static class compareY implements Comparator<Point> {
        //comparator for comparing y-coordinates
        public int compare(Point o1, Point o2) {
            if (o1.y < o2.y) return -1;
            if (o1.y > o2.y) return +1;
            return 0;
        }
    }
}
