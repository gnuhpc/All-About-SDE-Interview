package org.gnuhpc.bigdata.algorithm.sort;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.Random;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

// O(nlogn) 不稳定 , inplace sort，原始类型的一般都是QuickSort
// 总结的不错： https://segmentfault.com/a/1190000004410119
public class QuickSort {
    /*
    Method 1: 分而治之
     */
    public void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int q = partition3(numbers, low, high);
            quickSort(numbers, low, q - 1);
            quickSort(numbers, q + 1, high);
        }
    }

    /*
    1.1 遍历法
     */
    private int partition(int[] numbers, int low, int high) {
        int pivot = numbers[low];
        // Last position where puts the no_larger element.
        int pos = low;
        for(int i=low+1; i<=high; i++){
            if(numbers[i] < pivot){
                swap(numbers,++pos,i);
            }
        }
        swap(numbers,low,pos);
        return pos;
    }

    /*
    1.2 填坑法
     */
    private int partition2(int[] numbers, int low, int high) {
        //挖坑
        int pivot = numbers[low];//选第一个元素作为枢纽元
        int i = low;
        int j = high;
        while(i < j)
        {
            //之所以从后面先找是因为nums[low]已经被保存好了
            while(i < j && numbers[j] >= pivot)j--;
            //填坑,等于后面又多出一个坑
            numbers[i] = numbers[j];//从后面开始找到第一个小于pivot的元素，放到low位置
            while(i < j && numbers[i] < pivot)i++;
            //再填坑，等于前边又多出一个坑
            numbers[j] = numbers[i];//从前面开始找到第一个大于pivot的元素，放到high位置
        }
        //最后把最初的元素填上
        numbers[i] = pivot;//最后枢纽元放到low的位置
        return i;
    }

    /*
    1.3 双指针交换法；
     */
    public int partition3(int[] numbers, int low, int high) {
        int pivot = numbers[low];
        int i = low+1;
        int j = high;
        while(i <= j){
            while(numbers[i] < pivot && i <= j) i++;
            while(numbers[j] >= pivot && i <= j) j--;
            if(i <= j){
                swap(numbers,i,j);
            }
        }
        //交换j是因为它从它的有效区域探头出来了
        swap(numbers,low,j);
        return j;
    }


    /*
    Method 2 : 三路
     */
    private void quickSort3Way(int[] numbers, int left, int right) {
        if (left >= right) return;
        int[] pivots = parition3way(numbers, left, right);
        int lt = pivots[0];
        int gt = pivots[1];
        // recursive sort
        quickSort3Way(numbers, left, lt - 1);
        quickSort3Way(numbers, gt + 1, right);
    }

    /*
        a[l,lt-1] < pivot
        a[lt, i-1] = pivot
        a[i,gt] = unseen
        a[gt+1, r] > pivot
     */

    // 注意：在把i和lt交换时, i可以increment (因为我们知道a[lt]==pivot),
    // 但是i和gt交换时, i不能increment: 因为a[gt]不知道多大, 所以i位置要继续检查.
    public int[] parition3way(int[] numbers, int l, int r) {
        int pivot = numbers[l];
        int lt = l, gt = r;
        int i = l;

        while (i<=gt) {
            if (numbers[i] < pivot)
                swap(numbers, i++, lt++);
            else if (numbers[i] > pivot)
                swap(numbers, i, gt--);
            else // numbers[i]==  pivot
                i++;
        }

        return new int[] { lt, gt };
    }

    /*
    Method3 : 遍历版本， 没什么大意义，仅有帮助理解之作用
     */
    public void quickSortIterative (int arr[], int l, int h)
    {
        // Create an auxiliary stack
        int[] stack = new int[h-l+1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0)
        {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition(arr, l, h);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p-1 > l)
            {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p+1 < h)
            {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }


    private void sort(int[] arr) {
//        quickSort(arr,0,arr.length-1);
//        quickSort3Way(arr,0,arr.length-1);
        quickSortIterative(arr,0,arr.length-1);
    }

    @Test
    public void test() {
//        int[] arr = Utils.generateRandomArray(8, 0, 100);
        int[] arr = {3,2,8,5,6,4,1};
//        int[] arr = {52, 14, 71, 62, 76, 30, 6, 74};
        Utils.printArray(arr);
        sort(arr);
        Utils.printArray(arr);
    }

}
