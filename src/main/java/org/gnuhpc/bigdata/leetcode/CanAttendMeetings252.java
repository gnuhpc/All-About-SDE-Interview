package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Copyright gnuhpc 2019/10/9
 */
public class CanAttendMeetings252 {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) return false;
        }
        return true;

    }
}
