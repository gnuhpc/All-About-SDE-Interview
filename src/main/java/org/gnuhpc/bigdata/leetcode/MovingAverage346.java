package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2019/10/5
 */
public class MovingAverage346 {
    /**
     * Initialize your data structure here.
     */
    private Integer[] buffer;
    private int       cur;
    private int       sum;
    private boolean   isFull;

    public MovingAverage346(int size) {
        buffer = new Integer[size];
        Arrays.fill(buffer, null);
        cur = 0;
        sum = 0;
        isFull = false;
    }

    public double next(int val) {

        //Save
        int preVal = 0;
        if (!isFull) {
            preVal = 0;
        }
        else {
            preVal = buffer[cur];
        }


        sum = sum - preVal + val;
        buffer[cur] = val;

        //Calculate Average
        if (!isFull) {
            if (cur == buffer.length - 1) isFull = true;
        }
        cur = (cur + 1) % buffer.length;

        if (isFull) {
            return (double) sum / (double) buffer.length;
        }
        else {
            return (double) sum / (double) cur;
        }
    }

    public static void main(String[] args) {

        MovingAverage346 average = new MovingAverage346(3);
        System.out.println(average.next(1));
        System.out.println(average.next(10));
        System.out.println(average.next(3));
        System.out.println(average.next(5));
    }
}
