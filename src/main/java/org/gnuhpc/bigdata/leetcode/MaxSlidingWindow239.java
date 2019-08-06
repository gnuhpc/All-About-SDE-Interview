package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.queue.basicimpl.MonotonicQueue;
import org.gnuhpc.bigdata.datastructure.queue.basicimpl.Queue;
import org.junit.Test;

import java.util.*;


public class MaxSlidingWindow239 {
    @Test
    public void test(){
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        int k = 3;

        for (int i: maxSlidingWindow3(nums,k)){
            System.out.println(i);
        }
    }
    //Method1: 单调队列，专门解决sliding window的最大值最小值问题, 这个特殊的队列保留的都是promising 的值，
    // 也就是很有可能成为最大值的值。队头一定是最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int range = k-1;
        MonotonicQueue mq = new MonotonicQueue();
        int[] result = new int[nums.length-range];
        for (int i = 0; i < nums.length; i++) {
            mq.add(nums[i]);
            if (i>=range){//满足一个窗口了
                //获取结果
                result[i-range] = mq.peek();
                if (nums[i-range] == mq.peek()){
                    //如果要滑过去的数字是最大值，则将其删除
                    mq.poll();
                }
            }
        }

        return result;
    }


    //优先级队列 O(nlogn)
    /*
    * 思路:维护一个大小为K的最大堆，依此维护一个大小为K的窗口，
    * 每次读入一个新数，都把堆中窗口最左边的数扔掉，再把新数加入堆中，这样堆顶就是这个窗口内最大的值。
    * */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int range = k-1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int[] res = new int[nums.length - range];
        for(int i = 0; i < nums.length; i++){
            // 把窗口最左边的数去掉
            if(i >= k) pq.remove(nums[i - k]);
            // 把新的数加入窗口的堆中
            pq.offer(nums[i]);
            // 堆顶就是窗口的最大值
            if(i >= range) res[i - range] = pq.peek();
        }
        return res;
    }

    /* 纯技巧性的
    For Example: A = [2,1,3,4,6,3,8,9,10,12,56],  w=4

1. partition the array in blocks of size w=4. The last block may have less then w.
2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|

2. Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56

3. Similarly calculate max in future by traversing from end to start.
right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56

4. now, sliding max at each position i in current window, sliding-max(i) = max{right_max(i), left_max(i+w-1)}
sliding_max = 4, 6, 6, 8, 9, 10, 12, 56

     */

    public int[] maxSlidingWindow3(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int[] max_left = new int[nums.length];
        int[] max_right = new int[nums.length];

        max_left[0] = nums[0];
        max_right[nums.length - 1] = nums[nums.length - 1];

        for (int i = 1; i < nums.length; i++) {
            max_left[i] = (i % k == 0) ? nums[i] : Math.max(max_left[i - 1], nums[i]);

            int j = nums.length - i - 1;
            max_right[j] = (j % k == 0) ? nums[j] : Math.max(max_right[j + 1], nums[j]);
        }

        int[] sliding_max = new int[nums.length - k + 1];
        for (int i = 0, j = 0; i + k <= nums.length; i++) {
            sliding_max[j++] = Math.max(max_right[i], max_left[i + k - 1]);
        }

        return sliding_max;
    }

}
