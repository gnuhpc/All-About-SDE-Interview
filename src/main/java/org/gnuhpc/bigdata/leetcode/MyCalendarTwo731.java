package org.gnuhpc.bigdata.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarTwo731 {
    TreeMap<Integer, int[]> mapCache;
    TreeMap<Integer, int[]> twoCrossCache;

    public MyCalendarTwo731() {
        this.mapCache = new TreeMap<>();
        this.twoCrossCache = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        if (mapCache.isEmpty()) {
            mapCache.put(end,new int[]{start,end});
            return true;
        } else{
            if (!twoCrossCache.isEmpty()){
                if(!checkTwoCrossCache(start,end))
                    return false;
            }

            checkMapCache(start,end);
            return true;
        }
    }

    private void checkMapCache(int start, int end) {
        int[] crossAfter = null, crossBefore = null;

        Map.Entry<Integer, int[]> after = mapCache.ceilingEntry(end);
        Map.Entry<Integer, int[]> before = mapCache.floorEntry(end);

        if (after!=null) crossAfter = crossSection(after.getValue(), new int[]{start,end});
        if (before!=null) crossBefore = crossSection(before.getValue(), new int[]{start,end});
        if (crossBefore!=null && crossAfter!=null){
            mapCache.put(crossAfter[0], new int[]{crossBefore[1],crossAfter[0]});
        }
        if (crossBefore!=null){
            twoCrossCache.put(crossBefore[1],crossBefore);
        }
        if (crossAfter!=null){
            twoCrossCache.put(crossAfter[1],crossAfter);
        }
    }

    private boolean checkTwoCrossCache(int start, int end) {
        boolean isCrossAfter = false, isCrossBefore = false;
        Map.Entry<Integer, int[]> after = twoCrossCache.ceilingEntry(end);
        Map.Entry<Integer, int[]> before = twoCrossCache.floorEntry(end);

        if (after!=null) isCrossAfter = isCross(after.getValue(), new int[]{start,end});
        if (before!=null) isCrossBefore = isCross(before.getValue(), new int[]{start,end});

        if (!isCrossAfter && !isCrossBefore){
            return true;
        } else{
            return false;
        }
    }

    private int[] crossSection(int[] interval1, int[] interval2) {
        if (!isCross(interval1,interval2)) return null;
        if (interval1[0] > interval2[0]){
            int[] temp = interval1;
            interval1 = interval2;
            interval2 = temp;
        }

        return new int[]{interval2[0], interval1[1]};
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
        MyCalendarTwo731 calendar = new MyCalendarTwo731();

        System.out.println(calendar.book(10, 20)); // returns true
        System.out.println(calendar.book(50, 60)); // returns true
        System.out.println(calendar.book(10, 40)); // returns true
        System.out.println(calendar.book(5, 15)); // returns false
        System.out.println(calendar.book(5, 10)); // returns true
        System.out.println(calendar.book(25, 55)); // returns true

    }
}
