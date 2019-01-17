package org.gnuhpc.bigdata.leetcode;

public class Exist79 {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        boolean[][] isBeingVisited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == word.charAt(0) &&
                        dfs(board, word, isBeingVisited, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[][] isBeingVisited, int index, int row, int col) {
        if (index == word.length()) {  // reached the end of the word
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || isBeingVisited[row][col] || word.charAt(index) != board[row][col]) {
            return false;
        }
        isBeingVisited[row][col] = true;
        if (dfs(board, word, isBeingVisited, index + 1, row - 1, col)) {
            return true;
        }
        if (dfs(board, word, isBeingVisited, index + 1, row + 1, col)) {
            return true;
        }
        if (dfs(board, word, isBeingVisited, index + 1, row, col - 1)) {
            return true;
        }
        if (dfs(board, word, isBeingVisited, index + 1, row, col + 1)) {
            return true;
        }
        isBeingVisited[row][col] = false;  // umark current point; it might be used in other routes
        return false;
    }
}
