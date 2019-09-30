package org.gnuhpc.bigdata.datastructure.stack.jdkimpl;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StackTest {
    @Test
    public void whenPushToStackThenShouldRetrieveIt() {
        //推荐使用这个collections,注意是Deque 或者是linkedlist也行
        Deque<String> stack = new ArrayDeque<>();

        stack.push("a");
        assertEquals(stack.size(),1);

        String element = stack.pop();
        assertEquals(element,"a");
        assertTrue(stack.isEmpty());
    }

    @Test
    public void whenPushToStackThenShouldRetrieveElementsInTheProperOrder() {
        //given
        Stack<String> stack = new Stack<>();

        //when
        stack.push("a");
        stack.push("b");
        stack.push("c");

        //then
        assertEquals(stack.pop(),"c");
        assertEquals(stack.pop(),"b");
        assertEquals(stack.pop(),"a");
    }

    @Test
    public void whenPushAndPeekElementShouldNotRemoveIT() {
        //given
        Stack<String> stack = new Stack<>();

        //when
        stack.push("a");

        //then
        assertEquals(stack.size(),1);
        assertEquals(stack.peek(),"a");
        assertEquals(stack.size(),1);
    }
}
