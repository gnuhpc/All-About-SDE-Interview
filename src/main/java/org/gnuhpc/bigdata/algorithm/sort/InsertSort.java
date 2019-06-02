package org.gnuhpc.bigdata.algorithm.sort;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

/**
 * Created by gnuhpc on 2017/7/3.
 */
//Online排序算法，可以从网上边下载数据边排序，数据量小的时候很有优势
//插入排序比选择排序更快, 具备稳定性，特别想抽牌然后整牌 最快是O(N)，
// 最慢是O(N^2)
//在数组近乎有序的情况下，最好O(N)
public class InsertSort {
    public static Void sort(int[] array){
        //要点1：从i=1开始即可
        for(int i=1;i<array.length;i++){
            int value = array[i];
            //要点2：从i开始向左找（左边是sorted区域，右边是unsorted区域），只要后一个值比当前值大就一直继续直到最后，数字都往右边移动，留出空位
            //注意，插入排序的重要特点是可以提前终止内循环,因为在一个几乎已经排好序的数据中，插入排序的效率更高
            int j;
            for(j=i;j > 0 && array[j - 1] > value;j--) {
                array[j] = array[j - 1];
            }
            //要点3： 插空
            array[j] = value;
        }

        return null;
    }

    public static void sortRecursive(int[] array, int numItems) {

        if (numItems < 2) {
            return;
        }

        sortRecursive(array, numItems - 1);
        int value = array[numItems - 1];
        int i;

        for (i = numItems - 1; i > 0 && array[i - 1] > value; i--) {
            array[i] = array[i - 1];
        }

        array[i] = value;

        System.out.println("Result of call when numItems = " + numItems);
        for (i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(", ");
        }
        System.out.println("");
        System.out.println("---------------------");
    }


    @Test
    public void test(){
        int[] arr = Utils.generateRandomArray(10, 0, 10000);
        int[] arr2 = Utils.generateNearlyOrderArray(10,1);
        //Utils.evaluateSort(InsertSort::sort,arr);
        Utils.printArray(arr);

        sortRecursive(arr,arr.length);
    }
}
