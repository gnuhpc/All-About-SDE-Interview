package org.gnuhpc.bigdata.leetcode;

import java.util.*;

public class MyCalendar729 {
    TreeMap<Integer, int[]> mapCache;

    public MyCalendar729() {
        this.mapCache = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        if (mapCache.isEmpty()) {
            mapCache.put(end,new int[]{start,end});
            return true;
        } else{
            boolean isCrossAfter = false,isCrossBefore = false;
            Map.Entry<Integer, int[]> after = mapCache.ceilingEntry(end);
            Map.Entry<Integer, int[]> before = mapCache.floorEntry(end);

            if (after!=null) isCrossAfter = isCross(after.getValue(), new int[]{start,end});
            if (before!=null) isCrossBefore = isCross(before.getValue(), new int[]{start,end});

            if (!isCrossAfter && !isCrossBefore){
                mapCache.put(end,new int[]{start,end});
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean isCross(int[] interval1, int[] interval2) {
        if (interval1[0] > interval2[0]){
            int[] temp = interval1;
            interval1 = interval2;
            interval2 = temp;
        }
        return interval1[1]>interval2[0];
    }

    public static void main(String[] args) {
        MyCalendar729 calendar = new MyCalendar729();

        System.out.println(calendar.book(37,50));
        System.out.println(calendar.book(33,50));
    }
}
