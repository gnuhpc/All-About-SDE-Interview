package org.gnuhpc.bigdata.leetcode.solutions;

public class FirstBadVersion278 {
    private boolean isBadVersion(int v) {
        return true;
    }

    public int firstBadVersion(int n) {
        if (n <= 0) return -1;

        int start = 1, end = n;

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;

            boolean isBad = isBadVersion(mid);

            if (isBad) {

                end = mid;

            }
            else start = mid;
        }

        if (isBadVersion(start)) return start;
        return end;
    }
}
