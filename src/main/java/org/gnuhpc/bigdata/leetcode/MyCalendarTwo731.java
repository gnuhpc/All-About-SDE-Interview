package org.gnuhpc.bigdata.leetcode;

import java.util.TreeSet;

public class MyCalendarTwo731 {
    private static class Interval implements Comparable<Interval> {
        int start, end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        public int compareTo(Interval other) {
            if (end <= other.start) {
                return -1;
            }
            else if (start >= other.end) {
                return 1;
            }
            return 0;
        }
    }

    private TreeSet<Interval> single = new TreeSet<>(), doub = new TreeSet<>();

    public boolean book(int start, int end) {
        Interval cur = new Interval(start, end);
        if (doub.contains(cur)) {
            return false;
        }
        Interval dup = single.ceiling(cur);
        if (dup == null || dup.compareTo(cur) != 0) {
            single.add(cur);
        }
        else {
            single.remove(dup);
            int a = Math.min(start, dup.start), b = Math.max(start, dup.start);
            int c = Math.min(end, dup.end), d = Math.max(end, dup.end);
            book(a, b);
            book(c, d);
            doub.add(new Interval(b, c));
        }
        return true;
    }


    public static void main(String[] args) {
        MyCalendarTwo731 calendar = new MyCalendarTwo731();

        System.out.println(calendar.book(10, 20)); // returns true
        System.out.println(calendar.book(50, 60)); // returns true
        System.out.println(calendar.book(10, 40)); // returns true
        System.out.println(calendar.book(5, 15)); // returns false
        System.out.println(calendar.book(5, 10)); // returns true
        System.out.println(calendar.book(25, 55)); // returns true

    }
}
