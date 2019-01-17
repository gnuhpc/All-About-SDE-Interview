package org.gnuhpc.bigdata.leetcode;

public class LongestIncreasingPath329 {
    public int longestIncreasingPath(int[][] matrix) {

        if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
            return 0;

        int max = 0, n = matrix.length, m = matrix[0].length;

        // create a cache matrix
        int[][] cache = new int[n][m];

        // dfs search on every element in matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(dfs(matrix, Integer.MIN_VALUE, i, j, n, m, cache), max);
            }
        }
        return max;
    }

    int dfs(int[][] matrix, int min, int i, int j, int n, int m, int[][] cache) {

        // check boundary limits
        if (i < 0 || j < 0 || i >= n || j >= m)
            return 0;

        // check min condition
        if (matrix[i][j] <= min)
            return 0;

        // check into cache
        if (cache[i][j] != 0)
            return cache[i][j];

        // update min
        min = matrix[i][j];

        // run dfs in all four directions
        int a = dfs(matrix, min, i - 1, j, n, m, cache) + 1;
        int b = dfs(matrix, min, i + 1, j, n, m, cache) + 1;
        int c = dfs(matrix, min, i, j - 1, n, m, cache) + 1;
        int d = dfs(matrix, min, i, j + 1, n, m, cache) + 1;

        // find max and update cache
        int max = Math.max(a, Math.max(b, Math.max(c, d)));
        cache[i][j] = max;

        return max;
    }

    /*
    写法二
     */
    public static int n,m;

    public static int f[][] = new int[1000][1000];

    public static boolean check(int x,int y,int nx,int ny,int[][] mat){//能不能走到下一个格子，那些格子可以继续拓展
        return x >=0 && y>= 0 && nx >=0 && ny >=0 && x < n && y <m && nx < n && ny <m && mat[x][y] > mat[nx][ny];
    }

    public int robot(int x,int y,int[][] mat){//最远能走多少步

        if(f[x][y] > 0){
            return f[x][y];
        }

        int max = 0;
        for(int dx = -1;dx <= 1;dx++){
            for (int dy = -1;dy <= 1;dy++){
                if(Math.abs(dx + dy) == 1){ // 必须只在一个方向上走一步才是有效移动
                    if(check(x,y,x+dx,y+dy,mat))
                        max = Math.max(max,robot(x+dx,y+dy,mat)) ;
                }
            }
        }
        f[x][y] = max + 1;
        return max+1;
    }

    //枚举最后中止的位置, 为什么从最终位置倒退，是因为递归
    public int longestIncreasingPath2(int[][] matrix) {
        n = matrix.length;
        if (n==0) return 0;
        m = matrix[0].length;

        for(int i = 0;i< n;i++){
            for(int j = 0;j < m; j++){
                f[i][j] = 0;
            }
        }

        int ans = 0;
        for(int i =0;i<n;i++){
            for(int j = 0;j<m;j++){
                ans = Math.max(ans,robot(i,j,matrix));
            }
        }
        return ans;
    }

}
