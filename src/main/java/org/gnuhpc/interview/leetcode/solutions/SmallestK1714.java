package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 2021/1/12
 */
public class SmallestK1714 {
    public int[] smallestK(int[] arr, int k) {

        if (k == 0) return new int[0];
        // 建立一个大根堆
        Queue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        for (int num : arr) {
            // 大根堆size() < k，将数字放入。
            if (priorityQueue.size() < k) {
                priorityQueue.offer(num);
            } else if (num < priorityQueue.peek()) {
                // 与大根堆堆顶值比较，去除堆顶，将数字放入
                priorityQueue.poll();
                priorityQueue.offer(num);
            }
        }
        // 返回堆中的元素
        int[] result = new int[priorityQueue.size()];
        int i = 0;
        for (int num : priorityQueue) {
            result[i++] = num;
        }
        return result;
    }
}
