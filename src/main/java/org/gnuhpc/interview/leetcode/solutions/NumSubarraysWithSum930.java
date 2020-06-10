package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/6/2
 */

//560的特殊情况，560是通解
public class NumSubarraysWithSum930 {

    public int numSubarraysWithSum(int[] A, int S) {
        int[] presum = new int[A.length + 1];

        presum[0] = 0;

        for (int i = 1; i <= A.length; i++) {
            presum[i] = presum[i - 1] + A[i - 1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        //Key是下一个要配对的数值，Value是只要配对成功就加上的次数
        for (int a : presum) {
            res += map.getOrDefault(a, 0);
            map.put(a + S, map.getOrDefault(a + S, 0) + 1);
        }

        return res;

    }


    // 和上边那个写法本质一样，建立一个presum->count的map， 每次只要看presum-k有几个就好。
    //要注意的细节就是要把presumMap.put(0, 1)加上，就是0始终有一个， 相当于presum==S的时候，count要加1
    public int numSubarraysWithSum2(int[] A, int S) {
        Map<Integer, Integer> presumMap = new HashMap<>();
        int sum = 0;
        int totalCount = 0;
        presumMap.put(0, 1);
        for (int i = 0; i < A.length; ++i) {
            sum += A[i];
            totalCount += presumMap.getOrDefault(sum - S, 0);
            presumMap.put(sum, presumMap.getOrDefault(sum, 0) + 1);
        }
        return totalCount;
    }
}
