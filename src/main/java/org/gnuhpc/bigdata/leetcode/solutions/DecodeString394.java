package org.gnuhpc.bigdata.leetcode.solutions;

import com.google.inject.internal.util.$ToStringBuilder;
import jdk.nashorn.internal.ir.debug.NashornTextifier;
import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Copyright gnuhpc 2019/9/30
 */
public class DecodeString394 {
    /*
    Method1: recursion
     */
    Map<Integer, Integer> matchMap = new HashMap<>();

    public String decodeString(String s) {
        match(s);
        return decode(s, 0, s.length());
    }

    private String decode(String s, int start, int end) {
        StringBuilder resSb = new StringBuilder();
        char[] arr = s.toCharArray();

        int time = 0;
        for (int i = start; i < end; i++) {
            char c = arr[i];
            if (Character.isDigit(c)) {
                time = time * 10 + (c - '0');
            }
            if (c == '[') {
                resSb.append(
                        makeDup(
                                time,
                                decode(s, i + 1, matchMap.get(i) + 1)
                        )
                );
                time = 0;
                i = matchMap.get(i);
                continue;
            }

            if (Character.isLetter(c)) {
                resSb.append(c);
            }
        }

        return resSb.toString();
    }

    private void match(String s) {
        Deque<Integer> stack = new LinkedList<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];

            if (c == '[') stack.push(i);
            if (c == ']') matchMap.put(stack.pop(), i);
        }
    }

    private String makeDup(int times, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(s);
        }

        return sb.toString();
    }

    /*
    Method2 : Without construct match  map
     */

    public String decodeString2(String s) {
        String ans = "", times = "";
        int idx = 0, n = s.length();
        if (n == 0) return "";

        while (idx < n) {
            char c = s.charAt(idx);

            if (c >= '0' && c <= '9') times += c;
            else if (c == '[') {

                int toMatch = 1, end = idx + 1;
                //找到最后一个匹配的括号
                while (end < n && toMatch > 0) {
                    char cc = s.charAt(end);
                    if (cc == '[') toMatch++; //记录待匹配左括号的个数
                    if (cc == ']') toMatch--;
                    end++;
                }
                String sub = s.substring(idx + 1, end - 1);
                // System.out.println(sub);
                String res = decodeString(sub);
                int timeInt = Integer.parseInt(times);
                ans += makeDup(timeInt, res);
                idx = end - 1;
                times = "";
            }
            else {
                ans += c;
            }
            idx++;
        }
        return ans;
    }

    @Test
    public void test() {
//        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
    }
}
