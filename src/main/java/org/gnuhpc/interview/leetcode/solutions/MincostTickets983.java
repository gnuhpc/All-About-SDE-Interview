package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/5/6
 */
public class MincostTickets983 {
    public int mincostTickets(int[] days, int[] costs) {
        //判断
        if (days == null || days.length == 0 ||
                costs == null || costs.length == 0) {
            return 0;
        }

        //dp表示到了当天花的最低票价
        int[] dp = new int[days[days.length - 1] + 1];

        //base case: 第0天一定不用买票 则花费0元
        dp[0] = 0;
        //标记一下需要买票的日子
        for (int day : days) {
            dp[day] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < dp.length; i++) {
            //不需要买票
            if (dp[i] == 0) {
                //不需要买票花费的钱就是前一天的花费
                dp[i] = dp[i - 1];
                continue;
            }

            int n1 = dp[i - 1] + costs[0];//当天需要买票
            /**如果今天距离第一天已经超过7天了
             * 则花费: dp[i-7](7天前已经花费的钱)+cost[1](7天前买了一张7天的票)
             * 否则就是直接第一天买了一张7天票
             */
            int n2 = i > 7 ? dp[i - 7] + costs[1] : costs[1];
            //30天与7天 同理
            int n3 = i > 30 ? dp[i - 30] + costs[2] : costs[2];

            dp[i] = Math.min(n1, Math.min(n2, n3));
        }
        //最后一天花费多少钱
        return dp[days[days.length - 1]];
    }
}
