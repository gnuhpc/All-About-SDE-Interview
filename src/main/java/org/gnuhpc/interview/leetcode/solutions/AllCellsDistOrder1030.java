package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/8/5
 */
public class AllCellsDistOrder1030 {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<int[]> arrayList = new ArrayList<>();

        boolean[][] visited = new boolean[R][C];

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r0, c0});
        visited[r0][c0] = true;
        while (queue.size() != 0) {
            int[] cur_x_y = queue.poll();
//               这就是当前的元素
            arrayList.add(cur_x_y);

//                找到距离当前元素为1  的元素下标
            for (int[] dir : directions) {
                int nxt_x = cur_x_y[0] + dir[0];
                int nxt_y = cur_x_y[1] + dir[1];
                if (0 <= nxt_x && nxt_x < R
                        && 0 <= nxt_y && nxt_y < C
                        && !visited[nxt_x][nxt_y]) {
                    visited[nxt_x][nxt_y] = true;
                    queue.add(new int[]{nxt_x, nxt_y});
                }
            }
        }
        int[][] res = new int[arrayList.size()][2];
        for (int i = 0; i < arrayList.size(); i++) {
            res[i][0] = arrayList.get(i)[0];
            res[i][1] = arrayList.get(i)[1];
        }
        return res;
    }
}
