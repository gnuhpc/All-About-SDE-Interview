package org.gnuhpc.bigdata.leetcode.utils;

import java.util.Objects;

/**
 * Created_By: stefanie
 * Date: 14-11-15
 * Time: 下午4:47
 */
public class Point {
    public int x;
    public int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point)) return false;
        Point point = (Point) obj;
        return point.x == x && point.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }

    public float[] getFloat(){
        return new float[]{x, y};
    }
}
