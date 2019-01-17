package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MinPathSum64 {
    /*
    Method 1 : dfs (LTE)
     */

    private int[] stepX = new int[]{0, 1};
    private int[] stepY = new int[]{1, 0};
    private int ROW;
    private int COL;
    private int visited = Integer.MAX_VALUE;

    public int minPathSum(int[][] grid) {
        ROW = grid.length;
        if (ROW == 0) return 0;
        COL = grid[0].length;
        if (COL == 0) return 0;

        dfs(0, 0, grid, new ArrayList<>());

        return visited;
    }

    public void dfs(int r, int c, int[][] grid, List<Integer> temp) {
        if (r == ROW - 1 && c == COL - 1) {
            visited = Math.min(visited, temp.stream().mapToInt(Integer::intValue).sum() + grid[r][c]);
            return;
        }

        for (int i = 0; i < 2; i++) {
            if (valid(r, c)) {
                temp.add(grid[r][c]);
                dfs(r + stepY[i], c + stepX[i], grid, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean valid(int r, int c) {
        return r < ROW && r >= 0 && c < COL && c >= 0;
    }


    /*
    Method 2: dfs + Memorization //倒退法
     */

    public int minPathSum2(int[][] grid) {
        ROW = grid.length;
        if (ROW == 0) return 0;
        COL = grid[0].length;
        if (COL == 0) return 0;
        int[][] visited =new int[ROW][COL] ;

        return dfs2(ROW-1,COL-1, grid, visited);
    }
    private int dfs2(int r, int c,int[][] grid, int[][] visited){
        if(r==0&&c==0){
            return grid[0][0];
        }
        if(!valid(r,c)){
            return Integer.MAX_VALUE;
        }
        if(visited[r][c]!=0){
            return visited[r][c];
        }
        visited[r][c]=grid[r][c]+Math.min(dfs2(r-1,c,grid,visited), dfs2(r,c-1,grid,visited));
        return visited[r][c];
    }

    /*
    Method 3 : 2D DP
    设数组A[row][col],Min[i][j] = min(Min[i-1][j], Min[i][j-1]) +Sample[i][j];
    注意初始条件即可。
     */

    public int minPathSum3(int[][] grid) {
        ROW = grid.length;
        if (ROW == 0) return 0;
        COL = grid[0].length;
        if (COL == 0) return 0;

        int[][] visited = new int[ROW][COL];

        visited[0][0] = grid[0][0];
        //第一列的初始条件
        for (int i = 1; i < ROW; i++) {
            visited[i][0] = visited[i - 1][0] + grid[i][0];
        }

        //第一行的初始条件
        for (int i = 1; i < COL; i++) {
            visited[0][i] = visited[0][i - 1] + grid[0][i];
        }

        //除了第一列和第一行以外的格子
        for (int i = 1; i < ROW; i++) {
            for (int j = 1; j < COL; j++) {
                visited[i][j] = Math.min(visited[i - 1][j], visited[i][j - 1]) + grid[i][j];
            }
        }
        return visited[ROW - 1][COL - 1];
    }


    @Test
    public void test() {
        System.out.println(minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
    }
}
