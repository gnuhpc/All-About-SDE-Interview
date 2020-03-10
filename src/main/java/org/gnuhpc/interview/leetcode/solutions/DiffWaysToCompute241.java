package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiffWaysToCompute241 {
    private Map<String, List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        // 记录已经计算出来的字符串对应的值，避免重复计算。
        if (map.containsKey(input)) return map.get(input);
        List<Integer> list = new ArrayList<>();
        int len = input.length();
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {  // 出现运算符号，递归求解前半段和后半段。
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1, input.length()));
                // -1   =>  left:[[0]]  right:[[1]]

                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                list.add(l + r);
                                break;
                            case '-':
                                list.add(l - r);
                                break;
                            case '*':
                                list.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        // 单独一个数字的情况 (可能出现多位数)
        if (list.size() == 0) {
            if (input.isEmpty()) list.add(0);
            else
                list.add(Integer.valueOf(input));
        }

        map.put(input, list);
        return list;
    }

    @Test
    public void test() {
        System.out.println(diffWaysToCompute("-2-1-1"));
    }

}
