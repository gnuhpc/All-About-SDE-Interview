package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
二维计算距离的BFS
1. 计算每个建筑物到每个空地的最短距离，同时统计每个空地能够到达的建筑的个数；
2. 取最短的距离且能够全部到达所有的建筑物。
 */

public class ShortestDistance317 {
    int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        //记录某个0点到所有1的距离之和
        int[][] distances = new int[m][n];
        //记录某个0点可以到达1的个数
        int[][] counts = new int[m][n];
        //记录所有1的个数
        int cnt = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { //每次等于1 的时候就可以BFS遍历更新到0 的距离数据d
                    cnt++;
                    boolean[][] visited = new boolean[m][n];
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] v = queue.poll();
                        int x = v[0];
                        int y = v[1];
                        int dist = v[2];
                        distances[x][y] += dist; //更新d
                        for (int[] dir : dirs) {
                            int newX = x + dir[0];
                            int newY = y + dir[1];
                            if (newX < 0 || newY < 0 || newX >= m || newY >= n || visited[newX][newY] ||
                                grid[newX][newY] != 0) {
                                continue;
                            }
                            queue.add(new int[]{newX, newY, dist + 1});
                            visited[newX][newY] = true;
                            counts[newX][newY]++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (counts[i][j] == cnt) {
                    res = Math.min(res, distances[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    @Test
    public void test() {
        System.out.println(shortestDistance(new int[][]{
                {1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}
        }));
    }
}
