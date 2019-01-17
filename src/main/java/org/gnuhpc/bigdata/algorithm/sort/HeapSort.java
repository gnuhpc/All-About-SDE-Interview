package org.gnuhpc.bigdata.algorithm.sort;

import org.junit.Test;
import static org.junit.Assert.*;
//unstable
public class HeapSort{
    private int N;

    //O(N log(N)) - iterating over every element and building BST
    // 可以保证这个时间复杂度，不管数据是什么情况
    public void sort(int[] arr) {
        //第一步构建最大堆
        buildHeap(arr);
        //O(N) * log(n)
        for (int i = N; i > 0; i--) {
            //第二步，把最大值放到最后，将第一个节点与最后一个节点交换 ， 如果不是inplace，弄个stack也行
            swap(arr, 0, i);
            //第三步，缩小堆化的范围
            N = N - 1;
            //然后堆化这个数组
            heapify(arr, 0);
        }
    }

    /* Function to build a heap */
    // 想由小到大排序的话构建最大堆
    public void buildHeap(int arr[]) {
        N = arr.length - 1;
        //log(n)
        for (int i = N / 2; i >= 0; i--)
            heapify(arr, i);
    }

    /* Function to swap largest element in heap */
    //构建最大堆，将最大的元素向上放
    public void heapify(int arr[], int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])
            max = right;

        if (max != i) {
            swap(arr, i, max);
            heapify(arr, max);
        }
    }

    /* Function to swap two numbers in an array */
    public void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void testHeapSort(){
        HeapSort heapSort = new HeapSort();
        int[] input = new int[]{4,5,1,2,10,12,1};
        heapSort.sort(input);
        int[] result = new int[]{1,1,2,4,5,10,12};

        assertArrayEquals(input,result);

    }
}
