package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class LongestMountain845 {
    public int longestMountain(int[] A) {
        int res = 0;
        for (int i = 1; i < A.length - 1; i++) {//假设i所在为mountain point
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                int m = i - 1;
                int n = i + 1;

                //注意边界
                while (m > 0 && A[m - 1] < A[m]) m--; //上升
                while (n < A.length - 1 && A[n] > A[n + 1]) n++; //下降

                res = Math.max(res, n - m + 1);
            }
        }

        return res;
    }

    //这道题的follow up是如果有平原，并且算山峰 => 这个注意比较的时候把等号加上就行

    @Test
    public void test() {
        System.out.println(longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
    }
}
