package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.Interval;
import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.TreeMap;

/**
 * Copyright gnuhpc 19-8-4
 */

// 利用treemap进行, 能够在O(logN)而不是线性的去找比他大或者比他小的key的位置，特别合适用在kv且k有序的情况
public class SummaryRanges2352 {
    TreeMap<Integer, Interval> tree;

    public SummaryRanges2352() {
        tree = new TreeMap<>();
    }

    public void addNum(int val) {
        if (tree.containsKey(val)) return;
        Integer l = tree.lowerKey(val);
        Integer h = tree.higherKey(val);
        //一共四种情况，其中一种是直接以孤立点加进去
        // 其余三种，举个例子：add 7 to (5, 6), (8, 9); add 6 to (5, 5); add 4 to (5, 5):

        //合并低的，删除高的
        if (l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) {
            tree.get(l).end = tree.get(h).end;
            tree.remove(h);
            //更新低的
        }
        else if (l != null && tree.get(l).end + 1 >= val) {
            tree.get(l).end = Math.max(tree.get(l).end, val);
            //更新高的
        }
        else if (h != null && h == val + 1) {
            tree.put(val, new Interval(val, tree.get(h).end));
            tree.remove(h);
        }
        else { //不包含，直接添加孤立点
            tree.put(val, new Interval(val, val));
        }
    }

    public void addNum2(int val) {
        int key = val;
        Interval interval = new Interval(val, val);

        //看能不能和前边的一个合并， floorkey找挨着的前边的一个
        Integer prevKey = tree.floorKey(val);
        if (prevKey != null && tree.get(prevKey).end + 1 >= val) {
            int start = Math.min(tree.get(prevKey).start, val);
            key = start;
            interval.start = start;
            interval.end = Math.max(tree.get(prevKey).end, val);
        }

        //看能不能和后边的一个合并, ceilingkey找挨着的后边的那个key
        Integer nextKey = tree.ceilingKey(val);
        if (nextKey != null && nextKey - 1 == val) {
            interval.end = tree.get(nextKey).end;
            tree.remove(nextKey);
        }

        tree.put(key, interval);
    }


    public int[][] getIntervals() {
        int[][] res = new int[tree.size()][2];

        int i = 0;
        for (Interval v : tree.values()) {
            int[] temp = new int[2];
            temp[0] = v.start;
            temp[1] = v.end;
            res[i] = temp;
            i++;
        }

        return res;
    }


    @Test
    public void test() {
        SummaryRanges2352 summaryRanges = new SummaryRanges2352();
        addNum2(1);
        addNum2(3);
        addNum2(7);
        addNum2(2);
        addNum2(6);

        Utils.print2DArray(getIntervals());
    }


}
