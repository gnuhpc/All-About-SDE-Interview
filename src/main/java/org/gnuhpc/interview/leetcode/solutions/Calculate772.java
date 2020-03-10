package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.gnuhpc.interview.leetcode.utils.Utils.isInteger;
import static org.gnuhpc.interview.leetcode.utils.Utils.isLong;

public class Calculate772 {
    // 首先转换为后缀表达式
    public int calculate(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }

        String[] postfix = infix2postfix(s);
        System.out.println(Arrays.toString(postfix));

        return calcResult(postfix);
    }

    private int calcResult(String[] postfix) {
        Stack<Long> stack = new Stack<>();
        for (String str : postfix) {
            if (isLong(str)) {
                stack.push(Long.parseLong(str));
            } else {
                long b = stack.pop();
                long a = 0;
                if (!stack.isEmpty()) a = stack.pop();

                stack.push(applyOp(str.charAt(0), a, b));
            }
        }

        long res = stack.isEmpty() ? 0 : stack.pop();
        return (int) res;
    }


    public String[] infix2postfix(String expr) { // 后缀表达式 转换方法
        String[] infix = toStringArray(expr);
        System.out.println(Arrays.toString(infix));
        List<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>(); // precedence strictly-increasing stack
        for (String s : infix) {
            if (isLong(s)) {//如果是字母或者数字，则添加到list中
                list.add(s);
            } else if (s.equals("(")) { //如果是左括号添加到stack中
                stack.push(s);
            } else if (s.equals(")")) { //如果是右括号则弹出stack中的元素直到左括号
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                } else {
                    return new String[]{"invalid expression"};
                }
            } else { //如果是操作符,则判断优先级，低优先级的弹出，高优先级的压入
                while (!stack.isEmpty() && precedence(s) <= precedence(stack.peek())) {
                    list.add(stack.pop());
                }
                stack.push(s);
            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        return list.stream().toArray(String[]::new);
    }

    // Sample utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    private long applyOp(char op, long a, long b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }


    public String[] toStringArray(String s) {
        List<String> list = new ArrayList<>();
        String num = "";
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {//如果是数字则向后追加
                num += String.valueOf(c);
            } else {  //不是的话，首先判断num是不是空
                if (!num.equals("")) {
                    list.add(num);
                    num = "";
                } else {
                    if (c == '-') {
                        if (list.isEmpty() || list.get(list.size() - 1).equals("("))
                            list.add("0");
                    }
                }

                //其次判断c不是空格，那就是运算符
                if (c != ' ') {
                    list.add(String.valueOf(c));
                }
            }
        }

        //
        if (!num.equals("")) {
            list.add(num);
        }

        return list.stream().toArray(String[]::new);
    }

    public int precedence(String s) {
        int prec = 0;
        switch (s) {
            case "(":
                prec = 0;
                break;
            case "+":
                prec = 1;
                break;
            case "-":
                prec = 1;
                break;
            case "*":
                prec = 2;
                break;
            case "/":
                prec = 2;
                break;
            default:
                break;
        }
        return prec;
    }

    @Test
    public void test() {
        //  System.out.println(calculate("(   ((  (8+3) *(  4  -  10)  ) -   2  ) +(   (5  +   (   10/  2  )  )+   (  ( 9   + 5   )+(   2  +  2 ) )  )   )"));
//          System.out.println(calculate("0-2147483648"));
        System.out.println(calculate("-1+4*3/3/3"));

    }

}
