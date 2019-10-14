package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;
import scala.collection.parallel.AdaptiveWorkStealingForkJoinTasks;

import java.util.*;

public class IsBipartite785 {
    /*
    Method1: DFS
     */

    public boolean isBipartite(int[][] graph) {
        HashSet[] sets = new HashSet[2];
        for (int i = 0; i < sets.length; ++i) {
            sets[i] = new HashSet<>();
        }

        // This graph might be a disconnected graph. So check each unvisited node.
        for (int i = 0; i < graph.length; ++i) {
            //访问过的就直接过
            if(sets[0].contains(i)) continue;
            if(sets[1].contains(i)) continue;
            if (!dfs(graph, i, sets,  0)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int[][] graph, int node, HashSet[] sets, int setIdx) {
        if (sets[setIdx].contains(node)) return true;
        if (sets[1 - setIdx].contains(node)) return false;

        sets[setIdx].add(node);
        for (int n : graph[node]){
            if(!dfs(graph, n, sets, 1 - setIdx)){
                return false;
            }
        }

        return true;
    }

    /*
    Nethod2 : BFS
     */

    public boolean isBipartite2(int[][] graph) {
        HashSet[] sets = new HashSet[2];
        for (int i = 0; i < sets.length; ++i) {
            sets[i] = new HashSet<>();
        }

        Queue<Integer> queue =  new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if(sets[0].contains(i)) continue;
            if(sets[1].contains(i)) continue;
            if (!bfs(sets, graph,queue,i,0)){
                return false;
            }
        }

        return true;

    }

    private boolean bfs(HashSet[] sets, int[][] graph, Queue<Integer> queue, int node, int idx) {
        queue.offer(node);

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int n = queue.poll();
                sets[idx].add(n);
                for (int adjN: graph[n]){
                    if (sets[idx].contains(adjN)) return false;
                    if (sets[1-idx].contains(adjN)) continue;
                    else{
                        sets[1-idx].add(adjN);
                        queue.offer(adjN);
                    }
                }
            }

            idx = 1 - idx;//这一层搞完就要放置下一层
        }

        return true;
    }

    /*
    Method3: 染色法 DFS //TODO
     */

    int []color;

    public boolean isBipartite3(int[][] graph) {
        color = new int[graph.length];
        for (int i=0;i<graph.length;i++){
            if (color[i]==0){
                if (!dfs(i,1,graph)){
                    return false;
                }
            }
        }
        return true;

    }

    boolean dfs(int v,int c,int[][]graph){
        color[v] = c;
        for (int x:graph[v]){
            //如果当前点的相邻的点同色就返回false;
            if (color[x]==c){
                return  false;
            }

            //如果当前点未染色,就染成-c
            if (color[x]==0 && !dfs(x,-c,graph)){
                return false;
            }
        }

        return true;
    }

    /*
    Method4: BFS 染色法 //TODO
     */
    Queue<Integer> queue = new LinkedList<>();

    public boolean isBipartite4(int[][] graph) {
        color = new int[graph.length];
        for (int i=0;i<graph.length;i++){
            if (color[i]==0&&!bfs(i,graph)){
                return false;
            }
        }

        return true;
    }

    boolean bfs(int s,int[][] graph){
        color[s] = 1;
        queue.add(s);
        while (!queue.isEmpty()){
            int from = queue.poll();
            for (int x:graph[from]){
                //如果相邻的点没有上色就给这个点上色
                if (color[x]==0) {
                    queue.add(x);
                    color[x] = -color[from];
                }
                //如果相邻的点颜色相同就返回false
                if (color[x]==color[from]){
                    return false;
                }

            }

        }


        //如果所有的点染过色，且相邻点点颜色都不一样,返回true
        return true;
    }

    @Test
    public void test(){
        System.out.println(isBipartite2(new int[][]{
                //{1,3}, {0,2}, {1,3}, {0,2}
                {1},{0},{4},{4},{2,3}
        }));
    }

}
