package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.Stack;

public class EvalRPN150 {
    @Test
    public void test() {
        String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        evalRPN2(tokens);
    }

    public int evalRPN(String[] tokens) {
        int returnValue = 0;
        String operators = "+- * /";
        Stack<String> stack = new Stack<String>();
        for (String t : tokens) {
            if (!operators.contains(t)) { //push to stack if it is a number
                stack.push(t);
            }
            else {//pop numbers from stack if it is an operator
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                switch (t) {
                    case "+":
                        stack.push(String.valueOf(a + b));
                        break;
                    case "-":
                        stack.push(String.valueOf(b - a));
                        break;
                    case "*":
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stack.push(String.valueOf(b / a));
                        break;
                }
            }
        }
        returnValue = Integer.valueOf(stack.pop());
        return returnValue;

    }

    /*
    Method 2: recursive, 倒着进行递归操作的，非常巧妙，N为目前遍历的下标
     */

    private int N = -1;

    public int evalRPN2(String[] tokens) {

        if (N == -1)
            N = tokens.length - 1;
        String s = tokens[N--];
        char c = s.charAt(0);
        //c必须是操作符
        if (s.length() == 1 && "+-*/".indexOf(c) != -1) {
            int a = evalRPN2(tokens);
            int b = evalRPN2(tokens);
            switch (c) {
                case '+':
                    return a + b;
                case '-':
                    return b - a;
                case '*':
                    return a * b;
                case '/':
                    return b / a;
                default:
                    break;
            }
        }
        return Integer.parseInt(s);
    }


}
