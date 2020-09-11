package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/9/10
 */
public class KClosest973 {
    /*
        time: O(NLogN)。
        space: O(k)。
    */
    public int[][] kClosest(int[][] points, int K) {
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (cal(b) - cal(a)));
        int[][] result = new int[K][2];

        for (int i = 0; i < points.length; i++) {
            if (i < K) {
                maxHeap.offer(points[i]);
            } else {
                if (cal(maxHeap.peek()) > cal(points[i])) {
                    maxHeap.poll();
                    maxHeap.offer(points[i]);
                }
            }
        }
        for (int i = 0; i < K; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    public int cal(int[] a) {
        return (a[0] * a[0]) + (a[1] * a[1]);
    }
}
