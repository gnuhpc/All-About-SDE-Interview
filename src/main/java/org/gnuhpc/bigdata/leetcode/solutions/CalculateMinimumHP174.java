package org.gnuhpc.bigdata.leetcode.solutions;

public class CalculateMinimumHP174 {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) {
            return 0;
        }
        int row = dungeon.length;
        int col = dungeon[0].length;
        // dp[i][j],代表当前格子走到右下角需要的最低血量，依赖后面的路径，与前面的路径无关
        int[][] dp = new int[row][col];
        // 边界条件，dungeon[row - 1][col - 1] > 0 说明本格不掉血，只需有最低的1滴血就可以
        dp[row - 1][col - 1] = dungeon[row - 1][col - 1] > 0 ? 1 : -dungeon[row - 1][col - 1] + 1;
        // 边界条件，最下面的一行，dungeon[row - 1][i] >= dp[row - 1][i + 1] ，本格子的数目大于等于下一步需要的血滴数，说明本格子自身数目就可以补充后面路程的消耗，故也只
        // 需要1滴血就可以，否则，就需要系统补血了，补血值等于后面路径的消耗即加上当前格子所需要补充的血量
        for (int i = col - 2; i >= 0; i--) {
            dp[row - 1][i] = dungeon[row - 1][i] >= dp[row - 1][i + 1] ? 1 : dp[row - 1][i + 1] - dungeon[row - 1][i];
        }
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = dungeon[i][col - 1] >= dp[i + 1][col - 1] ? 1 : dp[i + 1][col - 1] - dungeon[i][col - 1];
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                // 找出右面和下面格子中所需血量的较小者，与当前格子血量比较，比较是否需要系统补血，
                dp[i][j] = (dungeon[i][j] >= Math.min(dp[i][j + 1], dp[i + 1][j])) ? 1
                                                                                   :
                           Math.min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i][j];
            }
        }
        return dp[0][0];
    }
}
