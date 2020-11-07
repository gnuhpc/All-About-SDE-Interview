package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

public class FloodFill0810 {
    int[][] dr = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
    boolean[][] visited;
    int oldColor;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        visited = new boolean[image.length][image[0].length];
        oldColor = image[sr][sc];

        dfs(image,sr,sc,newColor);

        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int newColor) {
        image[sr][sc] = newColor;
        visited[sr][sc] = true;

        for(int[] d: dr){
            int newX = sr + d[0];
            int newY = sc + d[1];
            if(isValid(newX,newY,image)){
                visited[newX][newY] = true;
                dfs(image, newX, newY, newColor);
            }
        }
    }

    private boolean isValid(int x, int y, int[][] image){
        return x>=0 && x< image.length && y>=0 && y<image[0].length && !visited[x][y]
                && image[x][y]==oldColor;
    }

    @Test
    public void test(){
        Utils.print2DArray(floodFill(
                new int[][]{{0,0,0},{0,1,1}},
                1,1,1));
    }
}
