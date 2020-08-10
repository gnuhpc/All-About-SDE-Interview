package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-8-10
 */
public class ReverseWords151 {
    public String reverseWords(String s) {
        String[] res = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = res.length - 1; i >= 0; i--) {
            if (!res[i].equals("")) {
                sb.append(res[i]);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    @Test
    public void test() {
        System.out.println(reverseWords("hello   world!  "));
    }

}
