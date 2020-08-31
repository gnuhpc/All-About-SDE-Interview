package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.unionfind.UnionFind;

import java.util.*;

public class CanVisitAllRooms841 {
    /*
    Method1: BFS
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int n = rooms.size();
        List<Integer> zeroRoom = rooms.get(0);
        set.add(0);
        for (int i : zeroRoom) {
            queue.offer(i);
            set.add(i);
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            List<Integer> currRoom = rooms.get(curr);
            for (int c : currRoom) {
                if (!set.contains(c)) {
                    queue.offer(c);
                    set.add(c);
                }

            }
        }
        return set.size() == n;
    }

    /*
    Method2: DFS
     */

    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms, 0, visited);
        for (boolean b : visited) if (!b) return false;
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int idx, boolean[] visited) {
        if (visited[idx]) return;
        visited[idx] = true;
        for (int i : rooms.get(idx)) dfs(rooms, i, visited);
    }

    /*
    Method3: UnionFind
     */

    public boolean canVisitAllRooms3(List<List<Integer>> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            return true;
        }

        UnionFind uf = new UnionFind(rooms.size());
        dfs(uf, 0, rooms, new boolean[rooms.size()]);

        return uf.count() == 1;
    }

    public void dfs(UnionFind uf, int index, List<List<Integer>> rooms, boolean[] visited) {
        // 取出当前房间的所有钥匙
        List<Integer> keys = rooms.get(index);

        // 钥匙为空，递归停止
        if (keys.isEmpty()) {
            return;
        }

        // 房间已经被打开过，递归停止
        if (visited[index]) {
            return;
        }

        // 设置房间已被打开过
        visited[index] = true;
        for (Integer key : keys) {
            // 用钥匙开其他房间的门
            uf.union(index, key);

            // 递归用其他房间的钥匙再去其他房间的门
            dfs(uf, key, rooms, visited);
        }
    }
}
