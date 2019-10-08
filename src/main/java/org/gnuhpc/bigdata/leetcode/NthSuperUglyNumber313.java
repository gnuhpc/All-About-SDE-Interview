package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 2019/10/1
 */
public class NthSuperUglyNumber313 {
    //https://leetcode.com/problems/super-ugly-number/discuss/76292/Java-solution21ms
    /*
    Keep adding minimum values to results and
    updating the time value for the chosen prime number in each loop.
    这个times的意思是乘到了uglyNumber中的第几个数
比如说，我们目前已经有了一串uglyNumber，对于下一个uglyNumber，肯定是由其中一个primes乘上已知的一个uglyNumber得到，那么如何找到这个数呢？一个办法是用所有的primes乘一遍所有的uglyNumber，但这样的时间复杂度比较高
我们来看times，一个prime对应的times[min_times]表示乘到了uglyNumber中的第几个数，对于times[min_times]之前的数，已经乘完了存在于uglyNumber中，times[min_times]之后的数，是比times[min_times]对应的数更大，
所以我们只需要考虑times[min_times]这个数即可，因此我们只需要统计每一个prime对应已经乘到了第几位，然后就可以达到O(nlog(k))了
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] times = new int[primes.length];
        int[] result = new int[n];
        result[0] = 1; // first is 1

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, primes[j] * result[times[j]]);
            }

            result[i] = min;

            for (int j = 0; j < times.length; j++) {
                if (primes[j] * result[times[j]] == min) {
                    times[j]++;
                }
            }
        }

        return result[n - 1];
    }

    @Test
    public void test() {
        System.out.println(nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
    }

}
