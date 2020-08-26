package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill733 {
    private int n;
    private int m;
    private final int[][] dr = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };
    private int oldColor;
    private int newColor;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        n = image.length;
        m = image[0].length;

        oldColor = image[sr][sc];
        boolean[][] visited = new boolean[n][m];
        this.newColor = newColor;
        if (isValid(sr, sc, visited)) {
            dfs(image, sr, sc, visited);
        }

        return image;
    }

    private void dfs(int[][] image, int x, int y, boolean[][] visited) {
        if (image[x][y] != oldColor) return;

        visited[x][y] = true;
        image[x][y] = newColor;

        for (int[] d : dr) {
            int newX = x + d[0];
            int newY = y + d[1];
            if (isValid(newX, newY, visited)) {
                dfs(image, newX, newY, visited);
            }
        }
    }

    private boolean isValid(int x, int y, boolean[][] visited) {
        return (x >= 0) && (x < n) && (y >= 0) && (y < m) && !visited[x][y];
    }


    /*
    Method2: BFS
     */
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        if (newColor == image[sr][sc]) return image;
        int h = image.length, w = image[0].length;  //记录image的长宽
        int[][] direct = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  //移动方向的数组
        Queue<int[]> q = new LinkedList<>();  //队列
        q.offer(new int[]{sr, sc});  //队列保存的是位置坐标
        int oldColor = image[sr][sc];  //记录旧颜色
        while (!(q.size() == 0)) {
            int[] p = q.poll();  //取出队首元素
            image[p[0]][p[1]] = newColor;  //上色
            for (int[] d : direct) {  //将四周相同颜色的点的位置入队
                int new_sr = p[0] + d[0];
                int new_sc = p[1] + d[1];
                if (new_sr >= 0 && new_sr < h && new_sc >= 0 && new_sc < w && image[new_sr][new_sc] == oldColor) {
                    q.offer(new int[]{new_sr, new_sc});
                }
            }
        }
        return image;
    }

    @Test
    public void test() {
        floodFill(new int[][]{
                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2);
    }
}
