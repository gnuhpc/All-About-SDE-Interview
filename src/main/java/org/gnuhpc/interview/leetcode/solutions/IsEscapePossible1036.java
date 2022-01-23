package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Copyright gnuhpc 2022/1/11
 */
public class IsEscapePossible1036 {
    int bound = 1000000;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        // 记录封锁列表节点（不可走）
        Set<Long> blockedNode = new HashSet<>();
        for (int i = 0; i < blocked.length; i++) {
            blockedNode.add((long) blocked[i][0] * bound + blocked[i][1]);
        }

        // 只有当起点和终点都能破圈时，说明可以从起点走到终点。
        return bfs(source, target, blockedNode) && bfs(target, source, blockedNode);
    }

    public boolean bfs(int[] source, int[] target, Set<Long> blockedNode) {

        Set<Long> visited = new HashSet<>();
        visited.add((long) source[0] * bound + source[1]);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{source[0], source[1]});

        int layer = 0;              // 记录当前扩展的层数
        while (!queue.isEmpty()) {
            int size = queue.size();
            layer++;

            for (int i = 0; i < size; i++) {
                // 访问当前节点
                int[] cur = queue.poll();

                // 走到了终点那就返回true
                if (cur[0] == target[0] && cur[1] == target[1]) {
                    return true;
                }

                // 将邻接节点入队
                for (int[] direction : directions) {
                    int nx = cur[0] + direction[0];
                    int ny = cur[1] + direction[1];
                    long newNode = (long) nx * bound + ny;
                    if (nx >= 0 && nx < bound && ny >= 0 && ny < bound && !blockedNode.contains(newNode) && !visited.contains(newNode)) {
                        queue.offer(new int[]{nx, ny});
                        visited.add(newNode);
                    }
                }
            }
            // 搜索的层数超过了所有封锁点的个数那么就表示起点/终点没有被封锁。
            if (layer > blockedNode.size()) return true;
        }
        return false;
    }
/*
作者：coping_code
链接：https://leetcode-cn.com/problems/escape-a-large-maze/solution/yi-ge-jian-dan-qie-zhi-jue-de-si-lu-chun-5mdp/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
