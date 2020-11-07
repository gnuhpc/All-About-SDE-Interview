package org.gnuhpc.interview.leetcode.solutions;

public class FindNumbers1295 {
    public int findNumbers(int[] nums) {
        int res = 0;
        for(int n:nums){
            int bits = getBits(n);
            if(bits%2 == 0) res++;
        }

        return res;
    }

    private int getBits(int n){
        int res = 0;
        while(n!=0){
            n/=10;
            res++;
        }

        return res;
    }
}
