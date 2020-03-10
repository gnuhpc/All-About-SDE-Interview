package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class MyPow50 {
    public double myPow(double x, int n) {
        int sign = 1;
        if (n < 0) {
            sign = -1;
            n = -n;
        }
        return sign < 0 ? 1 / pow(x, n) : pow(x, n);
    }

    private double pow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0) {
            return pow(x * x, n / 2);
        } else {
            return pow(x * x, n / 2) * x;
        }
    }
}
