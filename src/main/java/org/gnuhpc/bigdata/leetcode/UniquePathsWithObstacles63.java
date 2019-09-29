package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UniquePathsWithObstacles63 {
    private int[] stepX = new int[]{0, 1};
    private int[] stepY = new int[]{1, 0};
    private int ROW;
    private int COL;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.ROW = obstacleGrid.length;
        this.COL = obstacleGrid[0].length;
        if (!valid(0,0,obstacleGrid)) return 0;
        return dfs(0, 0, obstacleGrid, new HashMap<>());
    }


    public int dfs(int r, int c, int[][]grid, Map<String, Integer> memo) {
        if (r == ROW-1 && c == COL-1) {
            if (memo.isEmpty())
                return (grid[r][c]==1)? 0:1;
            else
                return 1;
        }

        String key = r + "," + c;
        if (memo.containsKey(key))
            return memo.get(key);

        int res = 0;
        for (int i = 0; i < 2; i++) {
            if (valid(r + stepX[i],c + stepY[i], grid)){
                res += dfs(r + stepX[i], c + stepY[i], grid, memo);
            }
        }
        memo.put(key, res);
        return res;
    }

    private boolean valid(int r, int c, int[][] grid) {
        return r < ROW && r >=0 && c<COL && c>=0 && grid[r][c]!=1;
    }

    @Test
    public void test(){
        System.out.println(uniquePathsWithObstacles(
                new int[][]{{0}, {1}})
        );
    }

    /*
    Method 2: DP
     */

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int ROW = obstacleGrid.length;
        int COL = obstacleGrid[0].length;
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++) {
                obstacleGrid[i][j] = 1 - obstacleGrid[i][j];
                if (obstacleGrid[i][j] > 0) { //not obstacke
                    if (i != 0 || j!= 0)
                        obstacleGrid[i][j] = ((i > 0) ? obstacleGrid[i-1][j] : 0) + ((j > 0) ? obstacleGrid[i][j-1] : 0);
                }
            }

        return obstacleGrid[ROW-1][COL-1];
    }

    /*
    Method 3: DP with O(n) space
     */

    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
    /*
    Dynamic Programming
    Every point in the grid has two approaches to reach, either from above or from left point. So a way to reach a given point is numofpath[i][j] = numofpath[i][j-1] + numofpath[i-1][j]
    However, consider that there is obstacles in this grid, we should let the numofpath of those points whose value is 1(obstacle) to be 0 since they cannot be passed.
    At the same time, since we don't need to reconstruct the path, we only need to keep track of the current row we are calculating. And based on the function above, we have to calculat from left to right and replace old values gradually.
    Then the last one of the array, after the whole loop ends, is what we need.
    O(m*n) time and O(n) space.
    */
        if (obstacleGrid == null || obstacleGrid.length == 0){
            return 0;
        }

        int l = obstacleGrid[0].length; //the number of grids in one row
        int h = obstacleGrid.length; // the number of rows.
        int[] numofpath = new int[l];

        //initialize the array. if there is only one row in the grid, then all points on the right side of the obstacle have 0 path to reach.
        //if there is only one point in the grid, we have to consider if it is a obstacle or not.
        numofpath[0] = obstacleGrid[0][0] == 1? 0 : 1;
        for (int i = 0; i < h; i++){
            // the first column of points can only have paths if this point is not an obstacle and its above rows have paths.
            numofpath[0] = (obstacleGrid[i][0] == 0) && (numofpath[0] != 0)? 1 : 0;
            for (int j = 1; j < l; j++){
                if (obstacleGrid[i][j] == 0){ //there is no obstacle on this grid
                    numofpath[j] += numofpath[j-1];
                }
                if(obstacleGrid[i][j] == 1){ //there is an obstacle on this grid
                    numofpath[j] = 0;
                }
            }
        }

        return numofpath[l-1];

    }

    /*
Method4 : 倒着推 //TODO
 */
    //add by tina,通过定义私有属性，赋值方式，避免了对数组传参
    // memo search
    private Integer[][] path; // Integer做判空比int要快很多
    private int[][] obstacleGrid;
    public int uniquePathsWithObstacles4(int[][] obstacleGrid) {
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
}
