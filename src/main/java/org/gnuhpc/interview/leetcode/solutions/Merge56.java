package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Interval;
import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.*;

public class Merge56 {
    public int[][] merge(int[][] intervals) {
        List<Interval> intervalList = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalList.add(new Interval(intervals[i][0], intervals[i][1], Interval.COMPARETYPE.START));
        }

        Collections.sort(intervalList);
        LinkedList<Interval> merged = new LinkedList<>();
        for (Interval interval : intervalList)
            if (merged.isEmpty() || merged.getLast().end < interval.start)//第一次加入或者无法合并
                merged.add(interval);
            else merged.getLast().end = Math.max(merged.getLast().end, interval.end);

        //最后整理结果
        int[][] res = new int[merged.size()][2];

        for (int i = 0; i < res.length; i++) {
            res[i][0] = merged.get(i).start;
            res[i][1] = merged.get(i).end;
        }

        return res;
    }

    //Method 2:  暴力解法，只是不用从array-> object List -> array ，时间和内存都可以节省出来
    public int[][] merge2(int[][] intervals) {
        if (intervals.length < 2)
            return intervals;

        boolean[] merged = new boolean[intervals.length];// 标记合并过的元素
        int mergedCount = 0;// 合并过的计数，用于最后初始化结果数组长度

        for (int i = 0; i < intervals.length; i++) {
            int[] a = intervals[i];

            for (int j = i + 1; j < intervals.length; j++) {
                int[] b = intervals[j];

                // 区间重叠，合并到后面一个元素上，前一个元素标记为已合并
                if (a[1] >= b[0] && a[0] <= b[1]) {
                    merged[i] = true;
                    mergedCount++;

                    b[0] = Math.min(a[0], b[0]);
                    b[1] = Math.max(a[1], b[1]);
                    break;//这个a区间已经被合并了，就不用再往下找了
                }
            }
        }

        // 组装返回结果
        int[][] result = new int[intervals.length - mergedCount][];
        for (int i = 0, pos = 0; i < intervals.length; i++) {
            if (!merged[i]) {
                result[pos++] = intervals[i];
            }
        }

        return result;
    }

    /*
    Method 3: TreeMap
     */

    public int[][] merge3(int[][] intervals) {
        if (intervals.length == 0) return new int[0][0];
        List<int[]> list = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] itv : intervals) {//添加区间节点的过程
            map.put(itv[0], map.getOrDefault(itv[0], 0) + 1);
            map.put(itv[1], map.getOrDefault(itv[1], 0) - 1);
        }
        int count = 0, start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
        for (int k : map.keySet()) {
            count += map.get(k);
            start = Math.min(start, k);
            end = Math.max(end, k);
            if (count == 0) {//关键：count==0代表区间闭合了，需要把刚刚闭合的区间添加到结果集中。可以类比字符串中左右括号的情况，遇到左括号+1，右括号-1。当count==0时，区间的括号是完全匹配的。
                list.add(new int[]{start, end});
                start = Integer.MAX_VALUE;
                end = Integer.MIN_VALUE;
            }
        }
        return list.toArray(new int[list.size()][2]);
    }

    @Test
    public void test() {

        int[][] a = {
                {1, 2},
                {2, 3}
        };

        Utils.print2DArray(merge3(a));
    }

}
