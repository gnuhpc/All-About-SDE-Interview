package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

//LC 385 是一般情况
public class ReorganizeString767 {
    public String reorganizeString(String S) {
        int len = S.length();
        Map<Character, Integer> map = new HashMap<>();
        char[] arr = S.toCharArray();
        for (int i = 0; i < len; i++) {
            char c = arr[i];
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > (len + 1) / 2) return "";
        }


        //sort the chars by frequency
        PriorityQueue<Character> queue = new PriorityQueue<>((c1, c2) -> {
            if (map.get(c2).intValue() != map.get(c1).intValue()) {
                return map.get(c2) - map.get(c1);
            } else {
                return c1.compareTo(c2);
            }
        });
        /*为什么要对Integer调用intValue函数
         * Integer i = new Integer(10);
         * Integer j = new Integer(10);
         * if (!(i == j)) {
         *     System.out.println("Surprise, doesn't match!");
         * }
         * if (i.intValue() == j.intValue()) {
         *     System.out.println("Cool, matches now!");
         * }
         */
        queue.addAll(map.keySet());

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {

            List<Character> temp = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                if (queue.isEmpty()) break;

                char c = queue.poll();
                sb.append(c);

                map.put(c, map.get(c) - 1);

                //如果这个字符还有没有用完的，则需要后边再塞回pq中，此刻先暂存
                if (map.get(c) > 0) {
                    temp.add(c);
                }
            }

            queue.addAll(temp);
        }
        return sb.toString();
    }


    @Test
    public void test() {
        // System.out.println(reorganizeString("aabbcc"));
        System.out.println(reorganizeString("aabbccd"));
        // System.out.println(reorganizeString("aaabbccc"));
    }
}
