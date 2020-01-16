package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveNQueens51 {
    /*
    Method : DFS 深度优先标准方法
     */
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(char[] row: board){
            Arrays.fill(row,'.');
        }
        List<List<String>> res = new ArrayList<>();
        dfs(board, 0, res);
        return res;
    }

    private void dfs(char[][] board, int col, List<List<String>> res) {
        if(col == board.length) {
            res.add(construct(board));
            return;
        }

        for(int row = 0; row < board.length; row++){
            if(isValid(board, row, col)) {
                board[row][col] = 'Q';
                dfs(board, col + 1, res);
                board[row][col] = '.';  //backtracking
            }
        }
    }

    /////Common Function
    private boolean isValid(char[][] board, int row, int col) {
        for(int i=0; i<board.length; i++) {
            if(board[i][col]!='.') return false; //同一列已经放置了，返回false
            if(board[row][i]!='.') return false; //同一行已经放置了，返回false
        }

        //两个对角线
        for(int i=row, j=col;i>=0 && j>=0;i--,j--) {
            if( board[i][j] != '.' )
                return false;
        }

        for(int i=row, j=col;i<board.length && j>=0;i++,j--) {
            if( board[i][j] != '.')
                return false;
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] row : board) {
            res.add(String.valueOf(row));
        }
        return res;
    }

    @Test
    public void test(){
        System.out.println(solveNQueens(4));
    }
}
