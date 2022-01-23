package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/5/18
 */
public class CountTriplets1442 {
    public int countTriplets(int[] arr) {

        int[] preXOR = new int[arr.length + 1];
        preXOR[0] = 0;

        // 先算出异或前缀和
        // 定义preXOR[0] = 0
        for (int i = 1; i <= arr.length; i++) {
            preXOR[i] = preXOR[i - 1] ^ arr[i - 1];
        }

        int count = 0;

        // 对于符合条件的triplet (i,k,j)
        // j,k都必须比i大，所以对于每个i,
        // j和k都从i+1开始遍历
        // 对于确定的i和j，k的范围是i+1到j
        // 所以有j-i-1种可能
        for (int i = 0; i <= arr.length - 1; i++) {
            for (int j = i + 1; j <= arr.length; j++) {
                // i,j中间的异或值为0
                if (preXOR[j] == preXOR[i]) {
                    count += j - i - 1;
                }
            }
        }

        return count;
    }
}
