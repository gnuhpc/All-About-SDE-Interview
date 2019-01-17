package org.gnuhpc.bigdata.leetcode;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

interface NestedInteger {

           // @return true if this NestedInteger holds a single integer, rather than a nested list.
           public boolean isInteger();

           // @return the single integer that this NestedInteger holds, if it holds a single integer
           // Return null if this NestedInteger holds a nested list
           public Integer getInteger();

           // @return the nested list that this NestedInteger holds, if it holds a nested list
           // Return null if this NestedInteger holds a single integer
           public List<NestedInteger> getList();
}

public class NestedIterator341 implements Iterator<Integer>{
    private Deque<NestedInteger> stack;

    public NestedIterator341(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        flattenList(nestedList);
    }

    @Override
    public Integer next() {
        return hasNext() ? stack.pop().getInteger() : null;
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (stack.peek().isInteger()) return true;
            flattenList(stack.pop().getList());
        }
        return false;
    }

    private void flattenList(List<NestedInteger> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }
    }

}
