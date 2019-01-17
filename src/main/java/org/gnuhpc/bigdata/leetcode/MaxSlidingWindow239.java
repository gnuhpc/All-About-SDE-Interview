package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.queue.basicimpl.Queue;
import org.junit.Test;

import java.util.*;

//定义一个单调队列
/*
单调队列（Monotone queue）是一种特殊的优先队列
添加元素e到队尾时，我们采取的解决方法同样是，先从队尾删去小于等于e的元素。
注意，普通的队列queue是不支持从队尾删除的，我们需要使用双端队列deque，即有两个指针，一头一尾。

单调队列，就是单调的队列，通常用来解决滑动窗口的最值问题，可以应用到 DP 的优化上。
一个单调队列中的元素总是单调递增（或递减）的。

滑动窗口
例：有一个队列，每次从队尾加入一个元素，或从队首删除一个元素，并在任何时刻求整个队列的最大值。
单调队列就是解决这类问题的数据结构，我们用一个辅助队列，使该队列的首元素总是原队列的最大值，
这样就可以 O(1)地求出队列的最大值了。
 */
class MonotonicQueue implements Queue<Integer>{
    private Deque<Integer> internalDq = new LinkedList<>();

    @Override
    public Integer add(Integer item) {
        while (!internalDq.isEmpty() && item > internalDq.peekLast()) internalDq.removeLast();
        internalDq.addLast(item);
        return item;
    }

    //删除最大值
    @Override
    public Integer remove() {
        return internalDq.removeFirst();
    }

    //获取最大值
    @Override
    public Integer element() {
        return internalDq.peekFirst();
    }

    @Override
    public int size() {
        return internalDq.size();
    }

    @Override
    public boolean isEmpty() {
        return internalDq.isEmpty();
    }

    @Override
    public void print() {
        return;
    }
}

// 首先我们读完题目，确定是用sliding window的方法来做
public class MaxSlidingWindow239 {
    @Test
    public void test(){
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        int k = 3;

        for (int i: maxSlidingWindow(nums,k)){
            System.out.println(i);
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int range = k-1;
        MonotonicQueue monotonicQueue = new MonotonicQueue();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            monotonicQueue.add(nums[i]);
            if (i>=range){//满足一个窗口了
                //获取结果
                result.add(monotonicQueue.element());
                if (nums[i-range] == monotonicQueue.element()){
                    //如果要滑过去的数字是最大值，则将其删除
                    monotonicQueue.remove();
                }
            }
        }

        return result.stream().mapToInt(i->i).toArray();
    }


    //优先级队列 O(nlogn)
    /*
    * 思路:维护一个大小为K的最大堆，依此维护一个大小为K的窗口，
    * 每次读入一个新数，都把堆中窗口最左边的数扔掉，再把新数加入堆中，这样堆顶就是这个窗口内最大的值。
    * 注意:结果数组的大小是nums.length + 1 - k， 赋值时下标也是i + 1 - k
    * */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[0];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        int[] res = new int[nums.length + 1 - k];
        for(int i = 0; i < nums.length; i++){
            // 把窗口最左边的数去掉
            if(i >= k) pq.remove(nums[i - k]);
            // 把新的数加入窗口的堆中
            pq.offer(nums[i]);
            // 堆顶就是窗口的最大值
            if(i + 1 >= k) res[i + 1 - k] = pq.peek();
        }
        return res;
    }

}
