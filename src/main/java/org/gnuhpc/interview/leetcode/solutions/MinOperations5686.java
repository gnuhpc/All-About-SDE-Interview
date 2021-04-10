package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2021/2/21
 */
public class MinOperations5686 {
    public int[] minOperations(String boxes) {
        int[] res = new int[boxes.length()];
        char[] arr = boxes.toCharArray();

        Set<Integer> posOne = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '1') posOne.add(i);
        }
        int oneCnt = posOne.size();
        for (int i = 0; i < res.length; i++) {
            int tmp = 0;
            for (int pos : posOne) {
                tmp += Math.abs(pos - i);
            }
            res[i] = tmp;
        }
        return res;
    }
}
