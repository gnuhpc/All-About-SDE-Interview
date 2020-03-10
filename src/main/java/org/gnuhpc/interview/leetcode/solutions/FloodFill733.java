package org.gnuhpc.interview.leetcode.solutions;

public class FloodFill733 {
    private int n;
    private int m;
    private int[][] dr = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        n = image.length;
        m = image[0].length;

        int oldColor = image[sr][sc];

        dfs(image, sr, sc, newColor, oldColor);

        return image;
    }

    private void dfs(int[][] image, int x, int y, int newColor, int oldColor) {
        if (image[x][y] != oldColor || image[x][y] == -1) return;

        image[x][y] = -1; //Marked as a being visited node

        for (int[] d : dr) {
            int newX = x + d[0];
            int newY = y + d[1];
            if (isValid(newX, newY)) {
                dfs(image, newX, newY, newColor, oldColor);
            }
        }

        image[x][y] = newColor;
    }

    private boolean isValid(int x, int y) {
        return (x >= 0) && (x < n) && (y >= 0) && (y < m);
    }
}
