package org.gnuhpc.bigdata.algorithm.sort;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

// O(nlogn) 稳定，Object类型的排序一般都是merge sort , 空间复杂度O(n)
// 左闭右开
// 2个优化点
// ① merge的时候如果已经有序就返回
// ② 递归退出的条件：当数据集足够小的时候，采用插入排序，这样会比较快
// 原因是因为
// 1、当数据数量足够小时，数组近乎有序的概率比较大
// 2、插入排序O(N^2)前面的系数比O(NLogN)小，在N足够小的时候，插入排序比较快
public class MergeSort {
    public static Void sort(int[] input){
        if (input == null || input.length < 2 ) return null;
        mergeSort(input,0,input.length);
        //也可以使用并发版本
        //parallelMergeSort(input,0,input.length,16);
        return null;
    }

    //左闭右开
    public static void mergeSort(int[] input, int start, int end) {
        //Breaking condition
        if (end - start <= 1) {
            return;
        }

//        if (end - start <= 16) {
//            insertSort(input,start,end);
//        }


        int mid = (end - start) / 2 + start;
        mergeSort(input, start, mid);
        mergeSort(input, mid, end);
        merge2(input, start, end);
    }

    // 左闭右开 ， 誊写的过程 , 双指针写法, 推荐这种。
    public static void merge(int[] nums, int start, int end) {
        int mid = (end - start) / 2 + start;
        //已经有序就返回
        if (nums[mid - 1] <= nums[mid]) {
            return;
        }

        int i = start, j = mid, cacheIdx = 0;
        int[] cache = new int[end - start];

        while (i < mid && j < end) {
            cache[cacheIdx++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }

        while (i < mid) {
            cache[cacheIdx++] = nums[i++];
        }
        while (j < end) {
            cache[cacheIdx++] = nums[j++];
        }

        //拷贝回结果，注意是copy回input从start开始的数据
        System.arraycopy(cache, 0, nums, start, cache.length);
    }

    // Merge循环嵌套写法， 这种写法在merge sort counting中有用
    public static void merge2(int[] nums, int start, int end){
        int mid = (end-start)/2 + start;
        int i = start;
        int j = mid;
        int cacheIdx = 0;

        int[] cache = new int[end-start];
        for(;i<mid;i++){
            while (j < end && nums[j]<nums[i] ){ //注意要判断j的有效性在前
                cache[cacheIdx++] = nums[j++];
            }

            cache[cacheIdx++] = nums[i];
        }

        //注意这个处理，因为右半边后边没有被放入cache中的数字本身就很大了。
        //也可以将右边没有放入cache的一个while都放进去，然后copy的时候长度直接为cache.length
        System.arraycopy(cache,0, nums, start, j-start);
    }

    private static void insertSort(int[] arr, int l, int r){
        for( int i = l + 1 ; i < r ; i ++ ){
            int e = arr[i];
            int j = i;
            for( ; j > l && arr[j-1]>e ; j--)
                arr[j] = arr[j-1];
            arr[j] = e;
        }
    }

    //并发版本
    public static void parallelMergeSort(int[] input,int low, int high, int numOfThreads) {
        if (numOfThreads <= 1) {
            mergeSort(input,low, high);
            return;
        }
        int middleIndex = (low + high) / 2;

        Thread leftSorter = mergeSortThread(input,low, middleIndex, numOfThreads);
        Thread rightSorter = mergeSortThread(input,middleIndex, high, numOfThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(input,low, high);
    }

    private static Thread mergeSortThread(int[] input, int low, int high, int numOfThreads) {
        return new Thread(() -> parallelMergeSort(input,low, high, numOfThreads / 2));
    }

    @Test
    public void test() {
//        int[] arr = Utils.generateRandomArray(5, 0, 100);
        int[] arr = new int[]{34,25,46,12,37};
        Utils.printArray(arr);
        Utils.evaluateSort(MergeSort::sort,arr);
        Utils.printArray(arr);
    }
}
