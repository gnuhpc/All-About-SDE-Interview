package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/3/10
 */
public class CorpFlightBookings1109 {
    /*
    Method1: Brute Force
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] b : bookings) {
            int start = b[0];
            int end = b[1];
            int count = b[2];
            for (int i = start; i <= end; i++) {
                res[i - 1] += count;
            }
        }

        return res;

    }

    /*
    Method2: Prefix Sum

    参看图:
    https://leetcode-cn.com/problems/corporate-flight-bookings/solution/qian-zhui-he-fa-python-java-shi-jian-fu-za-du-on-b/
     */

    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] b : bookings) {
            int start = b[0];
            int end = b[1];
            int count = b[2];
            res[start - 1] += count;
            if (end < n) {
                res[end] -= count;
            }
        }


        for (int i = 1; i < res.length; i++) {
            res[i] += res[i - 1];
        }

        return res;
    }
}
