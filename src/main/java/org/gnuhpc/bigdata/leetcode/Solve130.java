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

    // add by tina
    // 定义4个方向遍历的偏移量
    // 本体不需要使用used这样的数组，因为可以在原二维数组上标记
    // 对比第200题-岛屿个数
    private int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};
    private int m,n;

    public void solve3(char[][] board) {
        if(board == null || board.length==0)
            return;

        m = board.length;
        n = board[0].length;
        //4个for循环，寻找边界为O的字符，通过dfs遍历和其连通的O，都标记为*
       /* for(int j = 0; j< n;j++){
            if(board[0][j] == 'O'){
                dfs(board,0,j);
            }
        }
        for(int j = 0; j< n;j++){
            if(board[m-1][j] == 'O'){
                dfs(board,m-1,j);
            }
        }
        for(int i = 0; i< m;i++){
            if(board[i][0] == 'O'){
                dfs(board,i,0);
            }
        }
        for(int i = 0; i<m;i++){
            if(board[i][n-1] == 'O'){
                dfs(board,i,n-1);
            }
        }
        // 边界及与其联通的O都被标为*后，中间严格被X包围的O可以翻转为X了
        for(int i=0;i<m;i++)
            for(int j = 0; j<n;j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        // 有效O反转后，将*标重置为O
        for(int i=0;i<m;i++)
            for(int j = 0; j<n;j++){
                if(board[i][j] == '*')
                    board[i][j] = 'O';
            }*/

       // 优化
        //merge O's on left & right boarder
        for(int i=0;i<m;i++){
            if(board[i][0] == 'O'){
                dfs(board, i, 0);
            }

            if(board[i][n-1] == 'O'){
                dfs(board, i, n-1);
            }
        }

        //merge O's on top & bottom boarder
        for(int j=0; j<n; j++){
            if(board[0][j] == 'O'){
                dfs(board, 0, j);
            }

            if(board[m-1][j] == 'O'){
                dfs(board, m-1, j);
            }
        }

        //process the board
        for(int i=0;i<m;i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == '*'){//同一个位置，不会被扫2次
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int startx , int starty){ //, boolean[][] used
        board[startx][starty] = '*';

        for(int i = 0; i<4; i++){
            int newx = startx + d[i][0];
            int newy = starty + d[i][1];
            if(isInArea(newx,newy) && board[newx][newy] == 'O' ){//&& !used[newx][newy]
                dfs(board, newx ,newy); //,used
            }
        }
    }

    private boolean isInArea(int i ,int j){
        return i >= 0 && i< m && j >= 0 && j<n;
    }

}
