package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/2/6
 */
public class MinTime1443 {
    private List<List<Integer>> g;

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        g = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; ++i) {
            g.get(edges[i][0]).add(edges[i][1]);
            g.get(edges[i][1]).add(edges[i][0]);
        }

        boolean[] v = new boolean[n];
        return dfs(0, hasApple, v);

    }

    public int dfs(int node, List<Boolean> hasApple, boolean[] seen) {
        seen[node] = true;
        int res = 0;
        for (int child : g.get(node)) {
            if (!seen[child]) {
                int sub = dfs(child, hasApple, seen);
                if (sub > 0 || hasApple.get(child)) { // core code
                    res += 2 + sub;
                }
            }
        }

        return res;
    }
}
