package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class GuessNumber374 {
    private final static int guessNum = 6;
    private int guess(int num){
        if (num == guessNum) return 0;
        else if (num < guessNum) return 1;
        else return -1;
    }

    public int guessNumber(int n) {
        int start = 1, end = n;
        while (start+1<end){
            int mid = (end-start)/2 + start;
            int result = guess(mid);
            if (result == 0) return mid;
            else if (result == 1) start = mid;
            else end = mid;
        }

        if (guess(start)==0) return start;
        else return end;
    }

    @Test
    public void test(){
        System.out.println(guessNumber(10));
    }
}
