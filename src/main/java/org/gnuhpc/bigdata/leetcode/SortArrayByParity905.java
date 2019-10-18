package org.gnuhpc.bigdata.leetcode;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

public class SortArrayByParity905 {
    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length-1;

        while(i<j){
            int numi = A[i], numj = A[j];

            boolean f1 = isEven(numi), f2 = isEven(numj);

            if (!f1 && f2){
                swap(A,i,j);
                i++;
                j--;
            } else if (f1 && !f2){
                i++;
                j--;
            } else if (f1 && f2){
                i++;
            } else {
                j--;
            }
        }

        return A;
    }

    private boolean isEven(int num){
        return num%2 == 0;
    }
}
