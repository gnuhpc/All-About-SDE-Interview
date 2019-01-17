package org.gnuhpc.bigdata.leetcode;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-22 21:36
 * Version: 0.1
 */

/*
典型二分法，发现错误的版本然后往前找
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        if ( n<=0) return -1;

        int start = 1, end = n;

        while(start + 1 < end){
            int mid = (end-start)/2 + start;

            boolean isBad = isBadVersion(mid) ;

            if(isBad) {
                end = mid;
            }
            else start = mid;
        }

        if(isBadVersion(start)) return start;
        return end;
    }

    private boolean isBadVersion(int mid) {
        return true;
    }
}
