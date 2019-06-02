package org.gnuhpc.bigdata.leetcode;

public class TotalNQueens52 {
    // res作为int型返回结果，最好放在类成员变量中，放在参数中不太好传递正确
    private int res = 0;
    public int totalNQueens(int n) {
        char[][] matrix = new char[n][n];
        for(int i = 0;i<n;i++)
            for(int j = 0; j<n;j++){
                matrix[i][j] = '.';
            }
        dfs(matrix,0,n);
        return res;
    }

    public void dfs(char[][] matrix,int row,int n){
        if(row == n) {
            res += 1;
            return;
        }
        for(int col = 0; col<n;col++){
            if(isValid(matrix,row,col)){
                matrix[row][col] = 'Q';
                dfs(matrix,row+1,n);
                matrix[row][col] = '.';
            }

        }
    }

    public boolean isValid(char[][] matrix,int row,int col){
        // 因为是按照行进行dfs的，所以肯定不会在同一行
        // 放置的时候，仅需看是否已有Q在当前列
        //斜对角线
        for(int i = 0; i<row; i++)
            for(int j = 0;j<matrix.length;j++){
                // 小技巧： i+j == row+col || row-i == col-j 可以表示为
                // row-i == Math.abs(col-j)
                if(matrix[i][j] == 'Q' && ( j==col || i+j == row+col || row-i == col-j ) )
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

    // 如何不设成员变量，通过传递参数的方法，返回结果
    // 取了个巧，设计一个一维数组，长度为1
    public int totalNQueens3(int n) {
        if (n==0) return 0;
        int[] res= new int[1];
        int[] column=new int[n];
        helper(res,1,n,column);
        return res[0];
    }
    public void helper(int[] res, int row, int n, int[] column){
        if(row>n) {
            res[0]=res[0]+1;
            return;
        }
        for(int i=0;i<n;i++){
            column[row-1]=i;
            if(isValid2(column,row-1,i)) helper(res,row+1,n,column);
        }
    }
    public boolean isValid2(int[] column,int i, int j){
        for(int k=0;k<i;k++){
            if(column[k]==j || i-k==Math.abs(j-column[k])) return false;
        }
        return true;
    }

}
