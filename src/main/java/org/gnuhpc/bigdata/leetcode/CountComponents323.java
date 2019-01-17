package org.gnuhpc.bigdata.leetcode;

/*

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CountComponents323 {
    /*
    Method1: Union-find
This problem can be solved by using union-find beautifully. Initially, there are n nodes. The nodes that are involved in each edge is merged.
There are k loops and each loop processing the root array costs log(n). Therefore, time complexity is O(k*log(n)).
     */
    public int countComponents(int n, int[][] edges) {
        int count = n;

        int[] root = new int[n];
        // initialize each node is an island
        for(int i=0; i<n; i++){
            root[i]=i;
        }

        // 构建并差集
        for(int i=0; i<edges.length; i++){
            int x = edges[i][0];
            int y = edges[i][1];

            int xRoot = root(root, x);
            int yRoot = root(root, y);

            //合并，表示相连
            if(xRoot!=yRoot){
                root[xRoot]=yRoot;
                count--;
            }

        }

        return count;
    }

    public int root(int[] arr, int id){
        while(arr[id]!=id){
            arr[id]= arr[arr[id]];
            id=arr[id];
        }
        return id;
    }

    /*
    Method 2: dfs
     */

    public int countComponents2(int n, int[][] edges) {
        //转化为邻接矩阵
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i<n; i++) adjList.add(new ArrayList<>());
        for (int[] edge : edges){
            int from = edge[0];
            int to = edge[1];

            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        //dfs
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new LinkedList<>();
        int count=0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                count++;
                stack.push(i);

                while (!stack.isEmpty()){
                    int current = stack.pop();
                    visited[current] = true;

                    for (int j: adjList.get(current)){
                        if (!visited[j]) stack.push(j);
                    }
                }
            }
        }

        return count;
    }

    @Test
    public void test(){
        System.out.println(
                countComponents(5,
                new int[][]{{0, 1}, {1, 2}, {3, 4}}
                )
        );

        System.out.println(
                countComponents2(5,
                new int[][]{{0, 1}, {1, 2}, {3, 4}}
                )
        );

        System.out.println(
                countComponents(5,
                        new int[][]{{0, 1}, {1, 2}, {3, 4}, {2, 3}}
                )
        );

        System.out.println(
                countComponents2(5,
                        new int[][]{{0, 1}, {1, 2}, {3, 4}, {2, 3}}
                )
        );
    }

}
