package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarTwo7312 {

    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
            if (count > 2) {
                map.put(start, map.get(start) - 1);
                if (map.get(start) == 0) { //可以不要，但是这个可以节省内存也节省运行时间
                    map.remove(start);
                }
                map.put(end, map.get(end) + 1);//同上的优化
                if (map.get(end) == 0) {
                    map.remove(end);
                }
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        MyCalendarTwo7312 calendar = new MyCalendarTwo7312();

        System.out.println(calendar.book(10, 20)); // returns true
        System.out.println(calendar.book(50, 60)); // returns true
        System.out.println(calendar.book(15, 55)); // returns true

    }
}
