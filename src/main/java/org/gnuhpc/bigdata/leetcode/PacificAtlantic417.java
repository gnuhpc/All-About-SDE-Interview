package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*

深度优先，先找到那些肯定能流到太平洋里面的点（第一行和第一列），
然后从他们开始找到其他能流到太平洋里面的点，就得到了所有能流到太平洋里面的点的坐标
同理，得到所有能流到大西洋里面的点的坐标
两个都能流到的地方就是要求的地方

 */

public class PacificAtlantic417 {

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][]Pacific = new boolean[n][m];
        boolean[][]Atlantic = new boolean[n][m];
        for(int i = 0;i < n;i++){
            dfs(matrix, Pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, Atlantic, Integer.MIN_VALUE, i, m - 1);
        }
        for(int i = 0;i < m;i++){
            dfs(matrix, Pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, Atlantic, Integer.MIN_VALUE, n - 1, i);
        }
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(Pacific[i][j] && Atlantic[i][j]){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    res.add(temp);
                }
            }
        }
        return res;
    }
    int[] dx = {0,-1,0,1};
    int[] dy = {1,0,-1,0};
    public void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y){
        int n = matrix.length;
        int m = matrix[0].length;
        if(x < 0 || x >=n || y < 0 || y >= m || matrix[x][y] < height || visited[x][y]){
            return;
        }
        visited[x][y] = true;
        for(int i = 0;i < 4;i++){
            dfs(matrix, visited, matrix[x][y], x + dx[i], y + dy[i]);
        }
    }

}
