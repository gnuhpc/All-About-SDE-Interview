package org.gnuhpc.interview.leetcode.solutions;

import java.util.Stack;

/**
 * Copyright gnuhpc 2020/6/15
 */
public class IsValid591 {
    public boolean isValid(String code) {
        if (!(code.startsWith("<") && code.endsWith(">"))) {
            return false;
        }
        char[] chars = code.toCharArray();
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < chars.length) {
            if (chars[i] != '<') {
                //字符不在闭合标签之内，不合法
                if (stack.isEmpty()) {
                    return false;
                } else {
                    i++;
                }

            } else {
                // <>这种是非法的
                if (i + 1 == chars.length) {
                    return false;
                }
                if (chars[i + 1] == '!') {//为cdata标签
                    if (stack.isEmpty() || i + 1 + 7 >= chars.length) {
                        return false;
                    }
                    if (!code.startsWith("<![CDATA[", i)) {
                        return false;
                    }
                    int index = code.indexOf("]]>", i + 9);
                    if (index < 0) {
                        return false;
                    }
                    i = index + 3;
                } else if (chars[i + 1] == '/') {//为结束标签
                    //没有起始标签，即不合法
                    if (stack.isEmpty()) {
                        return false;
                    }
                    int index = code.indexOf(">", i + 2);
                    if (index < 0) {
                        return false;
                    }
                    //长度在范围 [1,9] 之外，即不合法
                    if (index - (i + 2) < 1 || index - (i + 2) > 9) {
                        return false;
                    }
                    for (int j = i + 2; j < index; j++) {
                        //含有非大写字母，不合法
                        if (chars[j] < 'A' || chars[j] > 'Z') {
                            return false;
                        }
                    }
                    //当前结束标签与上一个起始标签不匹配，不合法
                    if (!stack.pop().equals(code.substring(i + 2, index))) {
                        return false;
                    }
                    i = index + 1;
                } else {
                    //为起始标签
                    int index = code.indexOf(">", i + 1);
                    if (index < 0) {
                        return false;
                    }
                    //长度在范围 [1,9] 之外，即不合法
                    if (index - (i + 1) < 1 || index - (i + 1) > 9) {
                        return false;
                    }
                    for (int j = i + 1; j < index; j++) {
                        //含有非大写字母，不合法
                        if (chars[j] < 'A' || chars[j] > 'Z') {
                            return false;
                        }
                    }
                    //非第一个起始标签且不在标签之内，不合法，比如"<A></A><B></B>"
                    if (i > 0 && stack.isEmpty()) {
                        return false;
                    }
                    stack.push(code.substring(i + 1, index));
                    i = index + 1;
                }
            }
        }
        return stack.isEmpty();
    }
}
