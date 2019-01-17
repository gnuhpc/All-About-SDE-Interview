package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Point;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solve130 {
    /*
    Method1 : BFS, 依次进行BFS，需要处理特殊情况
     */
    int[][] dirs = {{0,0},{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solve(char[][] board) {
        if (board == null || board.length <= 1 || board[0].length <= 1) return;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && i != 0 && j != 0) {
                    BFS(board, i, j);
                }
            }
        }

    }

    private void BFS(char[][] board, int x, int y) {
        boolean canMark = true;
        Set<Point> tempSet = new HashSet<>();
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        while (q.size() > 0) {
            Point p = q.poll();
            for (int[] dir : dirs) {
                int x1 = p.x + dir[0];
                int y1 = p.y + dir[1];

                Point nextPoint = new Point(x1, y1);
                if (x1 >= 0 && y1 >= 0 && x1 < board.length && y1 < board[0].length
                        && !tempSet.contains(nextPoint)) {
                    if (board[x1][y1] == 'O'){
                        if (x1 == 0 || y1 == 0 || x1==board.length -1 || y1==board[0].length -1) {
                            canMark = false;
                        }

                        tempSet.add(nextPoint);
                        board[x1][y1] = 'X';
                        q.offer(nextPoint);
                    }
                }
            }
        }
        if (!canMark) { //recovery
            for (Point point : tempSet) {
                board[point.x][point.y] = 'O';
            }
        }
    }

    /*
    Method2 : BFS, 既然BFS最终扩散可能会回溯，干脆从可能回溯的部分开始，
    先处理完四个边缘，将不能处理的O标记为W。最后全部遍历一遍，将W改为O，其余的统统可以X。
     */

    public void solve2(char[][] board) {
        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            bfs(board, i, 0);
            bfs(board, i, m - 1);
        }
        for (int j = 0; j < m; j++) {
            bfs(board, 0, j);
            bfs(board, n - 1, j);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'W') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    void bfs(char[][] board, int sx, int sy) {
        if (board[sx][sy] != 'O') {
            return;
        }
        int n = board.length;
        int m = board[0].length;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sx,sy));
        board[sx][sy] = 'W';                          // 'W' ->  Water
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int cx = point.x;
            int cy = point.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m
                        && board[nx][ny] == 'O') {
                    board[nx][ny] = 'W';              // 'W' ->  Water
                    queue.offer(new Point(nx,ny));
                }
            }
        }
    }

    @Test
    public void test() {
        char[][] arr = new char[][]{
                {'X', 'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'X'}
        };
        solve(arr);

        System.out.println();
    }
}
