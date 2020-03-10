package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class CanCompleteCircuit134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int n = gas.length;

        //情况1： 如果gas的总和就小于cost，那就洗洗睡
        for (int i = 0; i < n; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
        }

        if (sumCost > sumGas) return -1;

        //情况2： 如果gas的总和大于cost，那就遍历数组
        int start = -1;
        for (int i = 0; i < n; i++) {
            if (gas[i] >= cost[i]) {
                start = i;
                int j = 0;
                int left = 0;
                for (; j < n; j++) {
                    left += gas[(j + start) % n] - cost[(j + start) % n];
                    if (left < 0) {
                        break;
                    }
                }

                if (j == n) return start;
            }
        }

        return -1;
    }

    @Test
    public void test() {
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(canCompleteCircuit(new int[]{5, 1, 2, 3, 4}, new int[]{4, 4, 1, 5, 1}));
    }
}
