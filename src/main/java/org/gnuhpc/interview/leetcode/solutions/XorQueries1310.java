package org.gnuhpc.interview.leetcode.solutions;

public class XorQueries1310 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] preXOR = new int[arr.length];
        int[] res = new int[queries.length];
        preXOR[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            preXOR[i] = preXOR[i-1]^arr[i];
        }

        for(int i=0;i<queries.length;i++){
            int l = queries[i][0];
            int r = queries[i][1];

            res[i] = l == 0? preXOR[r] : preXOR[r]^preXOR[l-1];

        }

        return res;
    }
}
