package org.gnuhpc.interview.leetcode.solutions;

public class Fib509 {
    public int fib(int N) {
        int[] dp = new int[N + 1];
        if (N == 0) return 0;
        if (N == 1) return 1;
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    /*
    func fib(n int) int {
	a, b := 0, 1
	for ; n > 0; n-- {
        //注意这儿需要防止越界
		a, b = (a+b)%1000000007, a
	}
	return a
}

     */
}
