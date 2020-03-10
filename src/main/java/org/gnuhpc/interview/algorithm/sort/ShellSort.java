package org.gnuhpc.interview.algorithm.sort;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

//插入排序的变种，gap=1就是插入排序，O(n2)，像一个筛子一样，筛出gap位置的数字进行插入排序
//https://www.youtube.com/watch?v=SCBf7aqKQEY
public class ShellSort {
    public static Void sort(int arr[]) {
        int n = arr.length;

        // Start with a big gap, then reduce the gap , 最后一个gap为1，即为插入排序
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1) {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i （创造一个hole）
                int temp = arr[i];

                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                //类似于插入排序，从后往前填空
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];

                // put temp (the original a[i]) in its correct
                // location 挖坑，填上
                arr[j] = temp;
            }
        }
        return null;
    }

    @Test
    public void test() {
        int[] arr = Utils.generateRandomArray(10, 0, 100);
        Utils.printArray(arr);
        sort(arr);
        Utils.printArray(arr);
        Utils.evaluateSort(ShellSort::sort, arr);
    }
}
