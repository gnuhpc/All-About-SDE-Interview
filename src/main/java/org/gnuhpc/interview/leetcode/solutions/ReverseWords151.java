package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-8-10
 */
public class ReverseWords151 {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");//其实性能不如以空格为分割，然后判断不是空格来的快
        StringBuilder ans = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            ans.append(words[i].trim());
            if (i != 0) {
                ans.append(" ");
            }
        }
        return ans.toString();
    }

    @Test
    public void test() {
        System.out.println(reverseWords("hello   world!  "));
    }

    // add by tina,首尾交换
    public String reverseWords2(String s) {
        if (s == null || s.length() == 0) return s;
        String[] strs = s.trim().split("\\s+");
        for (int i = 0, j = strs.length - 1; i < j; i++, j--) {
            String temp = strs[i];
            strs[i] = strs[j];
            strs[j] = temp;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]).append(" ");
        }
        return sb.toString().trim();

    }

}
