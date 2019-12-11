package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.Arrays;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

/**
 * Copyright gnuhpc 19-8-8
 */
public class WiggleSort324 {
    public void wiggleSort(int[] nums) {
        if (nums == null) return;
        if (nums.length <= 1) return;
        if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                swap(nums, 0, 1);
            }
            return;
        }

        int n = nums.length;
        //找中位数
        int[] res = findKthLargest(nums, (nums.length + 1) / 2);
        int pos = res[0];
        int median = res[1];

        // Repartition again, For cases like 3,2,1,1,3,2
        int idx = pos+1;
        for (int i = pos+1; i < n; i++) {
            if (nums[i] == median){
                swap(nums,i,idx);
                idx++;
            }
        }

        int left = 0, right = (nums.length %2 ==1)? pos: pos+1 ;
        boolean flag = true;

        int[] temp = new int[n];

        for (int j = 0; j < n; j++) {
            if (flag){
                temp[j] = nums[right++];
            } else {
                temp[j] = nums[left++];
            }

            flag = !flag;
        }

        System.arraycopy(temp,0,nums,0,n);


    }


    public int[] findKthLargest(int[] nums, int k) {
        if(nums==null || nums.length == 0) return null;
        int left = 0, right = nums.length-1;

        while (true){
            int pos = partition(nums,left,right);
            if (pos + 1 > k){
                right = pos -1 ;
            } else if (pos + 1 < k) {
                left = pos + 1;
            } else {
                return new int[]{pos,nums[pos]};
            }
        }
    }

    private int partition(int[] numbers, int low, int high){
        int pivot = numbers[low];

        int i = low+1, j = high;

        while(i<=j){
            while (i<=j && numbers[i] > pivot) i++;
            while (i<=j && numbers[j] <= pivot) j--;

            if (i<=j){
                swap(numbers,i,j);
            }
        }

        swap(numbers,low,j);
        return j;
    }



    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /*
    Method2: 直接排序 ，不符合时间复杂度，但是实际上更快
    我们可以先给数组排序，然后在做调整。
    调整的方法是找到数组的中间的数，相当于把有序数组从中间分成两部分，
    然后从前半段的末尾取一个，在从后半的末尾去一个，这样保证了第一个数小于第二个数，
    然后从前半段取倒数第二个，从后半段取倒数第二个，这保证了第二个数大于第三个数，
    且第三个数小于第四个数，以此类推直至都取完.
    O(nlogn)
     */
    public void wiggleSort2(int[] nums) {
        int[] tmp = nums.clone();
        Arrays.sort(tmp);
        int n = nums.length, m = (n + 1) / 2, j = n;
        for (int i = 0; i < n; i++) {
            nums[i] = (i % 2) == 1 ? tmp[--j] : tmp[--m];
        }
    }

    @Test
    public void test() {
//        int[] nums = new int[] {1,1,2,1,2,2,1};
//        int[] nums = new int[]{1, 3, 2, 2, 3, 1};
//        int[] nums = new int[]{5, 3, 1, 2, 6, 7, 8, 5, 5};
//        int[] nums = new int[] {4,5,5,6};
//        int[] nums = new int[] {4,5,5,5,5,6,6,6};
        int[] nums = new int[] {3,2,1,1,3,2};
        wiggleSort(nums);
        Utils.printArray(nums);
    }
}
