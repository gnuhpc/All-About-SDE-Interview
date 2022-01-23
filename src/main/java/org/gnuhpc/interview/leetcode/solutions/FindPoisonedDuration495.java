package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/7/12
 */
public class FindPoisonedDuration495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        //边界条件判断
        if (timeSeries.length == 0 || duration == 0)
            return 0;
        //res表示总的中毒持续时间
        int res = duration;
        for (int i = 1; i < timeSeries.length; i++) {
            //两次攻击的时间差和中毒持续的时间比较，选择小的
            res += Math.min(timeSeries[i] - timeSeries[i - 1], duration);
        }
        return res;
    }


    /*
    func findPoisonedDuration(timeSeries []int, duration int) int {
    if len(timeSeries) == 0 {
        return 0
    }

    if len(timeSeries) == 1 {
        return duration
    }

    return min(timeSeries[1] - timeSeries[0], duration) +
     findPoisonedDuration(timeSeries[1:], duration)
}

func min(a, b int) int {
    if a < b {
        return a
    }else {
        return b
    }
}

     */

}
