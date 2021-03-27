package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.gnuhpc.interview.leetcode.utils.Utils.isInteger;

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
            if (isInteger(str)) {
                stack.push(Integer.parseInt(str));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(applyOp(str.charAt(0), a, b));
            }
        }
        return stack.isEmpty() ? 0 : stack.pop();
    }


    public String[] infix2postfix(String expr) { // 后缀表达式 转换方法
        String[] infix = toStringArray(expr);
        List<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>(); // precedence strictly-increasing stack
        for (String s : infix) {
            if (isInteger(s)) {//如果是字母或者数字，则添加到list中
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
    private int applyOp(char op, int a, int b) {
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

    public int calculate227(String s) {
        int pre = 0, curr = 0, sign = 1, op = 0, num = 0;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
                if (i == s.length() - 1 || !Character.isDigit(s.charAt(i + 1))) {
                    curr = (op == 0 ? num : (op == 1 ? curr * num : curr / num));
                }

            } else if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                op = (s.charAt(i) == '*' ? 1 : -1);
                num = 0;

            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                pre += sign * curr;
                sign = (s.charAt(i) == '+' ? 1 : -1);
                op = 0;
                num = 0;
            }
        }

        return pre + sign * curr;
    }

    public int calculate2272(String s) {
        //字符串转数组。
        char[] a = s.toCharArray();
        //创建栈
        Stack<Integer> stack = new Stack<>();
        // n 为数组长度， num 为每次要进栈的元素， b 为判断要进栈元素的+-*/。
        int n = a.length;
        int num = 0;
        int b = 1;
        //常规循环，遍历数组
        for (int i = 0, j = 0; i < n; i++) {
            if (a[i] == '+') {
                // b 赋值为正。
                b = 1;
            } else if (a[i] == '-') {
                //b 赋值为负。
                b = -1;
            } else if (a[i] == ' ') {
                //啥也不执行
            } else if (a[i] == '*') {
                // b 赋值 2 ，标识*法。
                b = 2;
            } else if (a[i] == '/') {
                // b 赋值 3 ，标识/法。
                b = 3;
            } else {
                //转换为相应的十进制数。
                while (i < n && Character.isDigit(a[i])) {
                    num = num * 10 + a[i] - '0';
                    i++;
                }
                //这个i-1操作，是防止指针跳跃，溢出。
                i--;
                //判断进行什么操作
                if (b == 2) {
                    //进行乘法，下个数字与栈顶元素相乘，覆盖栈顶。
                    stack.push(stack.pop() * num);
                } else if (b == 3) {
                    //进行除法，下个数字与栈顶元素相除，覆盖栈顶。
                    stack.push(stack.pop() / num);
                } else {
                    //赋值正负。
                    stack.push(num * b);
                }
                //重新置0。
                num = 0;
            }
        }
        //出栈累加，得到答案。
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    /*

    Only 5 possible input we need to pay attention:

 1. digit: it should be one digit from the current number
 2. '+': number is over, we can add the previous number and start a new number
 3. '-': same as above
 4. '(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
 5. ')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.


Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.
     */
    public int calculate224(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
            }
        }
        if (number != 0) result += sign * number;
        return result;
    }

    public int calculate2241(String s) {
        int[] index = new int[1];
        return helper(s, index);
    }
    private int helper(String s, int[] index) {
        int res = 0;
        int num = 0;
        int sign = 1;
        while (index[0] < s.length()) {
            char c = s.charAt(index[0]);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                res += sign * num;
                num = 0;
                sign = c == '+' ? 1 : -1;
            } else if (c == '(') {
                index[0]++;
                res += sign * helper(s, index);
                sign = 1;
            } else if (c == ')') {
                res += sign * num;
                return res;
            }
            index[0]++;
        }
        return res + sign * num;
    }

    @Test
    public void test() {
//        calculate("(21+1)*2-5");
//        calculate2("(21+1)*2-5");
        System.out.println(calculate2241("(1+(4+5+2)-3)+(6+81)"));
    }
}
