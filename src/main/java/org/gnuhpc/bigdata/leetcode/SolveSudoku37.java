package org.gnuhpc.bigdata.leetcode;

public class SolveSudoku37 {

    public void solveSudoku(char[][] board) {
        if (board == null) return;

        solve(board,0,0);
    }


    public boolean solve(char[][] board,int rowIndex, int columnIndex) {
        if( rowIndex == 9 && ++columnIndex == 9){
            return true;
        }

        if( rowIndex == 9){
            //System.out.println("Hop to next column, reinitialize rowIndex=0");
            rowIndex=0;
        }

        if ( board[rowIndex][columnIndex] != '.' ) { // skip filled cells
            return solve(board,rowIndex + 1, columnIndex);
        }

        for (char number = '1'; number <= '9'; ++number) {
            if (isValid(board,rowIndex, columnIndex, number) ) {
                board[rowIndex][columnIndex] = number;
                if (solve(board,rowIndex + 1, columnIndex) )
                    return true;
                else {
                    board[rowIndex][columnIndex] = '.'; // backtracking !!!
                }
            }
        }

        return false;
    }

    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; ++i) // check the rows
            if ( board[i][col] == c )
                return false;

        for (int k = 0; k < 9; ++k)
            if ( board[row][k] == c )
                return false;

        // if the given number is already in the box: the number cannot be part of the solution
        // 求出每一个box的左上角
        // TODO
        int boxRowOffset = (row / 3) * 3;
        int boxColumnOffset = (col / 3) * 3;

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                if (c == board[boxRowOffset + i][boxColumnOffset + j])
                    return false;

        return true;
    }
}
