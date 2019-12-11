package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class UniquePathsIII980 {
    private int[][] dr = new int[][]{
            {0,1},
            {1,0},
            {0,-1},
            {-1,0}
    };

    private int r;
    private int c;
    private boolean[][] visited;
    private int count;
    private int[] start = new int[2], end = new int[2];

    public int uniquePathsIII(int[][] grid) {
        r = grid.length;
        c = grid[0].length;
        count = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) {
                    count++;
                }

                else if(grid[i][j] == 1){
                    start[0] = i;
                    start[1] = j;
                }

                else if (grid[i][j] == 2){
                    end[0] = i;
                    end[1] = j;
                }
            }
        }

        visited = new boolean[r][c];


        return dfs(grid, start[0],start[1],count);

    }

    private int dfs(int[][] grid, int x, int y, int count) {
        if (x == end[0] && y == end[1]) {
            if (count == 0) return 1;
            return 0;
        }


        visited[x][y] = true;
        int res = 0;
        for(int[] d:dr){
            int newX = x + d[0];
            int newY = y + d[1];
            if (isValid(newX, newY, grid)){
                res +=dfs(grid,newX, newY, grid[newX][newY]==2? count:count-1);//注意计数的情况
            }
        }

        visited[x][y] = false;

        return res;
    }

    private boolean isValid(int x, int y, int[][] grid){
        return x>=0 && x<r && y>=0 && y<c && grid[x][y] != -1 && !visited[x][y];
    }
}
