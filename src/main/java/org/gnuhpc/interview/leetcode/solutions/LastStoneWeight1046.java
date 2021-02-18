package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;

public class LastStoneWeight1046 {
    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            queue.offer(stone);
        }
        while (queue.size() > 1) {
            int first = queue.poll();
            int second = queue.poll();
            if (first == second) {
                continue;
            } else {
                queue.offer(first - second);
            }
        }
        return queue.isEmpty() ? 0 : queue.peek();
    }
}
