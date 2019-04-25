package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class ExpressionExpand {
    //Method 1: Non-recursive
    public String expressionExpand(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        Deque<String> stack = new ArrayDeque<>();

        char[] charArray = s.toCharArray();

        int tmpNum = 0;

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '[') {
                stack.push(String.valueOf(tmpNum));
                tmpNum = 0;
            } else if (c == ']') {
                LinkedList<String> tmpList = new LinkedList<>();
                while (!stack.isEmpty() && isNotInt(stack.peek())) {
                    tmpList.add(stack.pop());
                }

                StringBuilder sb = new StringBuilder();
                while (!tmpList.isEmpty()) {
                    sb.append(tmpList.pollLast());
                }
                String str = sb.toString();

                if (stack.isEmpty()) return str;
                int times = Integer.valueOf(stack.pop());

                StringBuilder tmpSB = new StringBuilder();
                for (int j = 0; j < times; j++) {
                    tmpSB.append(str);
                }

                stack.push(tmpSB.toString());

            } else if (c <= '9' && c >= '0') {
                tmpNum = tmpNum * 10 + Character.getNumericValue(c);
            } else {
                stack.push(String.valueOf(c));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        return sb.toString();
    }

    private boolean isNotInt(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }


    //Method 2: Non-recursive
    public String expressionExpand2(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        char[] charArray = s.toCharArray();
        int tmpNum = 0;

        int times = -1;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '[') {
                int nextLeftPos = s.substring(i+1).indexOf("]") + i + 1;
                sb.append(expand(s.substring(i+1,nextLeftPos),tmpNum));
                i = nextLeftPos + 1;
            } else if (c == ']') {
                continue;
            } else if (c <= '9' && c >= '0') {
                tmpNum = tmpNum * 10 + Character.getNumericValue(c);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private String expand(String s, int times) {
        String str = expressionExpand2(s);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }

        return sb.toString();
    }

    @Test
    public void test() {
//        System.out.println(expressionExpand2("abc3[a]"));
//        System.out.println(expressionExpand2("3[a]"));
        System.out.println(expressionExpand2("3[2[ad]3[pf]]xyz"));
    }
}
