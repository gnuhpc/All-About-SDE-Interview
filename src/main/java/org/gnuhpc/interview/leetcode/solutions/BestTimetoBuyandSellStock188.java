package org.gnuhpc.interview.leetcode.solutions;

public class BestTimetoBuyandSellStock188 {
    // dp

    /**
     * 假设一共有 n 天, 那么这 n 天最多能够完成 n / 2 比交易, 也就是说, 当 k * 2 >= n 时, 就变成了 买卖股票的最佳时机 II, 反之, 我们可以作为动态规划问题解决:
     * <p>
     * 定义:
     * <p>
     * globalbest[i][j] 表示前i天，至多进行j次交易时的最大获益
     * mustsell[i][j] 表示前i天，至多进行j次交易，并且第i天卖出手中的股票时的最大获益
     * 状态转移:
     * <p>
     * mustsell[i][j] = max(globalbest[i - 1][j - 1], mustsell[i - 1][j]) + prices[i] - prices[i - 1]
     * globalbest[i][j] = max(globalbest[i - 1][j], mustsell[i][j])
     * 边界: mustsell[0][i] = globalbest[0][i] = 0
     * <p>
     * 优化: 滚动数组优化两个状态的空间至一维数组.
     *
     * @param K
     * @param P
     * @return
     */
    public int maxProfit(int K, int[] P) {
        int n = P.length;
        if (n == 0) {
            return 0;
        }

        int i, j, k;
        if (K > n / 2) {
            // best time to buy and sell stock ii
            int tmp = 0;
            for (i = 0; i < n - 1; ++i) {
                tmp += Math.max(0, P[i + 1] - P[i]);
            }

            return tmp;
        }

        int[][] f = new int[n + 1][2 * K + 1 + 1];
        for (k = 1; k <= 2 * K + 1; ++k) {
            f[0][k] = Integer.MIN_VALUE; // impossible
        }

        f[0][1] = 0;
        for (i = 1; i <= n; ++i) {
            // 阶段1, 3, .. 2 * K + 1: f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}
            for (j = 1; j <= 2 * K + 1; j += 2) {
                f[i][j] = f[i - 1][j];
                if (j > 1 && i > 1 && f[i - 1][j - 1] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + P[i - 1] - P[i - 2]);
                }
            }

            // 阶段2, 4.., 2K: f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1]}
            for (j = 2; j <= 2 * K + 1; j += 2) {
                f[i][j] = f[i - 1][j - 1];
                if (i > 1 && f[i - 1][j] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + P[i - 1] - P[i - 2]);
                }
            }
        }

        int res = 0;
        for (j = 1; j <= 2 * K + 1; j += 2) {
            res = Math.max(res, f[n][j]);
        }

        return res;
    }

}
