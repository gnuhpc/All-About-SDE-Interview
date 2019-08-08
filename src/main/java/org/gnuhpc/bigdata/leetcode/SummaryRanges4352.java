package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Interval;
import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 19-8-4
 */

//HashMap
public class SummaryRanges4352 {

    HashMap<Integer,Integer> map;
    HashMap<Integer, Integer> Intervals;

    /** Initialize your data structure here. */
    public SummaryRanges4352() {
        map = new HashMap<>();
        Intervals = new HashMap<>();
    }

    public void addNum(int val) {
        if(map.containsKey(val))
            return;
        else{
            map.put(val,1);
            if(map.containsKey(val+1) && map.containsKey(val-1)){
                int l = map.get(val+1);
                int r = map.get(val-1);
                map.put(val+l,l+r+1);
                map.put(val-r,l+r+1);
                Intervals.remove(val+1);
                Intervals.put(val-r,l+r+1);
            }
            else if(map.containsKey(val+1)){
                int l = map.get(val+1);
                map.put(val+l,l+1);
                map.put(val,l+1);
                Intervals.remove(val+1);
                Intervals.put(val,l+1);
            }
            else if(map.containsKey(val-1)){
                int r = map.get(val-1);
                map.put(val-r,r+1);
                map.put(val,r+1);
                Intervals.put(val-r,r+1);
            }
            else
                Intervals.put(val,1);
        }
    }

    public int[][] getIntervals() {
        List<Interval> list = new ArrayList<>();
        for(Integer i: Intervals.keySet())
            list.add(new Interval(i,i+Intervals.get(i)-1));

        Collections.sort(list, Comparator.comparingInt(o -> o.start));
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            int[] temp = new int[2];
            temp[0] = list.get(i).start;
            temp[1] = list.get(i).end;
            res[i] = temp;
        }

        return res;
    }



    @Test
    public void test() {
        SummaryRanges4352 summaryRanges = new SummaryRanges4352();
        addNum(1);
        addNum(3);
        addNum(7);
        addNum(2);
        addNum(6);

        Utils.print2DArray(getIntervals());
    }
}
