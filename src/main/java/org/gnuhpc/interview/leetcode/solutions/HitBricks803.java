package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/16
 */
public class HitBricks803 {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        for (int[] hit : hits) {
            grid[hit[0]][hit[1]]--;
        }

        for (int i = 0; i < grid[0].length; i++) {
            dfs(0, i, grid);
        }

        int[] ans = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            grid[hits[i][0]][hits[i][1]]++;
            if (grid[hits[i][0]][hits[i][1]] == 1 && isConnectProof(hits[i][0], hits[i][1], grid)) {
                ans[i] = dfs(hits[i][0], hits[i][1], grid) - 1;
            }
        }
        return ans;
    }

    private int dfs(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) {
            return 0;
        }

        grid[x][y] = 2;
        return dfs(x - 1, y, grid) + dfs(x + 1, y, grid) + dfs(x, y - 1, grid) + dfs(x, y + 1, grid) + 1;
    }

    private final int[] dx = {0, 0, 1, -1};
    private final int[] dy = {1, -1, 0, 0};

    private boolean isConnectProof(int x, int y, int[][] grid) {
        if (x == 0) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] != 2) {
                continue;
            }
            return true;
        }
        return false;
    }

    /*
    作者：mufanlee
    https://leetcode-cn.com/problems/bricks-falling-when-hit/solution/hen-you-qu-de-da-zhuan-kuai-by-muyids/
    链接：https://leetcode-cn.com/problems/bricks-falling-when-hit/solution/803-da-zhuan-kuai-ni-xiang-si-wei-bing-c-ojdu/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
