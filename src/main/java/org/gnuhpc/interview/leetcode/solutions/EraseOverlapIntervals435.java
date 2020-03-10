package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Copyright gnuhpc 2019/9/21
 */
public class EraseOverlapIntervals435 {
    public int intervalSchedule(int[][] intvs) {
        if (intvs.length == 0) return 0;
        // 按 end 升序排序
        Arrays.sort(intvs, Comparator.comparingInt(a -> a[1]));
        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x
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
        int n = intervals.length;
        return n - intervalSchedule(intervals);
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
                {1, 100}, {11, 22}, {1, 11}, {2, 12}
        }));
    }
}
