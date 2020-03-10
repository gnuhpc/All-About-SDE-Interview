package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

import static org.gnuhpc.interview.leetcode.utils.Utils.swap;

//https://www.youtube.com/watch?v=Hoixgm4-P4M

//
/*
Method 1 : 分治+快排分区
 */
public class FindKthLargest215 {
    @Test
    public void test() {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(nums, 2));
    }


    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;

        while (true) {
            int pos = partition(nums, left, right);
            if (pos + 1 > k) {
                right = pos - 1;
            } else if (pos + 1 < k) {
                left = pos + 1;
            } else {
                return nums[pos];
            }
        }
    }

    private int partition(int[] numbers, int low, int high) {
        int pivot = numbers[low];

        int i = low + 1, j = high;

        while (i <= j) {
            while (i <= j && numbers[i] > pivot) i++;
            while (i <= j && numbers[j] <= pivot) j--;

            if (i <= j) {
                swap(numbers, i, j);
            }
        }

        swap(numbers, low, j);
        return j;
    }

    /*
    Method2 :Min/Max heap
    We can use a min heap to solve this problem. The heap stores the top k elements.
Whenever the size is greater than k, delete the min. Time complexity is O(nlog(k)).
Space complexity is O(k) for storing the top k numbers.
     */

    /*
    第kth大的数字用最小堆更好。 保持堆的大小为k，最小堆上边的那个就是第k大的值
     */
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //Step1: 填充pq
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        //Step2: 判断堆顶元素，这是待抛弃的一个元素，如果发现大的就抛弃（求第k个最小的则发现小的就抛弃）
        for (int i = k; i < nums.length; i++) {
            if (pq.peek() < nums[i]) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        return pq.peek();
    }

}
