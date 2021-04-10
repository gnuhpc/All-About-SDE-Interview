package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2021/2/22
 */
public class IsToeplitzMatrix766 {
    private int r, c;
    private boolean[][] visited;

    public boolean isToeplitzMatrix(int[][] matrix) {
        this.r = matrix.length;
        this.c = matrix[0].length;
        this.visited = new boolean[r][c];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r - 1, 0});

        visited[r - 1][0] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            int[] nEle = q.peek();
            int val = matrix[nEle[0]][nEle[1]];
            for (int j = 0; j < size; j++) {
                int[] ele = q.poll();
                if (matrix[ele[0]][ele[1]] != val) return false;
                int[] n1Ele = new int[]{ele[0] - 1, ele[1]};
                if (isValid(n1Ele)) {
                    q.offer(n1Ele);
                    visited[n1Ele[0]][n1Ele[1]] = true;
                }

                int[] n2Ele = new int[]{ele[0], ele[1] + 1};
                if (isValid(n2Ele)) {
                    q.offer(n2Ele);
                    visited[n2Ele[0]][n2Ele[1]] = true;
                }
            }

        }

        return true;
    }

    private boolean isValid(int[] ele) {
        return (ele[0] >= 0 && ele[0] < r)
                && (ele[1] >= 0 && ele[1] < c)
                && visited[ele[0]][ele[1]] == false;
    }

    public boolean isToeplitzMatrix2(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) return false;
            }
        }
        return true;
    }
}
