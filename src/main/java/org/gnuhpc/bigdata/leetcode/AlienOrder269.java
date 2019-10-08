package org.gnuhpc.bigdata.leetcode;

import com.google.common.base.Strings;
import com.google.inject.internal.util.$ImmutableMap;
import com.google.inject.internal.util.$ObjectArrays;
import org.junit.Test;
import scala.math.Ordering;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/2
 */
public class AlienOrder269 {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        if (words.length == 0) {
            return sb.toString();
        }
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                degree.put(ch, 0);
            }
        }

        //注意字典序,每个单词内部的字母是没有顺序的!
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            int length = Math.min(cur.length(), next.length());
            for (int j = 0; j < length; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = map.getOrDefault(c1, new HashSet<>());
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (char c : degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            char c = queue.remove();
            sb.append(c);
            if (map.containsKey(c)) {
                for (char ch : map.get(c)) {
                    degree.put(ch, degree.get(ch) - 1);
                    if (degree.get(ch) == 0) {
                        queue.add(ch);
                    }
                }
            }
        }
        if (sb.length() != degree.size()) {
            return "";
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(alienOrder(new String[]{
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"}));
    }
}
