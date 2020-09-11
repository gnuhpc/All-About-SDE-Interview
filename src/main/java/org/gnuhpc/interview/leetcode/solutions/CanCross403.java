package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/9/5
 */
public class CanCross403 {
    public boolean canCross(int[] stones) {
        int len = stones.length;
        boolean[][] visited = new boolean[len][len];
        Queue<Pair<Integer>> queue = new LinkedList<>();
        int[] directions = new int[]{-1, 0, 1};
        queue.offer(new Pair(0, stones[0]));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Pair<Integer> cur = queue.poll();
            for (int d : directions) {
                int step = cur.y+ d;
                int dest = stones[cur.x] + step;
                int ind = Arrays.binarySearch(stones, cur.x+ 1, len, dest);

                if (ind >= 0 && ind > cur.x) {
                    if (!visited[cur.x][ind]) {
                        if (ind == len - 1) {
                            return true;
                        }
                        visited[cur.x][ind] = true;
                        queue.offer(new Pair(ind, step));
                    }
                }
            }
        }

        return false;
    }
}
