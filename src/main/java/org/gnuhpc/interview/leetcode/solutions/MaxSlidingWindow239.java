package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.queue.basicimpl.MonotonicQueue;
import org.gnuhpc.interview.datastructure.queue.basicimpl.Queue;
import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.*;


public class MaxSlidingWindow239 {
    //Method1: 单调队列，专门解决sliding window的最大值最小值问题, 这个特殊的队列保留的都是promising 的值，
    // 也就是很有可能成为最大值的值。队头一定是最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int range = k - 1;
        MonotonicQueue mq = new MonotonicQueue();
        int[] result = new int[nums.length - range];
        for (int i = 0; i < nums.length; i++) {
            mq.add(nums[i]);
            if (i >= range) {//满足一个窗口了
                //获取结果
                result[i - range] = mq.peek();
                if (nums[i - range] == mq.peek()) {
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
        int range = k - 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int[] res = new int[nums.length - range];
        for (int i = 0; i < nums.length; i++) {
            // 把窗口最左边的数去掉
            if (i >= k) pq.remove(nums[i - k]);
            // 把新的数加入窗口的堆中
            pq.offer(nums[i]);
            // 堆顶就是窗口的最大值
            if (i >= range) res[i - range] = pq.peek();
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

    //借助双端队列，关键问题:
    //为什么队列中要存放数组下标的值而不是直接存储数值?
    //因为要判断队首的值是否在窗口范围内，由数组下标取值很方便，而由值取数组下标不是很方便。
    public int[] maxSlidingWindow4(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // 结果数组
        int[] result = new int[nums.length - k + 1];
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的下标是否还在窗口中
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if (i + 1 >= k) {
                result[i + 1 - k] = nums[queue.peek()];
            }
        }
        return result;
    }

    // add by tina
    // 优化暴力解，考虑当前窗口【low,high】，维护窗口最大值与位置
    // 如果上一个窗口的最大值位置为low，那么本窗口这个最大值会被移出去，
    // 意味着本窗口需重新寻找最大值
    // 如果不是，那么比较新加入的值，与窗口最大值即可。
    public int[] maxSlidingWindow5(int[] nums, int k) {
        if (nums == null) return null;
        if (nums.length == 0 || nums.length == 1) return nums;
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int maxPos = -1;
        int low = 0;
        int high = k - 1;
        int max = nums[0];
        while (high < len) {
            if (low - 1 == maxPos) {
                max = nums[low];
                maxPos = low;
                for (int i = low + 1; i <= high; i++) {
                    if (nums[i] > max) {
                        max = nums[i];
                        maxPos = i;
                    }
                }

            } else if (nums[high] >= max) {
                max = nums[high];
                maxPos = high;
            }
            res[low] = max;
            low++;
            high++;
        }
        return res;
    }


    @Test
    public void test() {
        Utils.printArray(maxSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

}
