package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.datastructure.unionfind.QuickUnion;
import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/4
 */

/*
To tell whether a graph is a valid tree, we have to:

1. Make sure there is no cycle in the graph - this has to be a none-cyclic graph;
https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
https://www.geeksforgeeks.org/detect-cycle-in-an-undirected-graph-using-bfs/
https://www.techiedelight.com/union-find-algorithm-cycle-detection-graph/

2. Make sure every node is reached - this has to be a connected graph;
https://www.geeksforgeeks.org/connectivity-in-a-directed-graph/
https://www.sanfoundry.com/java-program-check-whether-undirected-graph-connected-using-bfs/
https://www.programcreek.com/2014/05/leetcode-number-of-connected-components-in-an-undirected-graph-java/

 */
public class ValidTree261 {
    /*
    判断一个无向图是否有环的三种方法
     */

    /*
    Method1 :并差集,如果合并的两个点在一个集合中，则这个图是有环的
     */
    public boolean validTree(int n, int[][] edges) {
        if (n <= 1) return true;
        if (edges.length == 0) return n == 0;
        //n为孤岛个数
        QuickUnion qu = new QuickUnion(n);

        for (int[] edge : edges) {
            if (qu.isConnected(edge[0], edge[1])) {//Already connected
                return false;
            }
            else {
                qu.union(edge[0], edge[1]);
                n--;
            }
        }

        return n == 1;
    }

    /*
    Method2 BFS
    根据边构建邻接矩阵，然后进行BFS，在遍历过程中将访问过的节点涂黑，并记录访问过的节点。
    如果有节点已经被访问过，则表示有环，
    如果遍历完成后还有节点没有被访问到，说明图被分为了多个不连通的部分,一定不是树.

     */
    public boolean validTree2(int n, int[][] edges) {
        //构建邻接矩阵
        int[][] graph = new int[n][n];
        //有边的元素设置为1，没有边的元素设置为0
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }
        //进行BFS
        Queue<Integer> queue = new LinkedList<>();
        //从第一个节点开始搜索，这样就不会漏掉无边图的情况
        queue.add(0);
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            visited[cur] = true;
            //获取邻接点
            for (int i = 0; i < n; i++) {
                //查看当前节点的邻接点
                if (graph[cur][i] == 1) {
                    //如果访问过，则返回false
                    if (visited[i])
                        return false;

                    //标记邻接点，入队列
                    visited[i] = true;
                    //涂黑访问过的节点
                    graph[cur][i] = 0;
                    graph[i][cur] = 0;
                    queue.add(i);
                }
            }
        }

        //判断是否为单连通分量
        for (boolean b : visited)
            if (!b) return false;

        return true;
    }


    /*
    Method3: DFS
     */

    public boolean validTree3(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        if (!dfs(0, -1, visited, graph)) return false;

        for (boolean b : visited)
            if (!b) return false;
        return true;
    }

    // p is the pointer to 'parent' node, we ignore it to avoid infinite loop
    private boolean dfs(int v, int p, boolean[] visited, List<Integer>[] adj) {
        visited[v] = true;
        for (int i : adj[v]) {
            if (i == p) continue;
            if (visited[i] || !dfs(i, v, visited, adj)) return false;
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(validTree(5, new int[][]{
                {0, 1}, {0, 2}, {0, 3}, {1, 4}
        }));

        System.out.println(validTree(5, new int[][]{
                {0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}
        }));
    }
}
