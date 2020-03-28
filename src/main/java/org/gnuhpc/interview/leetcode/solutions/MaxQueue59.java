package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/3/28
 */

/*
记住当前最大值出队后，队列里的下一个最大值即可。
具体方法是使用一个双端队列 dequedeque，在每次入队时，如果 dequedeque 队尾元素小于即将入队的元素 valuevalue，则将小于 valuevalue 的元素全部出队后，再将 valuevalue 入队；否则直接入队。
这时，辅助队列 dequedeque 队首元素就是队列的最大值。
作者：z1m
如下链接有个动画：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/solution/ru-he-jie-jue-o1-fu-za-du-de-api-she-ji-ti-by-z1m/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MaxQueue59 {
    Queue<Integer> queue;
    Deque<Integer> maxQueue;

    public MaxQueue59() {
        queue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }

    public int max_value() {
        if (maxQueue.isEmpty())
            return -1;
        return maxQueue.peek();
    }

    public void push_back(int value) {
        queue.add(value);
        while (!maxQueue.isEmpty() && value > maxQueue.getLast())
            maxQueue.pollLast();
        maxQueue.add(value);
    }

    public int pop_front() {
        if (queue.isEmpty())
            return -1;
        int ans = queue.poll();
        if (ans == maxQueue.peek())
            maxQueue.poll();
        return ans;
    }
}
