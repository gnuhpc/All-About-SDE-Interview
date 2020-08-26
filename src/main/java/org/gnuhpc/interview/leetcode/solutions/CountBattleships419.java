package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/20
 */
public class CountBattleships419 {

    private final int[][] dr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int r, c;
    private boolean[][] visited;

    public int countBattleships(char[][] board) {
        if (board == null) return 0;
        r = board.length;
        if (r == 0) return 0;
        c = board[0].length;
        int res = 0;

        visited = new boolean[r][c];
        //Step 1： 遍历所有节点
        for (int x = 0; x < r; x++)
            for (int y = 0; y < c; y++) {
                if (board[x][y] == 'X' && !visited[x][y]) {// Step2 ：判断是否可以开始dfs
                    res++;// Step3: 有必要的时候进行计数
                    dfs(board, x, y, visited);
                }
            }

        return res;
    }

    /**
     * 此递归看起来没有结束条件，
     * 是因为我们在决定是否进行下一轮递归时，做了条件判断
     *
     * @param grid
     * @param x
     * @param y
     * @param visited
     */
    public void dfs(char[][] board, int x, int y, boolean[][] visited) {
        //DFS Step1： 标准访问过
        visited[x][y] = true;
        //DFS Step2: 向四个方向进行
        for (int[] d : dr) {
            //DFS Step3: 得到新坐标
            int newX = x + d[0];
            int newY = y + d[1];

            //DFS Step4： 判断是不是可以进一步 DFS， 判断条件和外边相比添加要确定是不是valid坐标
            if (isValid(newX, newY) && board[newX][newY] == 'X' && !visited[newX][newY])
                dfs(board, newX, newY, visited);
        }
        return;
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }
}
