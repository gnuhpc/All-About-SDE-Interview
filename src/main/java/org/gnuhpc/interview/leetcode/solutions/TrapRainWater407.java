package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;

/**
 * Copyright gnuhpc 2020/9/3
 */
public class TrapRainWater407 {
    public int trapRainWater(int[][] heightMap) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        int m = heightMap.length;
        int n = heightMap[0].length;

        boolean[][] flag = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    minHeap.offer(new int[]{heightMap[i][j], i, j});
                    flag[i][j] = true;
                }
            }
        }
        int ans = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!minHeap.isEmpty()) {
            int[] temp = minHeap.poll();
            for (int i = 0; i < 4; i++) {
                int newX = dirs[i][0] + temp[1];
                int newY = dirs[i][1] + temp[2];
                if (newX >= 0 && newX <= m - 1 && newY >= 0 && newY <= n - 1 && !flag[newX][newY]) {
                    if (heightMap[newX][newY] < temp[0]) {
                        ans += temp[0] - heightMap[newX][newY];
                    }
                    flag[newX][newY] = true;
                    //将更高的点入堆
                    minHeap.offer(new int[]{Math.max(heightMap[newX][newY], temp[0]), newX, newY});
                }
            }
        }
        return ans;
    }


    //链接：https://leetcode-cn.com/problems/trapping-rain-water-ii/solution/bfs-cong-wai-xiang-nei-kuo-zhan-by-xiao-x1mlf/

}
