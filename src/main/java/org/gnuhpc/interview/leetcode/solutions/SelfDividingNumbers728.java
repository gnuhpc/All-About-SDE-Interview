package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/3/12
 */
public class SelfDividingNumbers728 {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int num = left; num <= right; num++) {
            if (isDividingNumber(num)) {
                res.add(num);
            }
        }
        return res;
    }

    private boolean isDividingNumber(int num) {
        if (num == 0) return false;
        int n, number = num;
        while (number != 0) {
            n = number % 10;
            if (n == 0 || num % n != 0) return false;
            number /= 10;
        }
        return true;
    }
}
