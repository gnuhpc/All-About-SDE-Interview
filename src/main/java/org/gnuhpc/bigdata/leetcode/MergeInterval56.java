package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

public class MergeInterval56 {
    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(o -> o.start));
        LinkedList<Interval> merged = new LinkedList<>();
        for (Interval interval : intervals)
            if (merged.isEmpty() || merged.getLast().end < interval.start)
                merged.add(interval);
            else merged.getLast().end = Math.max(merged.getLast().end, interval.end);
        return merged;
    }



    @Test
    public void test(){
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(8,10));
        list.add(new Interval(1,3));
        list.add(new Interval(2,6));
        list.add(new Interval(15,18));

        merge(list);
    }

}
