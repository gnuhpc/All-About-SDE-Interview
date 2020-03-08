package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.datastructure.unionfind.QuickUnion;
import org.gnuhpc.bigdata.datastructure.unionfind.UnionFind;
import org.gnuhpc.bigdata.leetcode.utils.Point;
import org.junit.Test;

import java.util.*;

public class NumIslands200 {
    /*
        Method1: dfs recursivly calling，也是发现是1就向四个方向沉没
        也可以在在原数组上标记x而不用visited数组 二维DFS重点
     */

    private int[][] dr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int     r, c;
    private boolean[][] visited;

    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        r = grid.length;
        if (r == 0) return 0;
        c = grid[0].length;
        int res = 0;

        visited = new boolean[r][c];
        //Step 1： 遍历所有节点
        for (int x = 0; x < r; x++)
            for (int y = 0; y < c; y++) {
                if (grid[x][y] == '1' && !visited[x][y]) {// Step2 ：判断是否可以开始dfs
                    res++;// Step3: 有必要的时候进行计数
                    dfs(grid, x, y, visited);
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
    public void dfs(char[][] grid, int x, int y, boolean[][] visited) {
        //DFS Step1： 标准访问过
        visited[x][y] = true;
        //DFS Step2: 向四个方向进行
        for (int[] d : dr) {
            //DFS Step3: 得到新坐标
            int newX = x + d[0];
            int newY = y + d[1];

            //DFS Step4： 判断是不是可以进一步 DFS， 判断条件和外边相比添加要确定是不是valid坐标
            if (isValid(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY])
                dfs(grid, newX, newY, visited);
        }
        return;
    }

    /*
    Method 2 Union-Find -- QuickUnion //二维数组并差集标准用法
     */

    public int numIslands2(char[][] grid) {
        if (grid == null) return 0;
        r = grid.length;
        if (r == 0) return 0;
        c = grid[0].length;

        visited = new boolean[r][c];
        int res = 0;//这也是岛屿的最大个数

        UnionFind uf = new UnionFind(r * c);
        //假设这些点都不相连的情况下，count算出来是多少
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1') {
                    res++;
                }
            }
        }

        //再遍历一遍
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1') {
                    //四个方向进行操作
                    for (int[] d : dr) {
                        int x = i + d[0];
                        int y = j + d[1];

                        if (x >= 0 && x < r && y >= 0 && y < c && grid[x][y] == '1') {
                            if (!uf.isConnected(i * c + j, x * c + y)) {
                                //合并
                                uf.union(i * c + j, x * c + y);
                                res--;
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    /*
    Method 3: BFS ,这个方法比DFS好，DFS可能Stack Overflow
    //二维BFS标准解法
     */
    public int numIslands3(char[][] grid) {
        if (grid == null) return 0;
        r = grid.length;
        if (r == 0) return 0;
        c = grid[0].length;
        int res = 0;

        visited = new boolean[r][c];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == '1' && !visited[x][y]) {
                    res++;
                    bfs(grid, x, y);
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int x, int y) {
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int[] dir : dr) { //相当于getNeighbourList了
                int newX = p[0] + dir[0];
                int newY = p[1] + dir[1];
                if (isValid(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    q.offer(new int[]{newX, newY});
                }
            }
        }
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }

    @Test
    public void test() {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '1'}
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
                {
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '0',
                        '0',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '0',
                        '0',
                        '0',
                        '0',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1',
                        '1'},
                };
        System.out.println(numIslands2(grid));
    }
}
