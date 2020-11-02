package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

//这道题的关键是要解决如果最小值被pop掉了，那么上一个最小值是什么?
//该解法就是在新的最小值产生时再次先记录上一个最小值，在pop的时候如果发现顶端是最小值，那么需要pop两次
//（第二次是因为之前冗余放置了prev min），在第二次pop的时候记得更新min，从prev变为现在的min
public class MinStack1552 {
    int min = Integer.MAX_VALUE;
    Deque<Integer> stack = new LinkedList<>();

    // push -2,0,-3,3 -> -2  0  -2  -3  3
    public void push(int x) {
        if (x <= min) {
            stack.push(min);//先放入上一个的min
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.peek() == min) {//如果等于最小值说明该值是记录的上次最小值
            stack.pop(); //注意顺序
            min = stack.pop();// 后边跟着的一定是上一个min
        } else {
            stack.pop();
        }

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
