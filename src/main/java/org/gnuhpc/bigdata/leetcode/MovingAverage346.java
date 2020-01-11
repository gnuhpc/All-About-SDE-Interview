package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 2019/10/5
 */
public class MovingAverage346 {
    double              sum;
    int                 size;
    LinkedList<Integer> list;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage346(int size) {
        this.list = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        sum += val;
        list.offer(val);

        if (list.size() <= size) {
            return sum / list.size();
        }

        sum -= list.poll();//presum的想法

        return sum / size;
    }


    public static void main(String[] args) {

        MovingAverage346 average = new MovingAverage346(3);
        System.out.println(average.next(1));
        System.out.println(average.next(10));
        System.out.println(average.next(3));
        System.out.println(average.next(5));
    }
}
