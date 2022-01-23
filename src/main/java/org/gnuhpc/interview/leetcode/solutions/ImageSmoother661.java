package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/10/22
 */
public class ImageSmoother661 {
    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] smooth = new int[m][n];
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = img[i][j];
                int count = 1;
                for (int[] direction : directions) {
                    int newRow = i + direction[0], newColumn = j + direction[1];
                    if (newRow >= 0 && newRow < m && newColumn >= 0 && newColumn < n) {
                        sum += img[newRow][newColumn];
                        count++;
                    }
                }
                smooth[i][j] = sum / count;
            }
        }
        return smooth;
    }
}
