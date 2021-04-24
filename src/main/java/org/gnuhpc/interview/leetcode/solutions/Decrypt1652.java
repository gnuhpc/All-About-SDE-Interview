package org.gnuhpc.interview.leetcode.solutions;

public class Decrypt1652 {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] res = new int[n];


        if(k==0){
            return res;
        }
        int[] code2 = new int[n*2];
        for (int i = 0; i < n; i++) {
            code2[i] = code[i];
            code2[i+n] = code[i];
        }


        int[] presum = new int[n*2+1];
        for (int i = 1; i < code2.length; i++) {
            presum[i] = presum[i-1] + code2[i];
        }
        if(k>0){
            //code2 [5,7,1,4,5,7,1,4]
            //presum [0,5,12,13,17,22,29,30,34]
            for (int i = 0; i < res.length; i++) {
                res[i] = presum[i+k] - presum[i];
            }
        } else {
            //code2 [2,4,9,3,2,4,9,3]
            //presum [0,2,6,15,18,20,24,33,36]
            //res [12,5,6,13]
            for (int i = 0; i < res.length; i++) {
                res[i] = presum[i+n-1] - presum[i+n+k-1];
            }
        }

        return res;
    }
}
