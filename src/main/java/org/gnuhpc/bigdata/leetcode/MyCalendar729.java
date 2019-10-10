package org.gnuhpc.bigdata.leetcode;

import java.util.*;

public class MyCalendar729 {
    TreeMap<Integer, Integer> calendar = new TreeMap<>();

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start), next = calendar.ceilingKey(start);
        // 如果start在所有会议之前,或者在前一个会议结束之后;end在所有会议之后,或者下一个会议开始之前，说明是可以安排的
        if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        MyCalendar729 calendar = new MyCalendar729();

        System.out.println(calendar.book(37, 50));
        System.out.println(calendar.book(33, 50));
    }
}
