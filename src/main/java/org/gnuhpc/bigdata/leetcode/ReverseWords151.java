package org.gnuhpc.bigdata.leetcode;

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

}
