package org.gnuhpc.bigdata.algorithm.recursion.resersestack;

import org.junit.Test;

import java.util.Stack;

public class ReverseStack {
    //注意泛型函数的定义方式，
    // 首先弹出最上层的元素，然后reserse下边的stack剩余元素
    // 最后将这个元素插入到新的stack底部
    // 这里有两个recursive 一个是reversestack，一个是insertatbottom
    public <E> void reverseStack(Stack<E> stack){
        if (stack.isEmpty()) return;
        E temp = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack,temp);
    }

    //这也是个recursive method,利用程序栈保存了临时变量
    private <E> void insertAtBottom(Stack<E> stack, E element){
        if (stack.isEmpty()){
            stack.push(element);
        } else {
            E popElement = stack.pop();//取出顶部变量就进行递归，暂存了此变量
            insertAtBottom(stack, element);
            stack.push(popElement);
        }
    }

    @Test
    public void test(){
        Stack<Integer> stack1 = new Stack<>();

        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);
        stack1.push(5);

        reverseStack(stack1);

        while (!stack1.isEmpty()) System.out.println(stack1.pop());
    }
}
