package org.gnuhpc.interview.leetcode.solutions;

import java.util.TreeMap;

public class MyCalendarTwo7312 {

    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public boolean book(int start, int end) {
        int count = 0;
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        boolean canBook = true;
        for (int v : map.values()) {
            count += v;
            if (count > 2) {//最多出现冲突日程不能大于2
                canBook = false;
                break;
            }
        }
        if (!canBook) {
            map.put(start, map.get(start) - 1);
            map.put(end, map.get(end) + 1);
        }

        return canBook;
    }


    public static void main(String[] args) {
        MyCalendarTwo7312 calendar = new MyCalendarTwo7312();

        System.out.println(calendar.book(10, 20)); // returns true
        System.out.println(calendar.book(50, 60)); // returns true
        System.out.println(calendar.book(15, 55)); // returns true

    }
}
