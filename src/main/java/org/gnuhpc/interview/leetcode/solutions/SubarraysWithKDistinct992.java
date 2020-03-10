package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class SubarraysWithKDistinct992 {
    public int subarraysWithKDistinct(int[] A, int K) {
        if (A == null || A.length == 0 || K == 0 || K > A.length) return 0;

        int left = 0;
        int right = 0;
        int gap = 1;
        int res = 0;
        int[] hash = new int[A.length + 1];
        int count = 0;

        while (right < A.length) {//移动右边界的条件
            hash[A[right]]++;
            if (hash[A[right]] == 1)
                count++;

            //移动右边界的时候考虑移动左边界
            while (count > K || hash[A[left]] > 1) {
                //说明left的值重复
                if (count > K) {
                    count--;
                    gap = 1;
                }
                if (hash[A[left]] > 1) {
                    gap++;
                }
                hash[A[left]]--;
                left++;
            }
            if (count == K) {
                res = res + gap;
            }

            right++;
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3));
    }
}
