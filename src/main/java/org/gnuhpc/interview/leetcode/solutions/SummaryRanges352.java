package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 19-8-4
 */
public class SummaryRanges352 {
    private List<List<Integer>> intervals;

    public SummaryRanges352() {
        intervals = new LinkedList<>();
    }

    public void addNum(int val) {
        int i = 0;

        while (i < intervals.size()) {
            List<Integer> interval = intervals.get(i);
            int start = interval.get(0);
            int end = interval.get(1);

            if (val < start - 1) {
                List<Integer> l = new LinkedList<>();
                l.add(val);
                l.add(val);
                intervals.add(i, l);
                break;
            }

            if (val == start - 1) {
                interval.set(0, val);
                break;
            }

            if (val >= start && val <= end) {
                break;
            }

            if (val == end + 1) {
                interval.set(1, val);
                break;
            }

            if (val > end + 1) {
                i++;
            }
        }

        if (i == intervals.size()) {
            List<Integer> l = new LinkedList<>();
            l.add(val);
            l.add(val);
            intervals.add(l);
        }
    }

    public int[][] getIntervals() {
        return merge(intervals);
    }

    private int[][] merge(List<List<Integer>> intervals) {
        List<List<Integer>> merged = new LinkedList<>();

        for (int i = 0; i < intervals.size(); i++) {
            List<Integer> interval = intervals.get(i);
            if (merged.isEmpty()) {
                merged.add(interval);
                continue;
            }
            List<Integer> mergedLast = merged.get(merged.size() - 1);
            if (mergedLast.get(1) + 1 < interval.get(0))
                merged.add(interval);
            else if (mergedLast.get(1) + 1 == interval.get(0)) mergedLast.set(1, interval.get(1));
            else mergedLast.set(1, Math.max(mergedLast.get(1), interval.get(1)));
        }

        int[][] res = new int[merged.size()][2];

        for (int i = 0; i < merged.size(); i++) {
            res[i][0] = merged.get(i).get(0);
            res[i][1] = merged.get(i).get(1);
        }
        return res;
    }


    @Test
    public void test() {
        SummaryRanges352 summaryRanges = new SummaryRanges352();
        addNum(1);
        addNum(3);
        addNum(7);
        addNum(2);
        addNum(6);

        Utils.print2DArray(getIntervals());
    }
}
