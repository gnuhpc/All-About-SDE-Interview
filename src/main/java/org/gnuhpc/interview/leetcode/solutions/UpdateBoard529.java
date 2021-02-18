package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/8/21
 */
public class UpdateBoard529 {
    // 定义 8 个方向
    int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
    int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        // 1. 若起点是雷，游戏结束，直接修改 board 并返回。
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else { // 2. 若起点是空地，则从起点开始向 8 邻域的空地进行深度优先搜索。
            dfs(board, x, y);
        }
        return board;
    }

    private void dfs(char[][] board, int i, int j) {
        // 递归终止条件：判断空地 (i, j) 周围是否有雷，若有，则将该位置修改为雷数，终止该路径的搜索。
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                continue;
            }
            if (board[x][y] == 'M') {
                cnt++;
            }
        }
        if (cnt > 0) {
            board[i][j] = (char) (cnt + '0');
            return;
        }

        // 若空地 (i, j) 周围没有雷，则将该位置修改为 ‘B’，向 8 邻域的空地继续搜索。
        board[i][j] = 'B';
        for (int k = 0; k < 8; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E') {
                continue;
            }
            dfs(board, x, y);
        }
    }

    /*
    Method2: BFS
     */
    private final int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}};
    private int m, n;

    public char[][] updateBoard2(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
        char c = board[click[0]][click[1]];
        if (c == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        bfs(click[0], click[1], board);
        return board;
    }

    public void check(int x, int y, char[][] board) {
        int count = 0;
        for (int[] dir : direction) {
            int newx = x + dir[0];
            int newy = y + dir[1];
            if (newx < 0 || newx >= m || newy < 0 || newy >= n) continue;
            if (board[newx][newy] == 'M') ++count;
        }
        if (count == 0) board[x][y] = 'B';
        else board[x][y] = (char) (count + '0');
    }

    public void bfs(int x, int y, char[][] board) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        visited[x][y] = true;
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            check(pos[0], pos[1], board);
            if (board[pos[0]][pos[1]] == 'B') {
                for (int[] dir : direction) {
                    int newx = pos[0] + dir[0];
                    int newy = pos[1] + dir[1];
                    if (newx < 0 || newx >= m || newy < 0 || newy >= n || board[newx][newy] != 'E' || visited[newx][newy])
                        continue;
                    queue.offer(new int[]{newx, newy});
                    visited[newx][newy] = true;
                }
            }
        }
    }

}
