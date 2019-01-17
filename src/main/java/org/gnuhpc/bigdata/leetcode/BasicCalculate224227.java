package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BasicCalculate224227 {

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
        Stack<Integer> stack = new Stack<>();
        for (String str : postfix) {
            if (isLetterOrDigit(str)) {
                stack.push(Integer.parseInt(str));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(applyOp(str.charAt(0),a,b));
            }
        }
        return stack.isEmpty() ? 0 : stack.pop();
    }

    public String[] infix2postfix(String expr) {
        String[] infix = toStringArray(expr);
        List<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>(); // precedence strictly-increasing stack
        for (String s : infix) {
            if (isLetterOrDigit(s)) {//如果是字母或者数字，则添加到list中
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



    //方法2：两个stack
    public int calculate2(String expression){
        String[] tokenArray = toStringArray(expression);

        // Stack for numbers: 'profits'
        Stack<Integer> values = new Stack<Integer>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        for (String s:tokenArray)
        {
            // Current token is a number, push it to stack for numbers
            if (isLetterOrDigit(s))
            {
                values.push(Integer.valueOf(s));
            } else{
                char token = s.charAt(0);

                // Current token is an opening brace, push it to 'ops'
                if (token == '(')
                    ops.push(token);

                    // Closing brace encountered, solve entire brace
                else if (token == ')')
                {
                    while (ops.peek() != '(') doOps(values,ops);
                    ops.pop();
                }

                // Current token is an operator.
                else if (token == '+' || token == '-' ||
                         token == '*' || token  == '/') {
                    // While top of 'ops' has same or greater precedence to current
                    // token, which is an operator. Apply operator on top of 'ops'
                    // to top two elements in profits stack
                    while (!ops.empty() &&
                            precedence(String.valueOf(token))<=precedence(String.valueOf(ops.peek()))
                            ){
                        doOps(values, ops);
                    }

                    // Push current token to 'ops'.
                    ops.push(token);
                }
            }

        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining profits
        while (!ops.empty()){
            doOps(values, ops);
        }

        // Top of 'profits' contains result, return it
        return values.pop();
    }

    private void doOps(Stack<Integer> stack, Stack<Character> ops) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(applyOp(ops.pop(), a,b));
    }

    // Sample utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    private int applyOp(char op, int a, int b)
    {
        switch (op)
        {
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
                }  //其次判断c不是空格，那就是运算符
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

    public boolean isLetterOrDigit(String s) {
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            if (!(ch >= 48 && ch <= 57 || ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122 || ch=='-')) {
                return false;
            }
        }
        return true;
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
//        calculate("(21+1)*2-5");
//        calculate2("(21+1)*2-5");
        calculate2("(1+(4+5+2)-3)+(6+8)");
    }
}
