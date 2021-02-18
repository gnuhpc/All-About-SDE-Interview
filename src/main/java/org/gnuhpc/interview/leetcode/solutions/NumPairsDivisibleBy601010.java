package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/6
 */
public class NumPairsDivisibleBy601010 {
    public int numPairsDivisibleBy60(int[] time) {
        int[] record = new int[60];
        int count = 0;
        for (int t : time) {
            t = t % 60;
            if (t != 0)
                count += record[60 - t];
            else
                count += record[t];  //加上其他位0的数
            record[t]++;
        }
        return count;
    }
}
