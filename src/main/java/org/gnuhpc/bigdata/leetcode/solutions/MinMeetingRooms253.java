package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 2019/10/9
 */
public class MinMeetingRooms253 {
    /*

    举一个例子:[[9,10],[4,9],[4,17]]
    按照时间轴,[4,9] 这一场和[4,17]这一场先开,由于有交集所以一定是两个会议室,
    那么后边[9,10]这个会议需不需要再开会议室呢?不需要了,因为它和前边两个会议的其中一个会议室没有交集,因此可以复用,不冲突.

    思路是这样,那么如何用代码表达呢?
    就这meeting room的思路,既然按照时间轴开始时间去看过去,那么先按照开始时间从小到大排个序.
    然后判断有没有交集主要是看结束时间是不是够紧凑且不相交,
    那么以结束时间为一个pq,进行一个个判断,看谁和谁复用会议室. 该合并的合并
    将合并不了的和已经合并后的会议室起始时间对儿放在pq,那么这个pq size就是所求了.
     */

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));

        // Use a min heap to track the minimum end time of merged intervals
        Queue<int[]> heap = new PriorityQueue<>(intervals.length, (a, b) -> a[1] - b[1]);

        // start with the first meeting, put it to a meeting room
        heap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // get the meeting room that finishes earliest
            int[] meeting = heap.poll();

            if (intervals[i][0] >= meeting[1]) {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                meeting[1] = intervals[i][1];
            }
            else {
                // otherwise, this meeting needs a new room
                heap.offer(intervals[i]);
            }

            // don't forget to put the meeting room back
            heap.offer(meeting);
        }

        return heap.size();

    }
}
