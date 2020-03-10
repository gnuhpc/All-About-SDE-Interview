package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 19-8-3
 */
public class Insert57 {
    //Method 0: 56题方法，加进去即可
    class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public int[][] insert0(int[][] intervals, int[] newInterval) {
        List<Interval> intervalList = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalList.add(new Interval(intervals[i][0], intervals[i][1]));
        }

        intervalList.add(new Interval(newInterval[0], newInterval[1]));

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

    //Method 1: 暴力解法
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            //newInterval在interval的右边
            //插入的头大于原来的尾部，则原来的整体放入
            if (newInterval[0] > intervals[i][1]) {
                result.add(intervals[i]);
                //插入的尾小于原来的头，则插入的整体放入
            }
            //newInterval在interval的左边
            else if (newInterval[1] < intervals[i][0]) {
                result.add(newInterval);
                newInterval = intervals[i];//别忘了更新指针，使得newinterval一直指向要合并的空间
            } else { //有交集就合并,old->new
                if (newInterval[0] >= intervals[i][0]) {
                    newInterval[0] = intervals[i][0];
                }
                if (newInterval[1] <= intervals[i][1]) {
                    newInterval[1] = intervals[i][1];
                }
            }
        }
        result.add(newInterval);
        int[][] d = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            d[i] = result.get(i);
        }
        return d;
    }

    //Method 2 : 二分法
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        // 二分查找找到最后一个intervals[i][0]小于newInterval[0]的位置
        int index = searchLastEqualLessThan(intervals, newInterval[0]);
        ArrayList<int[]> list = new ArrayList<>();
        // newInterval插入到头部
        if (index < 0) {
            list.add(newInterval);
        } else {
            // 把前半部分加入list中
            for (int i = 0; i <= index; i++) {
                list.add(intervals[i]);
            }
            // 将newInterval插入
            int[] inter = list.get(list.size() - 1);
            if (inter[1] < newInterval[0]) {
                list.add(newInterval);
            } else {
                inter[1] = Math.max(inter[1], newInterval[1]);
            }
        }
        // 合并后半部分
        for (int i = index + 1; i < intervals.length; i++) {
            int[] inter = list.get(list.size() - 1);
            if (inter[1] < intervals[i][0]) {
                list.add(intervals[i]);
            } else {
                inter[1] = Math.max(intervals[i][1], inter[1]);
            }
        }
        // 转换结果返回
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }

    private int searchLastEqualLessThan(int[][] intervals, int num) {
        int start = 0, end = intervals.length - 1, mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (intervals[mid][0] > num) {
                end = mid;
            } else if (intervals[mid][0] < num) {
                start = mid;
            } else {
                start = mid;
            }
        }

        if (intervals[end][0] <= num) return end;
        if (intervals[start][0] <= num) return start;

        return -1;
    }


    @Test
    public void test() {
        int[][] intervals = new int[][]{
                {1, 3},
                {6, 9},
        };

        Utils.print2DArray(insert2(intervals, new int[]{2, 5}));
    }
}
