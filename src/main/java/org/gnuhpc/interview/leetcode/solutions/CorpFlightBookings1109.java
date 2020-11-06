package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.TreeMap;

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

    /*
    Method3: TreeMap + Prefix sum通解
     */
    public int[] corpFlightBookings3(int[][] bookings, int n) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] booking : bookings) {
            map.put(booking[0], map.getOrDefault(booking[0], 0) + booking[2]);
            map.put(booking[1] + 1, map.getOrDefault(booking[1] + 1, 0) - booking[2]);//+1是根据题意闭区间，则下一个点就降下来了
        }
        int prev = 0;
        int[] res = new int[n];
        for (int i = 1; i <= n; i++) {
            Integer value = map.getOrDefault(i, 0);
            res[i - 1] = prev + value;
            prev += value;
        }
        return res;
    }

    /*
    Method4: 方法3的化简版
     */
    public int[] corpFlightBookings4(int[][] bookings, int n) {
        int[] res = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] booking : bookings){
            map.put(booking[0], map.getOrDefault(booking[0], 0)+booking[2]);
            map.put(booking[1]+1, map.getOrDefault(booking[1]+1, 0)-booking[2]);
        }
        int prev = 0;
        for (int k : map.keySet()){
            map.put(k, map.get(k)+prev);
            prev = map.get(k);
        }
        for (int i=1;i<=n;i++){
            Integer booking = map.floorKey(i);
            if (booking!=null){
                res[i-1] = map.get(booking);
            }
        }
        return res;
    }

    @Test
    public void test() {
        Utils.printArray(corpFlightBookings3(new int[][]{
                {1, 2, 10}, {2, 3, 20}, {2, 5, 25}
        }, 5));
    }
}
