package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Copyright gnuhpc 2020/9/1
 */
public class SumSubarrayMins907 {
    /*
找出数组中的最小值（min）的索引（i），i左侧有m个元素，右侧有n个元素;
所以包含i的子数组一共有 (m+1)*(n+1)个;
这些子数组的最小值的和就是 min*(m+1)*(n+1)；

  */
    public int sumSubarrayMins(int[] A) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = A.length, res = 0, mod = (int) 1e9 + 7;
        for (int i = 0; i <= n; i++) {
            int cur = (i == n) ? 0 : A[i];
            while (!stack.isEmpty() && A[stack.peek()] > cur) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                res = (res + A[j] * (i - j) * (j - k)) % mod;
            }
            stack.push(i);
        }
        return res;
    }
}
