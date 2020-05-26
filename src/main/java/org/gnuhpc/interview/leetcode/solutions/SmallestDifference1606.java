package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/5/25
 */
public class SmallestDifference1606 {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long res = Long.MAX_VALUE;
        int len = b.length;
        for (int num_a : a) {
            int idx = Arrays.binarySearch(b, num_a);
            //能在b数组中找到值为num_a的数
            if (idx >= 0) return 0;
            int ip = -(idx + 1);
            if (ip == 0) {
                res = Math.min(res, Math.abs((long) b[0] - (long) num_a));
            } else if (ip > 0 && ip < len) { //如果插入点为0 说明
                long diff = Math.abs((long) num_a - (long) b[ip - 1]);
                res = Math.min(res, diff);

                diff = Math.abs((long) b[ip] - (long) num_a);
                res = Math.min(res, diff);
            } else { //ip = idx;
                res = Math.min(res, Math.abs((long) b[len - 1] - (long) num_a));
            }
        }
        return (int) res;
    }


    public int smallestDifference2(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0, j = 0;
        long res = Long.MAX_VALUE;
        //亦步亦趋，别距离太远
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                res = Math.min(res, (long) b[j] - (long) a[i]);
                i++;
            } else {
                res = Math.min(res, (long) a[i] - (long) b[j]);
                j++;
            }
        }
        return (int) res;
    }

}
