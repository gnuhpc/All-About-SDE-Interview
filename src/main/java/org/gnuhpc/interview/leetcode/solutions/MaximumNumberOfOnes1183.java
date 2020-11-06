package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;

/**
 * Copyright gnuhpc 2020/10/30
 */
public class MaximumNumberOfOnes1183 {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < sideLength; ++i)
            for (int j = 0; j < sideLength; ++j) {
                pq.add(((width - 1 - i) / sideLength + 1) * ((height - 1 - j) / sideLength + 1));
                if (pq.size() > maxOnes)
                    pq.poll();
            }
        while (!pq.isEmpty())
            ans += pq.poll();
        return ans;
    }
}
