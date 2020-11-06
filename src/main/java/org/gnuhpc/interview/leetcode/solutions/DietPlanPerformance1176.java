package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/29
 */
public class DietPlanPerformance1176 {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int l = 0, r = 0;
        int res = 0;
        int tmp = 0;
        for (; r < k; r++) {
            tmp += calories[r];
        }

        while (r < calories.length) {
            if (tmp > upper) res += 1;
            if (tmp < lower) res -= 1;
            tmp -= calories[l++];
            tmp += calories[r++];
        }

        if (tmp > upper) res += 1;
        if (tmp < lower) res -= 1;


        return res;
    }
}
