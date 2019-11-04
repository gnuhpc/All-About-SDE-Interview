package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class LenLongestFibSubseq873 {
    //https://segmentfault.com/a/1190000016917461
    public int lenLongestFibSubseq(int[] A) {
        int res = 0;
        int[][] map = new int[A.length][A.length];
        map[A.length - 2][A.length - 1] = 2;
        for (int j = A.length - 3; j >= 0; j--) {
            for (int i = j + 1; i < A.length; i++) {
                int three = A[j] + A[i];
                int index = findIndex(i, three, A);
                if (index != -1) {
                    map[j][i] = map[i][index] + 1;
                    res = Math.max(map[j][i], res);
                }
                else {
                    map[j][i] = 2;
                }
            }
        }
        return res < 3 ? 0 : res;
    }

    public int findIndex(int start, int target, int[] A) {
        int end = A.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] < target) {
                start = mid + 1;
            }
            else if (A[mid] > target) {
                end = mid - 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

}
