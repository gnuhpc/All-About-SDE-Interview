package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/3/10
 */
public class ShipWithinDays1011 {
    public int shipWithinDays(int[] weights, int D) {
        int[] prepare = prepare(weights);
        //Possible Answer between [Max(average, maxweight), sum]
        return binarySearch(weights, D, Math.max(prepare[1] / D, prepare[0]), prepare[1]);
    }

    //0 - max, 1 - sum
    private int[] prepare(int[] weights) {
        int[] res = new int[2];
        for (int w : weights) {
            if (w > res[0]) res[0] = w;
            res[1] += w;
        }

        return res;
    }

    private int binarySearch(int[] weights, int D, int start, int end) {
        if (start > end) { //search space exhausted
            return -1;
        }

        int mid = (start + end) / 2;
        if (!isEnough(weights, D, mid)) {
            return binarySearch(weights, D, mid + 1, end);
        } else {//mid成立,尝试再往左边找,如果成功则返回更小的,否则就是mid了
            int ret = binarySearch(weights, D, start, mid - 1);
            if (ret == -1) return mid;
            else {
                return ret;
            }
        }
    }

    private boolean isEnough(int[] weights, int D, int weightLimit) {
        int days = 1;
        int currentWeight = 0;
        for (int weight : weights) {
            if (currentWeight + weight <= weightLimit) {
                currentWeight += weight;
            } else {
                currentWeight = weight;
                days++;
                if (days > D) {
                    return false;
                }
            }
        }
        return true;
    }

}
