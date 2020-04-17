package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

public class MyCalendar729 {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public boolean book(int start, int end) {
        Integer prev = map.floorKey(start), next = map.ceilingKey(start);
        // 如果start在所有会议之前,或者在前一个会议结束之后;end在所有会议之后,或者下一个会议开始之前，说明是可以安排的
        if ((prev == null || map.get(prev) <= start) && (next == null || end <= next)) {
            map.put(start, end);
            return true;
        }
        return false;
    }

    public boolean book2(int start, int end) {
        int count = 0;
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        boolean canBook = true;
        for (int v : map.values()) {
            count += v;
            if (count > 1) {//最多出现冲突日程不能大于2
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
        MyCalendar729 calendar = new MyCalendar729();

        System.out.println(calendar.book(37, 50));
        System.out.println(calendar.book(33, 50));
    }
}
