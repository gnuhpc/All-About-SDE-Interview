package org.gnuhpc.interview.concurrency.immutable.immutableclass;

public class Line {
    private final Point startPoint;
    private final Point endPoint;

    public Line(int startx, int starty, int endx, int endy) {
        this.startPoint = new Point(startx, starty);
        this.endPoint = new Point(endx, endy);
    }

    public Line(Point startPoint, Point endPoint) {//注意这里有个坑，就是不能直接将一个mutable 的object引用给一个immutable的对象
        this.startPoint = new Point(startPoint.x, startPoint.y);
        this.endPoint = new Point(endPoint.x, endPoint.y);
    }

    public int getStartX() {
        return startPoint.getX();
    }

    public int getStartY() {
        return startPoint.getY();
    }

    public int getEndX() {
        return endPoint.getX();
    }

    public int getEndY() {
        return endPoint.getY();
    }

    public String toString() {
        return "[ Line: " + startPoint + "-" + endPoint + " ]";
    }
}
