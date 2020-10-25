package org.gnuhpc.interview.leetcode.solutions;

public class TotalNQueens52 {
    // res作为int型返回结果，最好放在类成员变量中，放在参数中不太好传递正确
    private int res = 0;

    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        dfs(board, 0, n);
        return res;
    }

    public void dfs(char[][] matrix, int col, int n) {
        if (col == n) {
            res += 1;
            return;
        }
        for (int row = 0; row < n; row++) {
            if (isSafe(matrix, row, col)) {
                matrix[row][col] = 'Q';
                dfs(matrix, col + 1, n);
                matrix[row][col] = '.';
            }

        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] != '.') return false; //同一列已经放置了，返回false
            if (board[row][i] != '.') return false; //同一行已经放置了，返回false
        }

        //两个对角线
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] != '.')
                return false;
        }

        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] != '.')
                return false;
        }

        return true;
    }


    // 借助一维数组来实现,这个比第一个方法快
    private int count = 0;

    public int totalNQueens2(int n) {
        // 第i个位置存放的数表示row行时，Q的列
        int[] queenList = new int[n];
        // 从第0行开始放
        placeQueen(queenList, 0, n);
        return count;
    }

    private void placeQueen(int[] queenList, int row, int n) {
        // 如果已经填满，就生成结果
        if (row == n) {
            count++;
            return;
        }
        // 按照行进行放置
        for (int col = 0; col < n; col++) {// 循环每一列
            if (isValid(queenList, row, col)) { // 如果在该列放入Q不冲突的话
                // 没有回溯，因为没有修改原结果集
                // 只是临时记录结果
                queenList[row] = col;
                placeQueen(queenList, row + 1, n);
            }
        }
    }

    private boolean isValid(int[] queenList, int row, int col) {
        for (int i = 0; i < row; i++) {
            // pos为列
            int pos = queenList[i];
            if (pos == col) { // 和新加入的Q处于同一列
                return false;
            }
            if (pos + row - i == col) { // 在新加入的Q的右对角线上
                return false;
            }
            if (pos - row + i == col) { // 在新加入的Q的左对角线上
                return false;
            }
        }
        return true;
    }

    // 列不重复
    private boolean[] col;
    // 对角线不重复
    private boolean[] dia1;
    private boolean[] dia2;

    public int totalNQueens3(int n) {
        if (n == 0) {
            return res;
        }
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        // 记录搜索过程
        int[] ans = new int[n];
        dfs(n, 0, ans);
        return res;

    }

    // 寻找n皇后第index行的位置
    private void dfs(int n, int index, int[] ans) {
        // 寻找到了
        if (index == n) {
            res++;
            return;
        }
        // 寻找该行所有的列
        for (int i = 0; i < n; i++) {
            // 寻找符合要求的位置
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                // 该位置作为该行皇后的放置位置
                ans[index] = i;
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                // 寻找下一行
                dfs(n, index + 1, ans);
                // 回溯
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
            }
        }
    }
}
