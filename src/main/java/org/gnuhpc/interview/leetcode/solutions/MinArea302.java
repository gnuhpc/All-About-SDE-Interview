package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class MinArea302 {
    private int top = Integer.MAX_VALUE, bottom = Integer.MIN_VALUE,
            left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
    private int r, c;
    private int[][] dr = new int[][]{
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    /*
    Method1: BFS
     */
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) return 0;
        r = image.length;
        c = image[0].length;

        bfs(image, x, y);

        return (right - left + 1) * (bottom - top + 1);
    }

    private void bfs(char[][] image, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] point = q.poll();
            x = point[0];
            y = point[1];
            if (!isValid(x, y) || image[x][y] == '0') continue;
            left = Math.min(left, y);
            right = Math.max(right, y);
            top = Math.min(top, x);
            bottom = Math.max(bottom, x);
            image[x][y] = '0';
            for (int i = 0; i < 4; i++) {
                int newX = point[0] + dr[i][0];
                int newY = point[1] + dr[i][1];
                q.offer(new int[]{newX, newY});
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    /*
    Method2 : DFS 比BFS快，因为要找最远处的
     */
    public int minArea2(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) return 0;
        top = bottom = x;
        left = right = y;
        dfs(image, x, y);
        return (right - left + 1) * (bottom - top + 1);
    }

    private void dfs(char[][] image, int x, int y) {
        if (x < 0 || y < 0 || x >= image.length || y >= image[0].length ||
                image[x][y] == '0')
            return;
        image[x][y] = '0'; // mark visited black pixel as white
        top = Math.min(top, x);
        bottom = Math.max(bottom, x);
        left = Math.min(left, y);
        right = Math.max(right, y);
        dfs(image, x + 1, y);
        dfs(image, x - 1, y);
        dfs(image, x, y - 1);
        dfs(image, x, y + 1);
    }

    @Test
    public void test() {
        System.out.println(minArea(new char[][]{
                {'0', '0', '1', '0'},
                {'0', '1', '1', '0'},
                {'0', '1', '0', '0'}
        }, 0, 2));
    }
}
