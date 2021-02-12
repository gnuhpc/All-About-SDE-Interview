package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.unionfind.UnionFind;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Copyright gnuhpc 2021/1/30
 */
public class SwimInWater778 {
    /**
     * 将 grid 所有点相连的边按相连两顶点最大高度排序，在并查集中不断合并权值最小的边，直到起点和终点相连
     * (执行用时：23 ms, 在所有 Java 提交中击败了30.71%的用户)
     * (内存消耗：38.4 MB, 在所有 Java 提交中击败了60.67%的用户)
     *
     * @param grid 坐标方格
     * @return 从左上到右下时间
     */
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        // edgesSize 边数
        int edgesSize = 2 * N * N - N - N, edgeIndex = 0;
        Edge[] edges = new Edge[edgesSize];

        int size = N * N, minTime = 0;
        UnionFind unionFind = new UnionFind(size);

        // 初始化所有边权值
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int idx = unionFind.node(N, i, j);
                // 当前顶点右边是否有条边
                if (j != N - 1) {
                    edges[edgeIndex++] = new Edge(idx, idx + 1,
                            Math.max(grid[i][j + 1], grid[i][j]));
                }
                // 当前顶点下边是否有条边
                if (i != N - 1) {
                    edges[edgeIndex++] = new Edge(idx, idx + N,
                            Math.max(grid[i + 1][j], grid[i][j]));
                }
            }
        }

        // 按边权值从小到大排序
        Arrays.sort(edges, Comparator.comparingInt(o -> o.high));


        for (Edge edge : edges) {
            // 不断合并权值最小的边
            unionFind.union(edge.from, edge.to);
            // 更新所需最小时间
            minTime = Math.max(minTime, edge.high);
            // 起点和终点相连时退出
            if (unionFind.isConnected(0, size - 1)) {
                break;
            }
        }

        return minTime;
    }

    // 体力消耗边类
    class Edge {
        int from, to, high;

        public Edge(int from, int to, int high) {
            this.from = from;
            this.to = to;
            this.high = high;
        }
    }
}
