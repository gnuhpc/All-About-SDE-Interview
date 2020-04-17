package org.gnuhpc.interview.leetcode.utils;

import java.util.List;

public class Interval implements Comparable<Interval> {
    public enum COMPARETYPE {
        OVERLAPEQUAL, START
    }

    //Overlap的时候是否视作相等
    private final COMPARETYPE compareType;
    public int start;
    public int end;

    public Interval(int start, int end, COMPARETYPE compareType) {
        this.start = start;
        this.end = end;
        this.compareType = compareType;
    }

    public Interval(int start, int end) {
        this(start, end, COMPARETYPE.START);
    }

    @Override
    public String toString() {
        return "interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public static void print(List<Interval> intervals) {
        for (Interval interval : intervals)
            System.out.println(interval);
    }

    public int compareToOverLapEqual(Interval other) {
        if (end <= other.start) {
            return -1;
        } else if (start >= other.end) {
            return 1;
        }
        return 0;
    }

    public int compareToOverLap(Interval other) {
        return this.start - other.start;
    }

    @Override
    public int compareTo(Interval other) {
        if (compareType == COMPARETYPE.OVERLAPEQUAL) return compareToOverLapEqual(other);
        else if (compareType == COMPARETYPE.START) return compareToOverLap(other);
        else return 0;
    }
}
