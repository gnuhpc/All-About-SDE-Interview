package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/3
 */
//From: https://www.youtube.com/watch?v=pUtxTz134qM
public class FindMinHeightTrees310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n == 1) {
            result.add(0);
            return result;
        }

        int[] degree = new int[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] pair : edges) {
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
            degree[pair[0]]++;
            degree[pair[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.add(i);
        }

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                list.add(cur);
                for (int m : map.get(cur)) {
                    degree[m]--;
                    if (degree[m] == 1) queue.add(m);
                }
                result = list;
            }
        }
        return result;
    }
}
