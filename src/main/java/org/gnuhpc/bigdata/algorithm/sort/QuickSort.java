package org.gnuhpc.bigdata.algorithm.sort;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

// O(nlogn) 不稳定 , inplace sort，原始类型的一般都是QuickSort
// 总结的不错： https://segmentfault.com/a/1190000004410119
public class QuickSort {
    /*
    Method 1: 双路，分成两部分
     */
    public void quickSort2Ways(int[] numbers, int low, int high) {
        if (low < high) {
            int q = partition2Ways(numbers, low, high);
            quickSort2Ways(numbers, low, q - 1);
            quickSort2Ways(numbers, q+1, high);
        }
    }

    /*
    分区写法： 双指针交换法；最好用，推荐记忆，从左边找一个不应该在左边的，从右边找一个不应该在右边的，然后交换
     */
    // 返回p, 使得arr[l...p-1] < pivot ; arr[p...r] >= pivot
    public int partition2Ways(int[] nums, int low, int high) {
        //优化点： 面试可以不记忆。
        //加上swap这句，当数组是有序数组时，int[] arr = {1,2,3,4,5,6,7};不会退化成O(N^2)
        //swap( nums, low , (int)(Math.random()*(high-low+1))+low );
        int pivotIdx = low;
        int pivot = nums[pivotIdx];
        int p = low+1;
        int q = high;
        while(p <= q){
            while(p <= q && nums[p] < pivot) p++;
            while(p <= q && nums[q] >= pivot) q--;
            if(p < q){
                swap(nums,p,q);
            }
        }
        //这个时候q就是pivot应该的位置，swap一下
        swap(nums,pivotIdx,q);
        return q;
    }

    /*
      遍历写法
     */
    public int partition2Ways2(int[] nums, int low, int high) {
        int pivot = nums[low];
        // Last position where puts the no_larger element.
        int pos = low;
        for(int i=low+1; i<=high; i++){
            if(nums[i] < pivot){
                swap(nums,++pos,i);
            }
        }
        swap(nums,low,pos);
        return pos;
    }


    /*
    Method 2 : 三路，分成三部分， 推荐！
     */
    public void quickSort3Ways(int[] numbers, int left, int right) {
        if (left >= right) return;
        int[] pivots = parition3Ways(numbers, left, right);
        int lt = pivots[0];
        int gt = pivots[1];
        // recursive sort , [lt,gt]都是已经确定好位置的了
        quickSort3Ways(numbers, left, lt - 1);
        quickSort3Ways(numbers, gt + 1, right);
    }

    /*  lt位置的左边都是小于pivot的
        lt到i都是等于pivot的
        i到gt都是不知道的
        gt的右边的都是大于pivot的

        a[l,lt-1] < pivot
        a[lt, i-1] = pivot
        a[i,gt] = unseen, 直到i到gt，最后 a[lt,gt] = pivot
        a[gt+1, r] > pivot
     */

    // 注意：在把i和lt交换时, i可以increment (因为我们知道a[lt]==pivot),
    // 但是i和gt交换时, i不能increment: 因为a[gt]不知道多大, 所以i位置要继续检查.
    //荷兰国旗问题
    public int[] parition3Ways(int[] numbers, int l, int r) {
        swap( numbers, l , (int)(Math.random()*(r-l+1))+l );
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

    public void sort(int[] arr) {
        quickSort2Ways(arr,0,arr.length-1);
        //quickSort3Ways(arr,0,arr.length-1);
    }


    @Test
    public void test() {
//        int[] arr = Utils.generateRandomArray(10, 0, 100);
//        int[] arr = {2,3,2,1,4,2};
        int[] arr = {4,2,5,1};
//        int[] arr = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        int[] arr = {1,2,3,4,5,6,7};
//        int[] arr = {52, 14, 71, 62, 76, 30, 6, 74};
//        int[] arr = new int[]{2,0,1};
        Utils.printArray(arr);
        sort(arr);
        Utils.printArray(arr);
    }
}
