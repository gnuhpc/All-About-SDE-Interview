package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 2021/2/6
 */
public class ConnectSticks1167 {
    public int connectSticks(int[] sticks) {
        if (sticks.length == 1) return 0;
        Queue<Integer> queue = new PriorityQueue<>();
        int sum = 0;

        for (int i = 0; i < sticks.length; i++) {
            queue.add(sticks[i]);
        }
        while (queue.size() > 1) {
            int a1 = queue.poll();
            int a2 = queue.poll();
            sum += a1 + a2;
            queue.add(a1 + a2);
        }
        return sum;
    }
}
