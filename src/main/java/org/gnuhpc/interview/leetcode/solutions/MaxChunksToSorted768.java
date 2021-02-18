package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 2020/5/13
 */
public class MaxChunksToSorted768 {
    // Testcase: 2,4,5,3,1
    //一个块的最大值不会超过块后边紧跟着的数, 用单调栈记录每个块的最大值即可，有几个区块最大值就有几个
    //单调递减栈，769通解
    //https://leetcode-cn.com/problems/max-chunks-to-make-sorted-ii/solution/leetcode-768-wo-gan-jio-jiang-de-ying-gai-xiang-xi/
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> s = new LinkedList<>();

        for (int n : arr) {
            //数字大于栈顶，说明可以分块了
            if (s.isEmpty() || n >= s.peek()) s.push(n);
            else {//如果数字小于块最大值，就要往前倒，看看哪些可以成一块
                int max = s.pop();
                while (!s.isEmpty() && n < s.peek()) s.pop();
                s.push(max);
            }
        }

        return s.size(); //里面存的是每个块中的最大值
    }

    @Test
    public void test() {
        System.out.println(maxChunksToSorted(new int[]{2, 4, 5, 3, 1}));
    }
}
