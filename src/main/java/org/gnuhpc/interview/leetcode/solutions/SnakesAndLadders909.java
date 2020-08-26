package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/8/26
 */
public class SnakesAndLadders909 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length, k = 1;
        int[] nums = new int[n * n + 1];
        boolean[] visit = new boolean[n * n + 1];
        while (k <= n * n) {  // 用一维数组记录每个方格对应的值，也可以用map记录，但数组效率更高
            int row = n - 1 - (k - 1) / n;
            int col = ((k - 1) / n % 2 == 0) ? (k - 1) % n : (n - 1) - (k - 1) % n;
            nums[k++] = board[row][col];


        }

        Queue<Integer> pq = new LinkedList<>();
        int start = 1, step = 0;
        pq.add(1);
        while (!pq.isEmpty()) {  //bfs广度遍历图，找到最短路径
            int num = pq.size();
            for (int i = 0; i < num; i++) {
                start = pq.poll();
                if (start == n * n) return step;
                for (int j = 1; j < 7 && start + j <= n * n; j++) {
                    int num1 = nums[start + j];
                    int loca = (num1 == -1) ? start + j : num1;
                    if (!visit[loca]) {
                        pq.add(loca);
                        visit[loca] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
