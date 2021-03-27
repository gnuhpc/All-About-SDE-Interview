package org.gnuhpc.interview.leetcode.solutions;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LargestSumAfterKNegations1005 {

    public int largestSumAfterKNegations(int[] A, int K) {
        Integer[] arr = Arrays.stream(A).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, (o1, o2) -> {
            int a = Math.abs(o1);
            int b = Math.abs(o2);

            return b - a;
        });

        int count = 0;
        for(int num: arr){
            if(num<0) count++;
        }

        if(count != K) {
            if (count > K) {
                for (int i = 0; K != 0; i++) {
                    if (arr[i] < 0) {
                        K--;
                        arr[i] = -arr[i];
                    }
                }
                int sum = 0;
                for (int num: arr) sum+=num;
                return sum;
            } else { // count < K
                for (int i = 0; i< arr.length; i++) {
                    if (arr[i] < 0) {
                        arr[i] = -arr[i];
                    }
                }

                int min = K - count;
                int sum = 0;
                for (int num: arr) sum+=num;
                if(min%2 == 0) {
                    return sum;
                } else{
                    return sum - arr[arr.length-1]*2;
                }
            }
        } else {
            int sum = 0;
            for (int num: arr) sum+=num>0?num:-num;
            return sum;
        }
    }

    @Test
    public void test() {
        System.out.println(largestSumAfterKNegations(new int[] {2,-3,-1,5,-4}, 2));
    }
}
