package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.Arrays;

public class GetMoneyAmount375 {
    /*
    首先，我们需要意识到我们在范围 (1, n)(1,n) 中猜数字的时候，需要考虑最坏情况下的代价。也就是说要算每次都猜错的情况下的总体最大开销。

在暴力算法中，我们首先在 (1, n)(1,n) 中任意挑选一个数字，假设它是个错误的猜测（最坏情况），我们需要用最小代价去猜到需要的数字。那么在一次尝试以后，答案要么在我们猜的数字的左边要么在右边，为了考虑最坏情况，我们需要考虑两者的较大值。因此，如果我们选择 ii 作为第一次尝试，总体最小代价是：

\mathrm{cost}(1, n)=i + \max\big(\mathrm{cost}(1,i-1), \mathrm{cost}(i+1,n)\big)
cost(1,n)=i+max(cost(1,i−1),cost(i+1,n))

对于左右两段，我们分别考虑在段内选择一个数，并重复上面的过程来求得最小开销。

使用如上方法，我们能求得从 ii 开始猜，猜到答案的最小代价。同样地，我们遍历 (1, n)(1,n) 中的所有数字并分别作为第一次尝试，求出每一个的代价，并输入最小值即为答案。

作者：LeetCode
链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/solution/cai-shu-zi-da-xiao-ii-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    int[][] dp;

    public int solve(int l, int r) {
        if (l >= r) return 0;
        if (dp[l][r] != Integer.MAX_VALUE) return dp[l][r];
        for (int i = l; i <= r; i++) {
            dp[l][r] = Math.min(dp[l][r], i + Math.max(solve(l, i - 1), solve(i + 1, r)));
        }
        return dp[l][r];
    }

    public int getMoneyAmount(int n) {
        dp = new int[n + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return solve(1, n);
    }
}
