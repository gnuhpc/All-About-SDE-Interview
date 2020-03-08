package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class IntervalIntersection986 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> l = new ArrayList<>();

        //双指针移动
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            int[] ia = A[i];
            int[] ib = B[j];
            if (!(ia[1] < ib[0] || ib[1] < ia[0])) {//相交
                int left = Math.max(ib[0], ia[0]);
                int right = Math.min(ib[1], ia[1]);
                l.add(new int[]{left, right});
            }

            if (ib[1] < ia[1]) {
                j++;
            }
            else {
                i++;
            }
        }
        return l.toArray(new int[0][]);//这个操作很简洁
        /*
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        String[] tt = (String[]) list.toArray(new String[0]);
         */
    }

}
