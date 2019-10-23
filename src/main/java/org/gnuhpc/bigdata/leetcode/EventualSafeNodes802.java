package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventualSafeNodes802 {
    private Set<Integer> visiting = new HashSet<>();
    private Set<Integer> visited = new HashSet<>();
    private List<Integer> res = new ArrayList<>();
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        for (int v = 0; v < n; v++) {
            if (dfs(v, graph)) {
                res.add(v);
            }
        }
        return res;
    }

    private boolean dfs(int node, int[][] graph) {
        if (visited.contains(node)) {
            return true;
        }
        if (visiting.contains(node)) {
            return false;
        }

        visiting.add(node);
        for (int neighbor : graph[node]) {
            if (visiting.contains(neighbor) || !dfs(neighbor, graph)) {
                return false;
            }
            if (visited.contains(node)) {
                continue;
            }
        }

        visiting.remove(node);
        visited.add(node);
        return true;
    }





    @Test
    public void test() {
        System.out.println(eventualSafeNodes(new int[][]{
                {1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}
        }));
    }
}
