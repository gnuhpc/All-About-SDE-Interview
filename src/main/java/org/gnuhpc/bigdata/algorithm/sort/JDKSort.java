package org.gnuhpc.bigdata.algorithm.sort;

import java.util.Arrays;

public class JDKSort {
    public static void main(String[] args) {
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };

        //if you are going to sort an array of objects ,implementing the comparable interface is a must.
        Arrays.parallelSort(intArray);

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }
}
