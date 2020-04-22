package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Interval;
import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/9/21
 */
public class EraseOverlapIntervals435 {
    /*
    在每次选择中，选择的区间结尾越小，留给后面的区间的空间越大，那么后面能够选择的区间个数也就越大。
    按区间的结尾进行升序排序，每次选择结尾最小，并且和前一个区间不重叠的区间。
     */

    //模板方法，算出这些区间中最多有几个互不相交的区间。
    public int intervalSchedule(int[][] intvs) {
        if (intvs.length == 0) return 0;
        // 按 end 升序排序
        Arrays.sort(intvs, Comparator.comparingInt(a -> a[1]));
        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x
        //由于我们事先排了序，不难发现所有与 x 相交的区间必然会与 x 的 end 相交；
        //如果一个区间不想与 x 的 end 相交，它的 start 必须要大于（或等于）x 的 end

        int x_end = intvs[0][1];
        for (int[] interval : intvs) {
            int start = interval[0];
            if (start >= x_end) {
                // 找到下一个选择的区间了
                count++;
                x_end = interval[1];
            }
        }
        return count;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        //贪心策略，先算出这些区间中最多有几个互不相交的区间。，然后用区间总个数减去不重叠区间的个数。
        int n = intervals.length;
        return n - intervalSchedule2(intervals);
    }

    /*
    拿封装类改了下
     */
    public int intervalSchedule2(int[][] intvs) {
        if (intvs.length == 0) return 0;

        List<Interval> intervalList = new ArrayList<>();
        for (int[] interval : intvs) {
            intervalList.add(new Interval(interval[0], interval[1], Interval.COMPARETYPE.END));
        }

        // 按 end 升序排序
        Collections.sort(intervalList);
        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x
        int x_end = intervalList.get(0).end;
        for (Interval interval : intervalList) {
            int start = interval.start;
            if (start >= x_end) {
                // 找到下一个选择的区间了
                count++;
                x_end = interval.end;
            }
        }
        return count;
    }
    @Test
    public void test() {
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {1, 3}
        }));
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {1, 2}, {1, 2}
        }));
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 2}, {2, 3}
        }));
        System.out.println(eraseOverlapIntervals(new int[][]{
                {1, 100}, {11, 22}, {1, 11}, {2, 12}
        }));
    }
}
