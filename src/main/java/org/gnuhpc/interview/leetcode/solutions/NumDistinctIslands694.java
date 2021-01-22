package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/12/26
 */
public class NumDistinctIslands694 {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        Set<String> islands = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    // 建立一个StringBuilder作为当前跟踪的island的所有相对坐标
                    StringBuilder island = new StringBuilder();
                    dfs(grid, island, i, j, i, j);
                    islands.add(island.toString());
                }
            }
        }
        return islands.size();
    }

    // origin_i，origin_j为相对原点坐标
    private void dfs(int[][] grid, StringBuilder island, int i, int j, int origin_i, int origin_j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        // 一旦遍历到有效的点后，将相对的横坐标和纵坐标加入StringBuilder
        island.append(i - origin_i);
        island.append(j - origin_j);
        dfs(grid, island, i + 1, j, origin_i, origin_j);
        dfs(grid, island, i - 1, j, origin_i, origin_j);
        dfs(grid, island, i, j + 1, origin_i, origin_j);
        dfs(grid, island, i, j - 1, origin_i, origin_j);
    }
}
