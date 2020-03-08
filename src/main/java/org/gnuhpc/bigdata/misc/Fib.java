package org.gnuhpc.bigdata.misc;

import java.util.Scanner;

public class Fib {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        if (n < 3) System.out.println("1");
        System.out.println(fib(n));
    }

    private static long fib(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
        }

        return dp[n];
    }
}
