package org.gnuhpc.bigdata.leetcode;

import java.util.*;

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

//Method: Recursive Method
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

//迭代直接准备好数据，内存消耗更大，但是后边在遍历的时候就使得next和hasnext方法解耦
class NestedIterator2 implements Iterator<Integer> {
    List<Integer> list = new ArrayList<>();
    int pos = 0;//current position
    public NestedIterator2(List<NestedInteger> nestedList) {
        //use arrayList to store nestedList
        traverse(nestedList);
    }
    public void traverse(List<NestedInteger> nestedList){
        if(nestedList == null) return;
        for(NestedInteger e: nestedList){
            if(e.isInteger()){
                list.add(e.getInteger());
            }else{
                traverse(e.getList());//do recursion when meeting list element
            }
        }
    }
    @Override
    public Integer next() {
        return list.get(pos++);
    }

    @Override
    public boolean hasNext() {
        return pos < list.size();
    }
}
