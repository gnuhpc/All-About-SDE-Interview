package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class EventualSafeNodes802 {
    //0 - unvisited, -1 : beingVisited, 1: visited
    //利用dfs判断是否有环 先设置为-1，然后利用回溯的方法进行判断
    public List<Integer> eventualSafeNodes(int[][] g) {
        List<Integer> res = new ArrayList<>();
        int n = g.length;

        int[] visited = new int[n];

        for (int i = 0; i < n; i++) {
            if (!isNoCycle(g, visited, i)) { //节点在环上的就跳过，因为题目中要求的节点是不在环上的。
                continue;
            }
            else {
                res.add(i);
            }

        }
        return res;
    }

    public boolean isNoCycle(int[][] g, int[] visited, int vertex) {
        visited[vertex] = -1;
        for (int v : g[vertex]) {
            if (visited[v] == -1) {
                return false;
            }
            if (visited[v] == 1) {
                continue;
            }
            if (!isNoCycle(g, visited, v)) {
                return false;
            }
        }
        visited[vertex] = 1;
        return true;
    }

    /*
    Topological Sort
    Using degree array to record the out-degree, neighbors map to record In-neighbors(for example 0->1, 2->1, map(1) = [0, 2]).
    Add the node whose out-degree is 0 into queue and result Set, then process each node in the queue, if the out-degree of one node becomes 0, add it to queue until queue is empty.
     */

    public List<Integer> eventualSafeNodes2(int[][] graph) {
        int N = graph.length;
        int[] outDegree = new int[N];
        Map<Integer, Set<Integer>> neighbors = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            for (int neighbor : graph[i]) {
                if (!neighbors.containsKey(neighbor)) neighbors.put(neighbor, new HashSet<>());
                neighbors.get(neighbor).add(i);
                outDegree[i]++;
            }
        }

        Set<Integer> res = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (outDegree[i] == 0) {
                res.add(i);
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            res.add(v);
            if (neighbors.containsKey(v)) {
                for (int neighbor : neighbors.get(v)) {
                    outDegree[neighbor]--;
                    if (outDegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        List<Integer> list = new ArrayList<>(res);
        Collections.sort(list);
        return list;
    }

    @Test
    public void test() {
        System.out.println(eventualSafeNodes(new int[][]{
                {1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}
        }));
    }
}
