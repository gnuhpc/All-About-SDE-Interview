package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.*;

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
            }
            else if (c == ']') {
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

            }
            else if (c <= '9' && c >= '0') {
                tmpNum = tmpNum * 10 + Character.getNumericValue(c);
            }
            else {
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
        }
        catch (NumberFormatException e) {
            return true;
        }
    }


    //Method 2: recursive
    public String expressionExpand2(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        char[] charArray = s.toCharArray();
        int tmpNum = 0;

        Map<Integer, Integer> pMap = buildMap(charArray);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '[') {
                int matchPos = pMap.get(i);
                sb.append(expand(s.substring(i + 1, matchPos), tmpNum));
                i = matchPos;
                tmpNum = 0;
            }
            else if (c == ']') {
                continue;
            }
            else if (c <= '9' && c >= '0') {
                tmpNum = tmpNum * 10 + Character.getNumericValue(c);
            }
            else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private Map<Integer, Integer> buildMap(char[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '[') {
                stack.push(i);
            }
            else if (arr[i] == ']') {
                map.put(stack.pop(), i);
            }
        }

        return map;
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
//        System.out.println(expressionExpand2("4[ac]dy"));
    }
}
