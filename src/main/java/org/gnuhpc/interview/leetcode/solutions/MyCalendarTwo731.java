package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Interval;

import java.util.TreeSet;

public class MyCalendarTwo731 {
    private TreeSet<Interval> single = new TreeSet<>(), doub = new TreeSet<>();

    public boolean book(int start, int end) {
        Interval cur = new Interval(start, end, Interval.COMPARETYPE.OVERLAPEQUAL);
        if (doub.contains(cur)) {
            return false;
        }
        Interval dup = single.ceiling(cur);
        if (dup == null || dup.compareTo(cur) != 0) {
            single.add(cur);
        } else {
            single.remove(dup);
            //分段
            int a = Math.min(start, dup.start), b = Math.max(start, dup.start);
            int c = Math.min(end, dup.end), d = Math.max(end, dup.end);
            book(a, b);//重新book
            book(c, d);//重新book
            doub.add(new Interval(b, c, Interval.COMPARETYPE.OVERLAPEQUAL));
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
