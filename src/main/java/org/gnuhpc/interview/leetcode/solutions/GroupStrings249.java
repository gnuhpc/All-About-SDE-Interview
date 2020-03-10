package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

public class GroupStrings249 {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        if (strings == null || strings.length == 0) return res;

        Map<String, List<String>> hmap = new HashMap<>();
        int n = strings.length;
        for (int i = 0; i < n; i++) {
            String cur = strings[i];
            String cs = basic(cur);
            hmap.computeIfAbsent(cs, k -> new ArrayList<>()).add(cur);
        }
        return new ArrayList(hmap.values());
    }

    public String basic(String s) {
        if (s.equals("")) return "";
        char c = s.charAt(0);
        int n = s.length();
        int gap = c - 'a';
        char[] arr = new char[n];
        arr[0] = 'a';
        for (int i = 1; i < n; i++) {
            c = s.charAt(i);
            arr[i] = (char) (c - gap) < 'a' ? (char) (c - gap + 26) : (char) (c - gap);
        }
        return String.valueOf(arr);
    }

    // method 2 encode,与方法一相比，仅编码方式差异
    // 每个字符串字符都和前一个字符比较，差值序列
    private String basic2(String s) {
        if (s.length() == 1) {
            return "-1";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            sb.append((s.charAt(i) + 26 - s.charAt(i - 1)) % 26);
        }
        return sb.toString();
    }

    // method 3
    // 每个字符串字符都和该字符串的首字符比较，差值序列
    public String basic3(String s) {
        char c = s.charAt(0);
        int n = s.length();

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < n; i++) {//数字序列
            sb.append(String.valueOf((s.charAt(i) - c + 26) % 26) + ",");
        }
        return sb.toString();
    }

    public String basic4(String s) {

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int n = s.charAt(i) - s.charAt(i - 1);
            if (n >= 0) sb.append(n + "");
            else sb.append(n + 26 + "");
        }

        return sb.toString();

    }
}
