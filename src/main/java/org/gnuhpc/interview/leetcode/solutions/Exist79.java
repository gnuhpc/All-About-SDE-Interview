package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class Exist79 {
    // 二维平面的坐标偏移量
    // 二维DFS 判断

    private final int[][] dr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean[][] visited;
    int m, n;

    public boolean exist(char[][] board, String word) {
        // 也可以不用，直接将原来的数组标记为一个#，表示访问过 ,这样更节省内存
        // 而且不用判断是不是绕回去了， 因为绕回去也不会因为元素重复而判错 //二维DFS的优化
        if(board == null || board.length == 0 || board[0].length == 0) return false;
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int x, int y, String word, int idx) {
        if (idx == word.length() - 1) return board[x][y] == word.charAt(idx);
        if (board[x][y] != word.charAt(idx)) return false;

        visited[x][y] = true;
        for (int[] d : dr) {
            int newX = x + d[0];
            int newY = y + d[1];
            if (isValid(newX, newY)) {
                if (dfs(board, newX, newY, word, idx + 1))
                    return true;
            }
        }

        visited[x][y] = false;

        return false;
    }

    public boolean isValid(int x, int y) {
        return (x >= 0 && x < m && y >= 0 && y < n) && !visited[x][y];
    }


    @Test
    public void test() {
//        System.out.println(exist(new char[][]{
//            {'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}
//        },"ABCCED"));
//        System.out.println(exist(new char[][]{
//            {'A'}
//        },"A"));
        System.out.println(exist(new char[][]{
                {'a', 'a'}
        }, "aaa"));
    }
}
