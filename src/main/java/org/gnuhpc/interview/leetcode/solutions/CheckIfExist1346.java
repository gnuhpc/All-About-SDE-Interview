package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/11/22
 */
public class CheckIfExist1346 {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> res = new HashSet<>();
        int zeroCnt = 0;
        for (int n : arr) {
            res.add(n);
            if (n == 0) zeroCnt++;
        }

        if (zeroCnt > 1) return true;

        for (int n : arr) {
            if (n != 0 && res.contains(n * 2)) return true;
        }

        return false;

    }
}
