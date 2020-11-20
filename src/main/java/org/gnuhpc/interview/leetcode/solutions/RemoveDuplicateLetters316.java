package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 19-8-12
 */
public class RemoveDuplicateLetters316 {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;

        int[] map = new int[26];
        char[] strArr = s.toCharArray();

        for (char c : strArr) {
            map[c - 'a']++;
        }

        int minPos = 0;

        for (int i = 0; i < s.length(); i++) {
            if (strArr[i] < strArr[minPos]) minPos = i; //找到字典序更小的就更新
            //如果都用完了某个字母，则这个就是当前最小的开头, 因为再往后有些字母就没有了
            if (--map[strArr[i] - 'a'] == 0) break;
        }

        //递归进行，需要把这个已经列入结果的字母去掉
        return strArr[minPos] + removeDuplicateLetters(
                s.substring(minPos + 1).replace(
                        "" + strArr[minPos],
                        ""
                ));
    }


    /*
    Method2: 单调栈
     */
    public String removeDuplicateLetters2(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        Deque<Character> stack = new LinkedList<>();
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!stack.isEmpty() && stack.getLast() > c && count.get(stack.getLast()) > 0) {
                    seen.remove(stack.removeLast());
                }
                stack.addLast(c);
                seen.add(c);
            }
            count.put(c, count.get(c) - 1);
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.removeFirst());
        }
        return res.toString();
    }

    @Test
    public void test() {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
//        System.out.println(removeDuplicateLetters("baab"));
//      System.out.println(removeDuplicateLetters("bbcaac"));
    }
}
