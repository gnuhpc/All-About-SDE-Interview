package org.gnuhpc.interview.leetcode.solutions;

public class PrevPermOpt11053 {
    public int[] prevPermOpt1(int[] A) {
        int len = A.length;
        for(int i=len-2;i>=0;i--){
            int max = i+1;
            for(int j=i+1;j<len;j++){
                if(A[j]>A[max] && A[j]<A[i]){
                    max = j;
                }
            }

            if(A[i]>A[max]){
                int temp = A[i];
                A[i] = A[max];
                A[max] = temp;
                return A;
            }
        }
        return A;
    }
}
