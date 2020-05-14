package org.gnuhpc.interview.leetcode.solutions;

public class MyPow50 {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        double half = myPow(x, n / 2);
        double rest = myPow(x, n % 2);
        return rest * half * half;
    }

    public double myPow2(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) {
            n = -n;
            return 1 / pow(x, n);
        } else {
            return pow(x, n);
        }
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
