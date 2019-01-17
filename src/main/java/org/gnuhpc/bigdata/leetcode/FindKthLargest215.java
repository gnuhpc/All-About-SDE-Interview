package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

//https://www.youtube.com/watch?v=Hoixgm4-P4M
/*
Method 1 : 分治+快排分区 //TODO 整理这个方法
 */
public class FindKthLargest215{
    @Test
    public void test(){
        int[] nums = new int[]{3,2,1,5,6,4};
        System.out.println(findKthLargest(nums,2));
    }


    public int findKthLargest(int[] nums, int k) {
        if(nums==null || nums.length == 0) return -1;
        int left = 0, right = nums.length-1;

        while (true){
            int pos = partition3(nums,left,right);
            if (pos + 1 > k){
                right = pos -1 ;
            } else if (pos + 1 < k) {
                left = pos + 1;
            } else {
                return nums[k-1];
            }
        }
    }


    public int partition(int[] nums, int left, int right) {
        int pivotVal = nums[left];

        //pos为分界线
        int pos= left;
        for (int i = left+1; i <= right; i++) {
            //i is valid, swap it with pos
            if (nums[i]>pivotVal){
                swap(nums,++pos,i);
            }
        }
        swap(nums,left,pos);

        return pos;
    }


    private int partition2(int[] numbers, int low, int high) {
        int pivot = numbers[low];//选第一个元素作为枢纽元
        int i = low, j = high;
        while(i < j)
        {
            while(i < j && numbers[j] < pivot)j--;
            numbers[i] = numbers[j];//从后面开始找到第一个小于pivot的元素，放到low位置
            while(i < j && numbers[i] >= pivot)i++;
            numbers[j] = numbers[i];//从前面开始找到第一个大于pivot的元素，放到high位置
        }
        numbers[i] = pivot;//最后枢纽元放到low的位置
        return i;
    }

    private int partition3(int[] numbers, int low, int high){
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

    /*
    Method2 :Min/Max heap
    We can use a min heap to solve this problem. The heap stores the top k elements.
Whenever the size is greater than k, delete the min. Time complexity is O(nlog(k)).
Space complexity is O(k) for storing the top k numbers.
     */

    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int n: nums){
            pq.offer(n);

            if (pq.size() > k){
                pq.poll();
            }
        }

        return pq.peek();
    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int n: nums){
            pq.offer(n);
        }

        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }

        return pq.peek();
    }
}
