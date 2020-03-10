package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/3/5
 */
public class KnightDialer935 {
    private long mod = 1000000007;
    private int[][] dr = new int[][]{
            {1, 2}, {-1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}
    };

    public int knightDialer(int N) {
        long[][][] mem = new long[4][3][N];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < N; k++) {
                    mem[i][j][k] = -1;
                }
            }
        }
        long ans = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                //有两个位置不需要考虑
                if (i == 3 && j == 0 || i == 3 && j == 2) {
                    continue;
                }
                ans += processing(i, j, N - 1, mem);
            }
        }
        return (int) (ans % mod);
    }

    public long processing(int i, int j, int move, long[][][] mem) {
        if (move == 0) {//可以这么思考，当move为0的时候代表一步都不跳，那这也是一种，因此返回1
            return 1;
        }

        if (mem[i][j][move] != -1) {
            return mem[i][j][move];
        }


        long ans = 0;

        for (int[] d : dr) {
            //8个方向可能的情况之和
            if (isValid(i + d[0], j + d[1])) {
                ans += processing(i + d[0], j + d[1], move - 1, mem);
            }
        }


        ans %= mod;
        mem[i][j][move] = ans;
        return ans;
    }

    public boolean isValid(int x, int y) {
        return !(x < 0 || x > 3 || y < 0 || y > 2 || (x == 3 && y == 0) || (x == 3 && y == 2));
    }


}
