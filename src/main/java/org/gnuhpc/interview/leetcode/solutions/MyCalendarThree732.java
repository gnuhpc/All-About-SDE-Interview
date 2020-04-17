package org.gnuhpc.interview.leetcode.solutions;

import java.util.TreeMap;

/**
 * Copyright gnuhpc 2019/10/10
 */
public class MyCalendarThree732 {
    //这种做法是这类问题的一个套路
    private TreeMap<Integer, Integer> map = new TreeMap<>();
    public int book(int start, int end) {

        // 添加至日程中
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        // 记录最大活跃的日程数
        int max = 0;
        // 记录活跃的日程数
        int active = 0;

        for (int d : map.values()) {
            // 以时间线统计日程
            active += d;

            // 找到活跃事件数量最多的时刻，记录下来。
            max = Math.max(active, max);
        }

        return max;
    }
}
