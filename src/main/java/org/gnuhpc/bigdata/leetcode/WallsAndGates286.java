package org.gnuhpc.bigdata.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 2019/9/28
 */

//https://gfzj.online/leetcode/detail.html?id=286 里面有个DFS\BFS的性能分析
public class WallsAndGates286 {
    private int r, c;
    private int[][] dr = new int[][]{
            {0, -1},
            {0, 1},
            {1, 0},
            {-1, 0}
    };

    /*
    Method1: DFS
     */
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        r = rooms.length;
        c = rooms[0].length;
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[0].length; j++)
                if (rooms[i][j] == 0) dfs(rooms, i, j);
    }

    //TODO 反复扫描更新
    private void dfs(int[][] rooms, int i, int j) {
        for (int[] d : dr) {
            int newX = i + d[0];
            int newY = j + d[1];
            if (!isValid(newX, newY)) {
                continue;
            }
            //TODO 如果发现在某点处计算出来的距离值比图中记录的距离值还要大的时候，就不要将该点纳入扫描路径。
            if (rooms[newX][newY] <= rooms[i][j] + 1) {
                continue;
            }

            //TODO 别忘了更新
            rooms[newX][newY] = rooms[i][j] + 1;
            dfs(rooms, newX, newY);
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    /*
    Method2: BFS
     */

    public void wallsAndGates2(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        r = rooms.length;
        c = rooms[0].length;
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[0].length; j++)
                if (rooms[i][j] == 0) bfsNative(rooms, i, j);
    }

    private void bfsNative(int[][] rooms, int i, int j) {
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j}); // Put gate in the queue
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            i = pos[0];
            j = pos[1];
            for (int[] d : dr) {
                int newX = i + d[0];
                int newY = j + d[1];
                if (!isValid(newX, newY)) continue;
                if (rooms[newX][newY] <= rooms[i][j] + 1) continue;

                rooms[newX][newY] = rooms[i][j] + 1;
                queue.offer(new int[]{newX, newY});
            }
        }
    }

    /*
    Method3 : MuiltEnd BFS //TODO
     */

    public void wallsAndGates3(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        r = rooms.length;
        c = rooms[0].length;
        Deque<int[]> queue = new LinkedList<>();
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (rooms[i][j] == 0) queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int i = pos[0], j = pos[1];
            for (int[] d : dr) {
                int newX = i + d[0];
                int newY = j + d[1];
                if (!isValid(newX, newY)) continue;
                if (rooms[newX][newY] <= rooms[i][j] + 1) continue;

                rooms[newX][newY] = rooms[i][j] + 1;
                queue.offer(new int[]{newX, newY});
            }
        }
    }


}
