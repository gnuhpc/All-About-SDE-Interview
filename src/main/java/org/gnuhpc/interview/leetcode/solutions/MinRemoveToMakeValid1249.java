package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 2020/7/5
 */
public class MinRemoveToMakeValid1249 {
    public String minRemoveToMakeValid(String s) {

        StringBuilder res = new StringBuilder(s);
        int toMatch = 0;
        for (int i = 0; i < res.length(); ++i)
            if (res.charAt(i) == '(')
                ++toMatch;
            else if (res.charAt(i) == ')' && --toMatch < 0) {
                toMatch = 0;
                res.deleteCharAt(i--);
            }

        while (toMatch > 0) {
            res.deleteCharAt(res.lastIndexOf("("));
            toMatch--;
        }

        return res.toString();
    }


    @Test
    public void test() {
        System.out.println(minRemoveToMakeValid(")((c)d()(l"));
    }


}
