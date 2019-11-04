package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

public class MaxStack716 {
    Deque<Integer> stack;
    TreeMap<Integer, Integer> maxCache;
    /** initialize your data structure here. */
    public MaxStack716() {
        stack = new LinkedList<>();
        maxCache = new TreeMap<>();
    }

    public void push(int x) {
        stack.push(x);
        if (maxCache.isEmpty()){
            maxCache.put(x,1);
        } else{
            if (maxCache.containsKey(x)) maxCache.put(x,maxCache.get(x)+1);
            else{
                maxCache.put(x,1);
            }
        }
    }

    public int pop() {
        if (stack.isEmpty()) return -1;
        int res = stack.pop();
        reduceKeyOfMaxCache(res);
        return res;
    }

    private void reduceKeyOfMaxCache(int key) {
        int newVal = maxCache.get(key) - 1;
        if (newVal == 0) maxCache.remove(key);
        else maxCache.put(key, newVal);
    }

    public int top() {
        if (stack.isEmpty()) return -1;
        return stack.peek();
    }

    public int peekMax() {
        return maxCache.lastKey();
    }

    public int popMax() {
        int res = peekMax();
        stack.removeFirstOccurrence(res); //注意stack中push是不断在list的头部加入。list头部即是堆顶
        reduceKeyOfMaxCache(res);

        return res;
    }

    @Test
    public void test(){
        MaxStack716 stack = new MaxStack716();

        stack.push(3);
        stack.push(5);
        stack.push(1);
        stack.push(5);

        System.out.println(stack.top());
        System.out.println(stack.popMax());
        System.out.println(stack.top());
        System.out.println(stack.popMax());
        System.out.println(stack.pop());
        System.out.println(stack.top());
    }
}
