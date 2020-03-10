package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class UpdateMatrix542 {

    /*
    We add all 0 elements to the queue, and set the res of all of their coordinates to 0 while 1s are Integer.MAX_VALUE. We bfs from all of 0s, and update the unvisited elements to the shortest distance if they are Integer.MAX_VALUE. You can also just check if it's a greater number than your current distance but a lot of people were getting confused with the time complexity. (bfs visits the shortest path to each node first so at most every node will only be visited once again)
     */
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> bfs = new LinkedList<>();
        final int M = matrix[0].length;
        final int N = matrix.length;
        int[][] res = new int[N][M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (matrix[i][j] == 0) {
                    bfs.add(new int[]{i, j, 0});
                    res[i][j] = 0;
                } else {
                    res[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] direct = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        while (!bfs.isEmpty()) {
            int[] pos = bfs.remove();
            int nextVal = pos[2] + 1;
            for (int[] dir : direct) {
                int nextRow = pos[0] + dir[0];
                int nextCol = pos[1] + dir[1];
                if (nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < M &&
                        res[nextRow][nextCol] == Integer.MAX_VALUE) {
                    res[nextRow][nextCol] = nextVal;
                    bfs.add(new int[]{nextRow, nextCol, nextVal});
                }

            }
        }
        return res;
    }

    /*
    Method 2 : Direct
     */
    public int[][] updateMatrix2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return null;
        int row = matrix.length;
        int col = matrix[0].length;
        boolean isChange = true;
        int k = 1;
        while (isChange) {
            isChange = false;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] >= k
                            && (i == 0 || matrix[i - 1][j] >= k)
                            && (j == 0 || matrix[i][j - 1] >= k)
                            && (i == row - 1 || matrix[i + 1][j] >= k)
                            && (j == col - 1 || matrix[i][j + 1] >= k)) {
                        //本身为1,四周也为1的情况
                        matrix[i][j]++;
                        isChange = true;
                    }
                }
            }
            k++;
        }
        return matrix;
    }

    @Test
    public void test() {
        updateMatrix(new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 0, 0}});
    }
}
