package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

//TODO 二维计算距离的BFS
public class ShortestDistance317 {
    int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        //记录某个点到所有1的距离之和
        int[][] d = new int[m][n];
        //记录某个点可以到达1的个数
        int[][] count = new int[m][n];
        //记录所有1的个数
        int cnt = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { //每次等于1 的时候就可以BFS遍历更新到0 的距离数据d
                    cnt++;
                    boolean[][] visited = new boolean[m][n];
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[] {i, j, 0});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] v = queue.poll();
                        int x = v[0];
                        int y = v[1];
                        int dist = v[2];
                        d[x][y] += dist; //更新d
                        for (int[] dir : dirs) {
                            int x1 = x + dir[0];
                            int y1 = y + dir[1];
                            if (x1 < 0 || y1 < 0 || x1 >= m || y1 >= n || visited[x1][y1] || grid[x1][y1] != 0) {
                                continue;
                            }
                            queue.add(new int[] {x1, y1, dist + 1});
                            visited[x1][y1] = true;
                            count[x1][y1]++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (count[i][j] == cnt) {
                    res = Math.min(res, d[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    @Test
    public void test(){
        System.out.println(shortestDistance(new int[][]{
                {1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}
        }));
    }
}
