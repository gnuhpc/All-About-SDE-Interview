package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.Point;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Copyright gnuhpc 2020/2/11
 */
public class FindMaximizedCapital502 {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        int size = Profits.length;
        int ans = W;
        Point projects[] = new Point[size];
        for (int i = 0; i < projects.length; i++) {
            projects[i] = new Point(Capital[i], Profits[i]);
        }
        Arrays.sort(projects, (a, b) -> {
            if (a.x == b.x)
                return a.y - b.y;
            return a.x - b.x;
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int j = 0;
        for (int i = 0; i < Math.min(k, size); i++) {
            while (j < size && projects[j].x <= ans) {
                pq.add(projects[j].y);
                j++;
            }
            if (!pq.isEmpty())
                ans += pq.poll();
        }
        return ans;
    }
}
