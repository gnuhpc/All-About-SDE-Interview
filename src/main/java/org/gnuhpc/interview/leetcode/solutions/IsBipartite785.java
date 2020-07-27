package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;
import scala.collection.parallel.AdaptiveWorkStealingForkJoinTasks;

import java.util.*;

public class IsBipartite785 {

    /*
        Method1: DFS
       先找到一个没被染色的节点u，把它染上一种颜色，之后遍历所有与它相连的节点v，
       如果节点v已被染色并且颜色和节点u一样，那么就不是二分图。如果这个节点v没有被染色，先把它染成与节点u不同颜色的颜色，
       然后遍历所有与节点v相连的节点...如此递归下去。

         */
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) return false;
        int v = graph.length;
        int[] colors = new int[v];  // 0未被染色， 1黑  2白
        // 要考虑非连通图, 所以要遍历每一个结点
        for (int i = 0; i < v; i++) {
            // lastColor为0
            if (!dfs(graph, i, colors, 0)) return false;
        }
        return true;
    }
    private boolean dfs(int[][] graph, int node, int[] colors, int lastColor) {
        // 注意，被染色的就不要继续染色了（因为这是自底向上的，被染色的点，其相连的节点肯定被染色了）
        // 如果继续对被染色的节点染色，就会导致死循环
        if (colors[node] != 0) return colors[node] != lastColor;
        // 未被染色，染成与相邻结点不同的颜色（lastColor为0时，就染成1）
        colors[node] = lastColor == 1 ? 2 : 1;
        for (int nei: graph[node]) {
            if (!dfs(graph, nei, colors, colors[node])) return false;
        }
        return true;
    }



    /*
    Nethod2 : BFS
     */

    private boolean[] visited;
    public boolean isBipartite2(int[][] graph) {
        HashSet[] sets = new HashSet[2];
        visited = new boolean[graph.length];
        for (int i = 0; i < sets.length; ++i) {
            sets[i] = new HashSet<>();
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) continue;
            if (!bfs(sets, graph, queue, i, 0)) {
                return false;
            }
        }

        return true;

    }

    private boolean bfs(HashSet[] sets, int[][] graph, Queue<Integer> queue, int node, int idx) {
        queue.offer(node);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int n = queue.poll();
                visited[node] = true;
                sets[idx].add(n);
                for (int adjN : graph[n]) {
                    if (sets[idx].contains(adjN)) return false;
                    if (sets[1 - idx].contains(adjN)) continue;
                    else {
                        sets[1 - idx].add(adjN);
                        queue.offer(adjN);
                    }
                }
            }

            idx = 1 - idx;//这一层搞完就要放置下一层
        }

        return true;
    }


    /*
    Method3: BFS 染色法
     */
    Queue<Integer> queue = new LinkedList<>();
    int[] color;

    public boolean isBipartite3(int[][] graph) {
        color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0)
                if (!bfs(i, graph)) {//注意BFS由于没有递归因此不需要传入起始color
                    return false;
                }
        }

        return true;
    }

    boolean bfs(int s, int[][] graph) {
        color[s] = 1;
        queue.add(s);
        while (!queue.isEmpty()) {
            int from = queue.poll();
            for (int node : graph[from]) {
                //如果相邻的点没有上色就给这个点上另一个色
                if (color[node] == 0) {
                    queue.add(node);
                    color[node] = -color[from];
                }
                //如果相邻的点颜色相同就返回false
                else if (color[node] == color[from]) {
                    return false;
                } else {
                    continue;
                }

            }

        }


        //如果所有的点染过色，且相邻点点颜色都不一样,返回true
        return true;
    }

    @Test
    public void test() {
        System.out.println(isBipartite2(new int[][]{
                //{1,3}, {0,2}, {1,3}, {0,2}
                {1}, {0}, {4}, {4}, {2, 3}
        }));
    }

}
