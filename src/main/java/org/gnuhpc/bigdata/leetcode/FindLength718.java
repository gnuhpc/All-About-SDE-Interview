package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FindLength718 {
    public int findLength(int[] A, int[] B) {
        if (A==null || B==null || A.length == 0 || B.length == 0) return 0;

        int len1 = A.length;
        int len2 = B.length;

        int max = 0;
        List<int[]> result = new ArrayList<>();

        Integer[][] match = new Integer[len1][len2];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (A[i] == B[j]) {
                    if (i == 0 || j == 0) match[i][j] = 1;

                    else match[i][j] = match[i - 1][j - 1] + 1;

                    if (match[i][j] > max) {
                        max = match[i][j];
                        //注释掉的是如果要求这个common substring的方法
                        //result.clear();//更大以后过去就不算了
                        //result.add(Arrays.copyOfRange(A,i-max+1,i+1));
                    } //else if (match[i][j] == max) {
                       // result.add(Arrays.copyOfRange(A,i-max+1,i+1));
                    //}
                } else match[i][j] = 0;
            }
        }
        return max;
    }

    @Test
    public void test(){
        System.out.println(findLength(new int[]{1,2,3,2,1},new int[]{3,2,1,4,7}));
    }
}
