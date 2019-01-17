package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UniquePaths62 {
    /*
    Method: Raw dfs (LTE)
     */
    private int[] stepX = new int[]{0,1};
    private int[] stepY = new int[]{1,0};
    private int ROW;
    private int COL;

    private int res;
    public int uniquePaths(int m, int n) {
        this.ROW = m;
        this.COL = n;

        dfs(0,0);

        return res;
    }

    private void dfs(int r, int c) {
        if (r == ROW -1 && c == COL -1) {
            res++;
        }

        for (int i = 0; i < 2; i++) {
            if (valid(r+stepY[i],c + stepX[i])){
               dfs(r + stepY[i], c + stepX[i]);
            }
        }
    }


    /*
    dfs + memorization
     */
    public int uniquePaths2(int m, int n) {
        this.ROW = m;
        this.COL = n;
        return dfs2(0, 0, new Integer[m][n]);
    }

    public int dfs2(int r, int c, Integer[][] memo) {
        int res = 0;
        if (valid(r,c)){
            if (r == ROW -1 && c == COL -1) {
                memo[r][c] = 1;
                return 1;
            }


            if (memo[r][c] != null)
                return memo[r][c];

            for (int i = 0; i < 2; i++) {
                res += dfs2(r + stepY[i], c + stepX[i], memo);
            }
            memo[r][c] = res;
        }

        return res;
    }

    private boolean valid(int r, int c) {
        return r < ROW && r >=0 && c< COL && c>=0 ;
    }

    /*
    Method 3: recursion
     */

    public int uniquePaths3(int m, int n) {
        if(m==1 || n==1) return 1;
        return uniquePaths3(m-1,n)+uniquePaths3(m,n-1);
    }

    /* Method 4 :DP
     */

    public int uniquePaths4(int m, int n) {
        int nums[][] = new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(i==0 || j==0)
                {
                    nums[i][j]=1;
                } else {
                    nums[i][j]=nums[i-1][j]+nums[i][j-1];
                }
            }
        }
        return nums[n-1][m-1];
    }


    @Test
    public void test(){
        System.out.println(uniquePaths(3,2));
        System.out.println(uniquePaths2(3,2));
    }
}
