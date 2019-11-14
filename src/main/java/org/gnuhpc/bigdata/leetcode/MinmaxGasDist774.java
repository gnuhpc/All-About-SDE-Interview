package org.gnuhpc.bigdata.leetcode;

public class MinmaxGasDist774 {
    public double minmaxGasDist(int[] stations, int K) {
        // 题目中给了数字的范围[0, 10^8]，那么二分查找的左右边界值就有了
        double start = 0;
        double end = 1e8;

        // 又给了误差范围10^-6，那么只要right和left差值大于这个阈值，就继续循环。
        while (end - start > 1e-6) {
            // 折半计算出来的mid就是一个candidate，我们要去验证个candidate是否符合题意。
            double mid = start + (end - start) / 2;
            if (helper(stations, K, mid)) { //smallest，所以可以的时候就往下的猜
                end = mid;
            } else {
                start = mid;
            }
        }

        return start;
    }

    /**
     * 验证的方法其实也不难，我们计算每两个加油站之间的距离，
     * 如果此距离大于candidate，则计数器累加1，
     * 如果大于candidate距离的个数小于等于k，则说明我们的candidate偏大了，那么right赋值为mid；
     * 反之若大于candidate距离的个数大于k，则说明我们的candidate偏小了，那么left赋值为mid。
     * 最后left和right都会收敛为所要求的最小的任意两个加油站间的最大距离 *
     *
     * @apiNote
     * @param stations
     * @param K
     * @param candidate
     * @return
     */
    private boolean helper(int[] stations, int K, double candidate) {
        int cnt = 0, n = stations.length;
        for (int i = 0; i < n - 1; ++i) {
            // 如果我能用k个加油站把他分割到mid，从左到右，超过mid就加一个加油站
            cnt += (stations[i + 1] - stations[i]) / candidate;
        }
        return cnt <= K;
    }

}
