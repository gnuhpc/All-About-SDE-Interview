package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class UniquePathsWithObstacles63 {
    private int[][] dr = new int[][]{
            {0,1},
            {1,0}
    };
    private int r;
    private int c;
    private int[][] memo;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.r = obstacleGrid.length;
        this.c = obstacleGrid[0].length;
        this.memo = new int[this.r][this.c];
        for(int[] tmp: memo){
            Arrays.fill(tmp,-1);
        }
        if (!isValid(0,0,obstacleGrid)) return 0;
        return dfs(0, 0, obstacleGrid);
    }


    public int dfs(int r, int c, int[][]grid) {
        if (r == this.r -1 && c == this.c -1) {
            return (grid[r][c]==1)? 0:1;
        }


        if (memo[r][c]!=-1)
            return memo[r][c];

        int res = 0;
        for(int[] d:dr){
            int newX = r + d[0];
            int newY = c + d[1];
            if (isValid(newX, newY, grid)){
                res += dfs(newX, newY, grid);
            }
        }

        memo[r][c]= res;
        return res;
    }

    private boolean isValid(int r, int c, int[][] grid) {
        return r < this.r && r >=0 && c< this.c && c>=0 && grid[r][c]!=1;
    }


    @Test
    public void test(){
        System.out.println(uniquePathsWithObstacles(
                new int[][]{{0}, {1}})
        );
    }

    /*
    Method2 : 倒着推
    */
    //add by tina,通过定义私有属性，赋值方式，避免了对数组传参
    // memo search
    private Integer[][] path; // Integer做判空比int要快很多
    private int[][] obstacleGrid;
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid[0] == null) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        this.obstacleGrid = obstacleGrid;
        path = new Integer[m][n];
        return search(m-1,n-1);
    }

    public int search(int i,int j){
        if(i<0||j<0) return 0;  //因为下面这个条件需保证i,j合法性
        if(obstacleGrid[i][j] == 1) return 0; //[[1]] == 0
        if(i == 0 && j==0) return 1;

        if(path[i][j] != null) return path[i][j];

        if(i>=0 && j>=0) path[i][j] = search(i-1,j) + search(i,j-1);
        return path[i][j];
    }

    // add by tina, 对比62，仅是DP的条件有变化
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length <= 0) {
            return 0;
        }
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        int[][] dp = new int[r][c];

        for (int i = 0; i < c; i++)
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
                break;   // 遇到障碍后面的都无法到达直接返回就行 默认就是0
            }
            else dp[0][i] = 1;

        for (int i = 0; i < r; i++)
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
                break;  // 遇到障碍后面的都无法到达直接返回就行 默认就是0
            }
            else dp[i][0] = 1;

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (obstacleGrid[i][j] == 1)  dp[i][j] = 0; // 遇到障碍就是0
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // dp
            }
        }
        return dp[r - 1][c - 1];
    }
}
