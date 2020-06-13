package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

public class Tribonacci1137 {
    public int tribonacci(int n) {
        if(n<=1) return n;
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        mem[0] = 0;
        mem[1] = 1;
        mem[2] = 1;

        return dfs(mem, n);
    }

    private int dfs(int[] memory, int n) {

        if(memory[n] != -1) {
            return memory[n];
        }
        int sum = dfs(memory ,n - 3) + dfs(memory, n - 2) + dfs(memory, n - 1);
        memory[n] = sum;

        return sum;
    }
}
