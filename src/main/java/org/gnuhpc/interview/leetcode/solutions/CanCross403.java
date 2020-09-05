package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/5
 */
public class CanCross403 {
    public boolean canCross(int[] stones) {
        int len = stones.length;
        boolean[][] visited = new boolean[len][len];
        Queue<Pair> queue = new LinkedList<>();
        int[] directions = new int[]{-1, 0, 1};
        queue.offer(new Pair(0, stones[0]));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int d : directions) {
                int step = cur.second + d;
                int dest = stones[cur.first] + step;
                int ind = Arrays.binarySearch(stones, cur.first + 1, len, dest);

                if (ind >= 0 && ind > cur.first) {
                    if (!visited[cur.first][ind]) {
                        if (ind == len - 1) {
                            return true;
                        }
                        visited[cur.first][ind] = true;
                        queue.offer(new Pair(ind, step));
                    }
                }
            }
        }

        return false;
    }
}
