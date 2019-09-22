package org.gnuhpc.bigdata.leetcode;

public class IsPowerOfFour342 {
    public boolean isPowerOfFour(int num) {
        if (num<=0) return false;

        int left = 1, right = num/8;

        while (left+1<right){
            int mid = (right-left)/2 + left;
            double temp = Math.pow(4,mid);

            if (temp == num) return true;
            else if (temp>num) right = mid;
            else left = mid;
        }

        if (Math.pow(4,left) == num) return true;
        if (Math.pow(4,right) == num) return true;

        return false;
    }
}
