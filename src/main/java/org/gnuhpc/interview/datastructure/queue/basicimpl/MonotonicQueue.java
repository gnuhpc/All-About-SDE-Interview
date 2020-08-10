package org.gnuhpc.interview.datastructure.queue.basicimpl;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 19-8-5
 */

/*
单调队列不是真正的队列。因为队列都是FIFO的，统一从队尾入列，从对首出列。
但单调队列是从队尾入列，从队首或队尾出列，所以单调队列不遵守FIFO。

1) 对于单调(递增)队列，每次添加新元素时跟队尾比较，如果队尾元素值大于待入队元素，则将对尾元素从队列中弹出，重复此操作，直到队列为空或者队尾元素小于待入队元素。然后，再把待入队元素添加到队列末尾。
单调递增是指从队首到队尾递增。举个例子[1,3,4,5]，1是队首，5是队尾。
单调(递减)队列与之类似，只是将上面的大,小两字互换。

2)单调(递增)队列可以用来求滑动窗口的最小值。同理，单调(递减)队列可以用来求滑动窗口的最大值。其算法复杂度都是O(n)。注意，如果我们用最小堆或是最大堆来维持滑动窗口的最大/小值的话，复杂度是O(nlogn)，因为堆查询操作是O(1)，但是进堆和出堆都要调整堆，调整的复杂度O(logn)。

3) 单调队列的一个用途是利用其滑动窗口最值优化动态规划问题的时间复杂度。
 */
public class MonotonicQueue {
    private final Deque<Integer> dq = new LinkedList<>();

    public Integer add(Integer item) {
        while (!dq.isEmpty() && item > dq.peekLast()) dq.removeLast();
        dq.addLast(item);
        return item;
    }

    //删除最大值
    public Integer poll() {
        return dq.removeFirst();
    }

    //获取最大值
    public Integer peek() {
        return dq.peekFirst();
    }

    public int size() {
        return dq.size();
    }

    public boolean isEmpty() {
        return dq.isEmpty();
    }
}
