package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Merge56 {
    class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public int[][] merge(int[][] intervals) {
        List<Interval> intervalList = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalList.add(new Interval(intervals[i][0], intervals[i][1]));
        }

        intervalList.sort(Comparator.comparingInt(o -> o.start));
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


    //Method 2:  其实也是暴力解法，只是不用从array-> object List -> array ，时间和内存都可以节省出来
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
                    break;
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


    @Test
    public void test() {

        int[][] a = {
                {1, 4},
                {4, 5}
        };

        Utils.print2DArray(merge2(a));
    }

}
