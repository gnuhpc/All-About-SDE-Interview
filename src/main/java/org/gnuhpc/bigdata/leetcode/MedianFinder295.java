package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 19-8-5
 */
public class MedianFinder295 {
    Queue<Integer> low;
    Queue<Integer> high;

    public MedianFinder295() {
        low = new PriorityQueue<>(Comparator.reverseOrder());
        high = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if ((low.size()==0)&&(high.size()==0)) low.add(num);
        else if (low.size()>high.size()) {
            if (num < low.peek()) {
                high.add(low.poll());
                low.add(num);
            } else high.add(num);
        } else if (low.size()<high.size()) {
            if (num > high.peek()) {
                low.add(high.poll());
                high.add(num);
            } else low.add(num);
        } else{
            if (num>high.peek()){
                high.offer(num);
            } else {
                low.offer(num);
            }
        }
    }


    public double findMedian() {
        if (low.size() == 0 && high.size() == 0) return 0.0;

        if (low.size() == high.size()){
            return ((double)high.peek()+(double)low.peek())/2.0;
        }

        return (low.size()>high.size()? low.peek():high.peek());
    }

    @Test
    public void test(){
        addNum(-1);
        System.out.println(findMedian());
        addNum(-2);
        System.out.println(findMedian());
        addNum(-3);
        System.out.println(findMedian());
        addNum(-4);
        System.out.println(findMedian());
        addNum(-5);
        System.out.println(findMedian());
    }
}
