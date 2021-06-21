package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/3/10
 */
public class ShipWithinDays1011 {
    public int shipWithinDays(int[] weights, int D) {
        int left = 1;
        int right = 500000;
        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, weights, D)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    //check 方法得好好想想,这个才是难点
    public boolean check(int x, int[] weights, int D) {
        int cnt = 0, remainder = x;
        for (int weight : weights) {
            if (weight > x) return false; // 如果包裹重量大于运载力就返回false
            if (weight <= remainder) { // 重量小于还能装载的能力就需要装载
                remainder -= weight;
            } else { // 包裹太重就放到下一次装载
                cnt++;
                remainder = x - weight;
            }
        }
        cnt++; // 需要计算最后一次装载
        return cnt <= D;
    }


}
