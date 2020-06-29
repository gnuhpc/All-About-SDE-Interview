package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.PriorityQueue;

//https://www.youtube.com/watch?v=Hoixgm4-P4M

//
/*
Method 1 : 分治+快排分区
 */
public class FindKthLargest215 {
    @Test
    public void test() {
        int[] nums = new int[]{5, 6, 1, 2, 5, 7};
        System.out.println(findKthLargest(nums, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        return Utils.findKthLargest(nums, k);
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
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            //Step1: 填充pq
            if (i < k) pq.offer(nums[i]);
                //Step2: 判断堆顶元素，这是待抛弃的一个元素，如果发现有比堆顶大的就抛弃（求第k个最小的则发现小的就抛弃）
            else {
                if (nums[i] > pq.peek()) {
                    pq.poll();
                    pq.offer(nums[i]);
                }
            }
        }

        return pq.peek();
    }

}
