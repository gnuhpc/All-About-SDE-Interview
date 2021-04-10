package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 2021/4/1
 */
public class Clumsy1006 {
    public int clumsy(int N) {
        //存放操作数
        Deque<Integer> stack = new LinkedList<>();
        //先将第一个数N存入栈中
        stack.push(N);
        //N自减1
        //index作为判断当前符号的依据
        int index = 1;
        //当还有数未参加运算时，循环
        while (--N > 0) {
            /*利用 index 除以4的余数判断当前操作符：*、/ 直接运算；+、- 将数入栈*/
            if (index % 4 == 1) stack.push(stack.pop() * (N));
            else if (index % 4 == 2) stack.push(stack.pop() / (N));
            else if (index % 4 == 3) stack.push((N));
            else stack.push(-(N));
            index++;
        }
        //最后将栈中各个整数相加即为结果
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
