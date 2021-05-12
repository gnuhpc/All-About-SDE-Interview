package org.gnuhpc.interview.leetcode.solutions;

public class Decode1734 {
    public int[] decode(int[] encoded) {
        int all = 0;
        int n = encoded.length;
        int[] v = new int[n+1];

        for(int i=1;i<=n+1;i++) all ^= i;
        int first_out = 0;
        for(int i=1;i<n;i+=2) first_out ^= encoded[i];
        v[0] = all^first_out;
        for(int i=1;i<=n;i++)
            v[i] = v[i-1]^encoded[i-1];

        return v;
    }
}
