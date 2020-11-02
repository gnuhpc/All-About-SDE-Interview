package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2020/10/29
 */
public class KillProcess582 {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 对于每一个进程
        for (int i = 0; i < pid.size(); i++) {
            // 找到他的父进程
            int parent = ppid.get(i);
            // 创建邻接表
            // <parentId, list of childIDs>
            map.putIfAbsent(parent, new ArrayList<>());
            map.get(parent).add(pid.get(i));
        }

        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(cur);
            queue.addAll(map.getOrDefault(cur, new ArrayList<>()));
        }
        return res;
    }

    public List<Integer> killProcess2(List<Integer> pid, List<Integer> ppid, int kill) {
        // 建立邻接表
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            int parent = ppid.get(i);
            map.putIfAbsent(parent, new ArrayList<>());
            map.get(parent).add(pid.get(i));
        }

        List<Integer> res = new ArrayList<>();

        helper(map, res, kill);
        return res;
    }

    private void helper(Map<Integer, List<Integer>> map, List<Integer> res, int kill) {
        res.add(kill);
        if (map.containsKey(kill)) {
            for (int p : map.get(kill)) {
                helper(map, res, p);
            }
        }
    }
}
