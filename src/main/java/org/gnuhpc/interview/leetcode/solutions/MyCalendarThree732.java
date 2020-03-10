package org.gnuhpc.interview.leetcode.solutions;

import java.util.TreeMap;

/**
 * Copyright gnuhpc 2019/10/10
 */
public class MyCalendarThree732 {
    //这种做法是这类问题的一个套路
    private TreeMap<Integer, Integer> calendar;

    public MyCalendarThree732() {
        calendar = new TreeMap<>();
    }

    public int book(int start, int end) {

        // 添加至日程中
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);

        // 记录最大活跃的日程数
        int max = 0;
        // 记录活跃的日程数
        int active = 0;

        for (Integer d : calendar.values()) {
            // 以时间线统计日程
            active += d;

            // 找到活跃事件数量最多的时刻，记录下来。
            if (active > max) {
                max = active;
            }
        }

        return max;
    }
}
