package org.gnuhpc.bigdata.leetcode;

import java.util.HashSet;
import java.util.Set;

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
            if (!dfsIsBipartite(graph, i, sets)) {
                return false;
            }
        }

        return true;
    }

    public boolean dfsIsBipartite(int[][] graph, int i, Set<Integer>[] sets) {
        return dfsIsBipartite(graph, i, sets, 0) || dfsIsBipartite(graph, i, sets, 1);
    }

    public boolean dfsIsBipartite(int[][] graph, int i, Set<Integer>[] sets, int k) {
        if (sets[1-k].contains(i)) {    // i is already in the other set
            return false;
        }

        //Already visited
        if (sets[k].contains(i)) {      // i is already in the target set
            return true;
        }

        //visit it
        sets[k].add(i);

        for (int j : graph[i]) {
            // add neighbors to the other set
            if (!dfsIsBipartite(graph, j, sets,1-k)) {
                sets[k].remove(sets[k].size() - 1); //记得回溯
                return false;
            }
        }

        return true;
    }
}
