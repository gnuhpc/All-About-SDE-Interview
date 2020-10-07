package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;
import java.util.Comparator;

public class MaxEnvelopes354 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        //定义一个比较器，以宽度进行升序排序
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];//宽度相等的话，就以长度肾虚（升序）排序
            }
            return o1[0] - o2[0];
        });
        int n = envelopes.length;
        int[] dp = new int[n];//表示第i个信封能够套进去的最大信封个数，其实初始化都为1，
        int i, j, res = 0;//记录最大值
        for (i = 0; i < n; i++) {
            dp[i] = 1;//自己单独一个就是一个结果，谁也没有套，初始化就为1
            for (j = 0; j < i; j++) {//遍历前面所有可能的套进去的情况
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {//需要严格小于，不能有等号
                    dp[i] = Math.max(dp[i], dp[j] + 1);//dp[j]+1就是可以套进去第j个信封，加上自己本身的信封，
                }
            }
            res = Math.max(res, dp[i]);//记录结果最值
        }
        return res;
    }
}
