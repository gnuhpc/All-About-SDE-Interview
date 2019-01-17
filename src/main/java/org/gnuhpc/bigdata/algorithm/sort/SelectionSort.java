package org.gnuhpc.bigdata.algorithm.sort;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

//O(n^2), unstable, 对于小数组很快，比插入排序写入cost小
public class SelectionSort {
    //整体分为sorted 和 unsorted部分，从0 开始sorted部分开始一个个增加
    public static Void sort(int[] arr){
        for (int sorted = 0; sorted < arr.length; sorted++) {
            int minIndex = sorted;
            //要点1：在unsorted部分找到最小值进行下标标记
            for (int i = sorted+1; i < arr.length; i++) {
                if (arr[i] < arr[minIndex]) minIndex = i;
            }

            //要点2：与sorted的最后一个数字进行交换
            Utils.swap(arr,sorted, minIndex);
        }

        return null;
    }

    @Test
    public void test(){
        int[] arr = Utils.generateRandomArray(10, 0, 100);
        Utils.printArray(arr);
        sort(arr);
        Utils.printArray(arr);
    }
}
