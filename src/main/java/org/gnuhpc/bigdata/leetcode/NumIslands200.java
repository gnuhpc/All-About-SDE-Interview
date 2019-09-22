package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.set.unionfindset.QuickUnion;
import org.junit.Test;

import java.util.*;

//很典型，棋盘类的问题本质就是一个图问题. 方法一和方法六推荐
public class NumIslands200 {

    /*
        Method1: dfs recursivly calling，也是发现是1就向四个方向沉没
        在原数组上标记为x TODO: 二维DFS重点
     */
    /*
    Flood Fill
    Flood-Fill (node, target_color, replacement_color) do
    1. If the color of node is not equal to target_color,
    then return
    2. Set the color of node to replacement_color
    3. Perform Flood-Fill (step to the west of node ...)
    Perform Flood-Fill (step to the east of node ...)
    Perform Flood-Fill (step to the north of node ...)
    Perform Flood-Fill (step to the south of node ...)
    4. Return
    end
     */
    public static int numIslands(char[][] grid) {
        int result = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfsMark(r, c, grid);
                    result++;
                }
            }
        }

        return result;
    }

    private static void dfsMark(int r, int c, char[][] grid) {
        if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == '1') {
            grid[r][c] = 'x';
            dfsMark(r + 1, c, grid);
            dfsMark(r - 1, c, grid);
            dfsMark(r, c + 1, grid);
            dfsMark(r, c - 1, grid);
        }
    }

    /*
    Method2: dfs Iterative
     */
    public int numIslands2(char[][] grid) {
        int[] dx={-1, 1, 0, 0};
        int[] dy={0, 0, -1, 1};
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new LinkedList<>();
        int res = 0;  // final ans stored in circles
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++) {
                String key = String.valueOf(i) + '_' +String.valueOf(j);
                if(grid[i][j]=='1' && !visited.contains(key)){
                    res++;  // // person i unvisited so start new friend circle

                    // dfs magic : {
                    // push to stack -
                    // pop top -
                    // retrieve neighbours -
                    // repeat first 3 steps for each unvisited neighbour
                    // until stack empty }
                    stack.push(key);

                    while(!stack.isEmpty()){
                        String current = stack.pop();
                        visited.add(current);        // mark node visited

                        int cx = Integer.valueOf(current.split("_")[0]);
                        int cy = Integer.valueOf(current.split("_")[1]);

                        // retrieve it's unvisited neighbours and push them to stack
                        for(int k = 0; k < 4; k++){
                            int x = cx + dx[k];
                            int y = cy + dy[k];
                            String nextKey = String.valueOf(x) + '_' + String.valueOf(y);
                            if(!visited.contains(nextKey)
                                    && x<grid.length && x>=0
                                    && y<grid[0].length && y>=0
                                    && grid[x][y] == '1'){
                                if (!stack.contains(nextKey))
                                stack.push(nextKey);
                            }
                        }
                    }
                }
            }
        }

        return res;


    }

    /*
    Method 3 Union-Find -- QuickUnion //TODO 二维数组并差集标准用法
     */

    public int numIslands3(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int[] dx={-1, 1, 0, 0};
        int[] dy={0, 0, -1, 1};


        QuickUnion qu = new QuickUnion(m*n);
        int count=0;//这也是岛屿的最大个数
        //假设这些点都不相连的情况下，count算出来是多少
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]=='1'){
                    count++;
                }
            }
        }

        //再遍历一遍
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]=='1'){
                    //四个方向进行操作
                    for(int k=0; k<4; k++){
                        int x = i+dx[k];
                        int y = j+dy[k];

                        if(x>=0&&x<m&&y>=0&&y<n&&grid[x][y]=='1'){
                            if (!qu.connected(i*n+j,x*n+y)){
                                //合并
                                qu.union(i*n+j,x*n+y);
                                //总数减1
                                count--;
                            }
                        }
                    }
                }
            }
        }

        return count;
    }


    /*
    Method 5: inplace dfs
     */
    class Point {
        int x;
        int y;

        Point(int a, int b) { x = a; y = b; }
    }

    private final Point directions[] = {new Point(0, 1), new Point(0, -1), new Point(1, 0), new Point(-1, 0)};
    public int numIslands5(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int res = 0, n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && grid[i][j] != 'v') {
                    res++;
                    DFS(grid, i, j);
                }
            }
        }
        return res;
    }


    void DFS(char[][] grid, int i, int j) {// Set 'v' to visited node in grid
        Deque<Point> stack = new LinkedList<>();

        stack.add(new Point(i, j));
        while (!stack.isEmpty()) {
            Point node = stack.pop();
            int nodex = node.x, nodey = node.y;
            if (nodex < 0 || nodex > grid.length - 1 || nodey < 0 || nodey > grid[0].length - 1) continue;
            if (grid[nodex][nodey] == 'v' || grid[nodex][nodey] != '1') continue;

            grid[nodex][nodey] = 'v';
            for (Point direction : directions) {
                stack.push(new Point(nodex + direction.x, nodey + direction.y));
            }
        }
    }



    /*
    Method 6: BFS ,发现是1的不断让这个岛沉没(改为0)，一层层的沉没 , 这个方法比DFS好，DFS可能Stack Overflow
    TODO 种子填充
     */

    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public int numIslands6(char[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int islands = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    islands++;
                    BFS(grid,i,j);
                }
            }
        }
        return islands;
    }
    private void BFS(char[][] grid, int x, int y){
        grid[x][y] = '0';
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int[] dir:dirs){ //相当于getNeighbourList了
                int x1 = p.x+dir[0];
                int y1 = p.y+dir[1];
                if(x1>=0 && y1>=0 && x1< grid.length && y1<grid[0].length && grid[x1][y1]=='1'){
                    grid[x1][y1] = '0';
                    q.offer(new Point(x1,y1));
                }
            }
        }
    }

    @Test
    public void test(){
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        System.out.println(numIslands(grid));

//        grid = new char[][]{
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        };
//        System.out.println(numIslands2(grid));

        grid = new char[][]{
                {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0','0','0','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
        };
        System.out.println(numIslands2(grid));
    }

    // add by tina
    private int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};
    private int m, n;

    public int numIslands7(char[][] grid) {
        m = grid.length;
        if(m == 0) return 0;
        n = grid[0].length;
        int res = 0;

        boolean[][] used = new boolean[m][n];
        for(int i = 0; i< m; i++)
            for(int j = 0; j<n;j++){
                if(grid[i][j] == '1' && !used[i][j]){
                    //System.out.println("i = " +i + ",j=" +j +  "," +used[i][j]);
                    res++;
                    dfs(grid,i,j,used);
                }
            }

        return res;
    }

    /**
     * 此递归看起来没有结束条件，
     * 是因为我们在决定是否进行下一轮递归时，做了条件判断
     * 相对而言，第一种解法看起来更加简洁
     * @param grid
     * @param startx
     * @param starty
     * @param used
     */
    public void dfs(char[][] grid, int startx,int starty,boolean[][] used){
        used[startx][starty] = true;
        for(int i = 0; i<4;i++){
            int newx = startx + d[i][0];
            int newy = starty + d[i][1];
            if(isInArea(newx,newy) && grid[newx][newy] == '1' && !used[newx][newy])
                dfs(grid,newx,newy,used);
        }
        return;
    }

    private boolean isInArea(int i ,int j){
        return i >= 0 && i< m && j >= 0 && j<n;
    }

}
