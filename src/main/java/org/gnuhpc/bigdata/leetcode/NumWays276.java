package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 2019/10/25
 */
public class NumWays276 {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (n == 1) return k;
        // same[i] : 第i个post和第i-1个post颜色相同
        int[] same = new int[n];
        // diff[i] : 第i个post和第i-1个post颜色不同
        int[] diff = new int[n];
        same[0] = same[1] = k;
        diff[0] = k;
        diff[1] = k * (k - 1);
        /*
        same:表示前两个颜色一样的的涂色数
        diff:表示前两个颜色不一样的涂色数
        注意在这个时刻，第二个格子的涂色总数为此刻same + diff的值
        要得到第三个格子(dp[3]，表示前三个格子的涂色数，下同)的涂色数，需要新的same'和diff',第三个格子的涂色数为same'+diff',和第二个格子的涂色数计算方法如出一辙
        此时此刻，面临着两种情况：

        前两个涂色为same的情况，第三个格子只能选择一个新的颜色，故第三个格子有same * (k-1)种涂色数，这样第二个和第三个格子的数必定不同，记为diff' = same * (k-1)

        前两个涂色为diff的情况，第三个格子有两种选择：

        第三个格子不与第二个格子颜色相同，故这种情况第三个格子有diff * (k - 1)种涂色数，这样第二个和第三个格子的数不同，记为diff'' = diff * (k - 1)

        第三个格子与第二个格子颜色相同，故这种情况第三个格子有diff * 1种涂色数，这样第二个和第三个格子的数相同，记为same' = diff * 1
        这样我们就得到了新的same' = diff * 1, 新的diff' = diff' + diff'' = (same + diff) * (k - 1)
         */
        for (int i = 2; i < n; ++i) {
            same[i] = diff[i-1];
            diff[i] = (k - 1) * same[i-1] + (k - 1) * diff[i-1];
        }
        return same[n-1] + diff[n-1];
    }
}
