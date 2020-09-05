package org.gnuhpc.interview.leetcode.solutions;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/9/3
 */
public class TrapRainWater107 {
    class Node {
        int x, y;
        int height;

        public Node(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }
        int n = heights.length;
        int m = heights[0].length;
        int ans = 0;
        boolean[][] visit = new boolean[n][m];
        //方向数组
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.height - o2.height);
        //初始化堆
        for (int i = 0; i < n; i++) {
            pq.offer(new Node(i, 0, heights[i][0]));
            pq.offer(new Node(i, m - 1, heights[i][m - 1]));
            visit[i][0] = true;
            visit[i][m - 1] = true;
        }
        for (int j = 1; j < m - 1; j++) {
            pq.offer(new Node(0, j, heights[0][j]));
            pq.offer(new Node(n - 1, j, heights[n - 1][j]));
            visit[0][j] = true;
            visit[n - 1][j] = true;
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (int i = 0; i < 4; i++) {
                int next_x = node.x + dx[i];
                int next_y = node.y + dy[i];
                if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < m && visit[next_x][next_y] == false) {
                    visit[next_x][next_y] = true;
                    //将周围节点放进堆中，如果周围节点height值小于当前节点，则将当前节点放进该位置节点，否则，放进周围的值
                    pq.offer(new Node(next_x, next_y, Math.max(node.height, heights[next_x][next_y])));
                    //计算接雨水量，如果周围值小于当前值，说明可以接雨水；否则不能接雨水
                    ans += Math.max(0, node.height - heights[next_x][next_y]);
                }
            }
        }
        return ans;

    }
}
