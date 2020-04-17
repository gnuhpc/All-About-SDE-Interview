package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/3/30
 */

public class MaxDistance1162 {
    /*
    一开始，我们找出所有陆地格子，将它们放入队列，作为第 0 层的结点。
然后进行 BFS 遍历，每个结点的相邻结点可能是上、下、左、右四个方向的结点，注意判断结点位于网格边界的特殊情况。
当遍历结束时，当前的遍历层数就是海洋格子到陆地格子的最远距离。
     */

    private int N;
    private int dr[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int maxDistance(int[][] grid) {
        N = grid.length;

        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];//记录已经计算过的位置
        // 将所有的陆地格子加入队列
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        // 如果我们的地图上只有陆地或者海洋，请返回 -1。
        if (queue.isEmpty() || queue.size() == N * N) {
            return -1;
        }

        int distance = -1;
        while (!queue.isEmpty()) {
            distance++;
            int size = queue.size();
            // 这里一口气取出 n 个结点，以实现层序遍历
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];
                for (int[] d : dr) {
                    int newX = x + d[0];
                    int newY = y + d[1];
                    if (isValid(newX, newY) && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
        }

        return distance;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
