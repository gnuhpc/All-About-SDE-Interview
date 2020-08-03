package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/8/2
 */
public class FindWords500 {
    public String[] findWords(String[] words) {
        Set<Character> set1 = new HashSet<Character>();
        Set<Character> set2 = new HashSet<Character>();
        Set<Character> set3 = new HashSet<Character>();
        String line1 = "qwertyuiopQWERTYUIOP";
        String line2 = "asdfghjklASDFGHJKL";
        String line3 = "zxcvbnmZXCVBNM";
        //将每一行的字符都放入对应的set中
        for (char c1 : line1.toCharArray()) {
            set1.add(c1);
        }
        for (char c2 : line2.toCharArray()) {
            set2.add(c2);
        }
        for (char c3 : line3.toCharArray()) {
            set3.add(c3);
        }
        //开始对比
        String[] newWords = new String[words.length];//存储结果
        int k = 0;
        for (int i = 0; i < words.length; i++) {
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (set1.contains(c)) {//此处不易用add来判断，set中一个元素都别放进去
                    count1++;
                }
                if (set2.contains(c)) {
                    count2++;
                }
                if (set3.contains(c)) {
                    count3++;
                }
            }
            if (count1 == words[i].length() || count2 == words[i].length() || count3 == words[i].length()) {
                newWords[k] = words[i];
                k++;
            }
        }
        String[] res = new String[k];//由于newWords固定长度长于等于实际输出长度
        for (int i = 0; i < k; i++) {
            res[i] = newWords[i];
        }
        return res;
    }
}
