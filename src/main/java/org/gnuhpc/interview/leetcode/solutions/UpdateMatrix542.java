package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class UpdateMatrix542 {

    /*
    Method: BFS +
    题目给出了多个1，要找出每个1到0的最近曼哈顿距离。由于1到0的距离和0到1的距离一样的，
    所以其实我们可以换个思维：找出每个0到1的距离。
     */

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return null;
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[m][n];//结果集
        boolean[][] visited = new boolean[m][n];//记录已经计算过的位置
        Queue<int[]> queue = new LinkedList<>();//广搜队列
        //遍历，将等于0的位置计入结果集并入队
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        //四个方向广搜
        int[][] dr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//上下左右
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            //四个方向上找 1
            for (int[] d : dr) {
                int newX = x + d[0], newY = y + d[1];
                //没有计算过的地方一定是 1
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY]) {
                    res[newX][newY] = res[x][y] + 1;
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY});
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
        updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
    }
}
