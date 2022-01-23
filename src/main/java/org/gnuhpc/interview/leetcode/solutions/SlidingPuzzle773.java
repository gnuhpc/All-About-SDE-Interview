package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2021/12/26
 */

//TODO 二维数组的BFS表示
public class SlidingPuzzle773 {
    public int slidingPuzzle(int[][] board) {
        String end = "123450";
        String cur = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                cur += board[i][j];
            }
        }
        int[][] dirs = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        Set<String> visit = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(cur);
        int step = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                cur = q.poll();
                if (cur.equals(end)) return step;
                int idx = cur.indexOf("0");
                for (int k = 0; k < dirs[idx].length; k++) {
                    String next = swap(cur, dirs[idx][k], idx);
                    if (visit.contains(next)) continue;
                    q.add(next);
                    visit.add(next);
                }
            }
            step++;
        }
        return -1;
    }

    String swap(String cur, int i, int j) {
        StringBuilder sb = new StringBuilder(cur);
        sb.setCharAt(i, cur.charAt(j));
        sb.setCharAt(j, cur.charAt(i));
        return sb.toString();
    }
}
