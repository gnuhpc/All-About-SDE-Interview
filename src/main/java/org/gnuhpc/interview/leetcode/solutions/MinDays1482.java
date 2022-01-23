package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/5/9
 */
public class MinDays1482 {
    public int minDays(int[] bloomDay, int m, int k) {
        int l = bloomDay.length;
        if (l < m * k) {
            return -1;
        }

        int min = Integer.MAX_VALUE, max = 0;
        for (int i = 0; i < l; i++) {
            min = Math.min(min, bloomDay[i]);
            max = Math.max(max, bloomDay[i]);
        }
        int left = min, right = max;
        while (left < right) {
            int mid = (left + right) / 2;
            //t 是每次连续的开放的花朵数目
            //j 是遍历变量
            //res 是记录的总的花束
            int res = 0, j = 0, t = 0;
            while (j < l) {
                if (bloomDay[j] <= mid) {
                    t++;
                } else {
                    //如果大于，就要判断前面可以形成多少花束
                    res += t / k;
                    t = 0;
                }
                j++;
                //到最后一个数字了
                if (j == l) {
                    res += t / k;
                    break;
                }
            }
            if (res >= m) {//天数给多了；
                right = mid;
            } else {//天数给少了
                left = mid + 1;
            }

        }
        return left;
    }
/*
作者：xue-you-yong-de-ben-mao
链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/solution/1482-zhi-zuo-m-shu-hua-suo-xu-de-zui-sha-3sx6/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
