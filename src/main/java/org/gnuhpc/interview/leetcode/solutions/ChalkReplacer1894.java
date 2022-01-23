package org.gnuhpc.interview.leetcode.solutions;

public class ChalkReplacer1894 {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        int[] presum = new int[n+1];
        if(n == 1 || chalk[0] > k) return 0;

        for(int i = 1; i <= n; i++){
            presum[i] = presum[i-1] + chalk[i-1];
            if(presum[i] > k) return i-1;
        }

        k = k % presum[n];

        for(int i = 0; i <= n; i++){
            if(presum[i] > k) return i-1;
        }

        return 0;
    }
}
