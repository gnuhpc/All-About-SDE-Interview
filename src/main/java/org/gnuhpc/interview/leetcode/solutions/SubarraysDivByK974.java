package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/5/27
 */
public class SubarraysDivByK974 {
    public int subarraysDivByK(int[] A, int K) {
        int N = A.length, sum = 0, ans = 0;
        int[] map = new int[K];
        map[0] = 1;
        for (int i = 1; i <= N; i++) {
            sum = sum + A[i - 1];
            int key = sum % K < 0 ? K + (sum % K) : sum % K;
            ans += map[key];
            map[key]++;
        }
        return ans;
    }
}
