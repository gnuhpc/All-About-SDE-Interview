package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/6/3
 */
//LTE
public class New21Game837 {
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[K + W];
        double sum = 0;//用來計算 dp[K-1] 的變數
        for (int ix = K; ix < K + W; ix++) {// 先處理最後一輪的獲勝機率
            dp[ix] = ix <= N ? 1.0 : 0.0;// 小於等於N為1.0、大於N為0.0
            sum += dp[ix];// sum=dp[K]+dp[K+1]+...+dp[K+W-1]
        }
        //  推導 dp[K-1]
        if (K - 1 >= 0 && W > 0) dp[K - 1] = sum / W;
        for (int ix = K - 2; ix >= 0; ix--) {// 從 K-2 開始，由後向前推導出所有的值
            dp[ix] = dp[ix + 1] - (1.0 / W) * (dp[ix + 1 + W] - dp[ix + 1]);
        }
        return dp[0];
    }

    //独立事件 概率 每层概率单独算 依次加 每一层的样本空间不一样
    public double new21Game1(int N, int K, int W) {
        if (K < 1) return 1;
        return rate(N, K, W, 0, 1);
    }

    public double rate(double N, double K, double W, double point, double preRate) {

        double successRate = 0;
        double nextRate = 0;
        for (double i = 1; i <= W; i++) {
            if ((point + i) < K) {
                //继续下一轮
                nextRate += rate(N, K, W, point + i, (1 / W) * preRate);
            } else {
                if ((point + i) <= N) {
                    successRate = successRate + preRate * (1 / W);
                }
            }
        }
        return successRate + nextRate;
    }
}
