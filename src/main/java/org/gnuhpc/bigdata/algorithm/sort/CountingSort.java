package org.gnuhpc.bigdata.algorithm.sort;

//O(n+k), k是最大值和最小值的差值
public class CountingSort {
    public static void main(String[] args) {
        int[] intArray = { 2, 5, 9, 8, 2, 8, 7, 10, 4, 3 };

        countingSort(intArray, 1, 10);

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }

    public static void countingSort(int[] input, int min, int max) {
        int[] countArray = new int[(max - min) + 1];

        //先计数
        for (int n :input) {
            countArray[n - min]++;
        }

        //再取idx为最终数值，arr[idx]里面记录的是次数
        for (int i = 0, j =0 ; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                input[j++] = i + min;
                countArray[i]--;
            }
        }
    }
}
