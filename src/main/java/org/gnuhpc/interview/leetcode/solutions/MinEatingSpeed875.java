package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

public class MinEatingSpeed875 {
    public int minEatingSpeed(int[] piles, int H) {
        Arrays.sort(piles);

        int start = piles[0];
        int end = piles[piles.length - 1];

        int minDiff = Integer.MAX_VALUE;

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            int h = calc(piles, mid);
            if (h > H) start = mid;
            else {
                int diff = H - h;
                if (diff < minDiff) {
                    minDiff = diff;
                }

                end = mid;
            }
        }
        int startH = calc(piles, start);
        int endH = calc(piles, end);

        if (startH > H) return end;
        else {
            if (startH > endH) return start;
            else return end;
        }
    }

    private int calc(int[] piles, int speed) {
        int res = 0;
        for (int pile : piles) {
            if (pile <= speed) res++;
            else res += pile / speed + 1;
        }

        return res;
    }

}
