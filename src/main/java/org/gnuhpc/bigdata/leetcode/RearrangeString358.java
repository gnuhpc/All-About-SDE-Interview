package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

public class RearrangeString358 {

    public String rearrangeString(String s, int k) {
        if (s == null || s.length() == 0) return "";
        if (k <= 1) return s;

        char[] arr = s.toCharArray();
        int n = arr.length;

        Map<Character, Integer> map = new HashMap<>();
        //TODO 注意这种表示Key-Value 的方式，不用自定义类
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (int i = 0; i < n; i++) {
            char c = arr[i];
            //TODO 注意这种map 的值累加的方法
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);
        }
        pq.addAll(map.entrySet());

        Deque<Map.Entry<Character, Integer>> queue = new ArrayDeque<>(k);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (pq.size() == 0) {
                return "";
            }
            Map.Entry<Character, Integer> entry = pq.remove();
            sb.append(entry.getKey());
            int newVal = entry.getValue() - 1;
            entry.setValue(newVal);

            //queue是暂存区，先进先出，到了size=k的时候就安全了，就一个一个的放回pq TODO
            queue.offer(entry);
            if (queue.size() == k) {
                Map.Entry<Character, Integer> poll = queue.poll();
                if (poll.getValue() > 0) {
                    pq.add(poll);
                }
            }
        }

        return sb.toString();
    }

    /*
    Method2
     */

    public String rearrangeString2(String s, int k) {
        if (k <=1)
            return s;

        //initialize the counter for each character
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        //sort the chars by frequency TODO
        Queue<Character> queue = new PriorityQueue<>((c1, c2) -> {
            if (map.get(c2).intValue() != map.get(c1).intValue()) {
                return map.get(c2) - map.get(c1);
            } else {
                return c1.compareTo(c2);
            }
        });

        for (char c : map.keySet())
            queue.offer(c);

        StringBuilder sb = new StringBuilder();

        int len = s.length();

        while (!queue.isEmpty()) {
            int cnt = Math.min(k, len);
            ArrayList<Character> temp = new ArrayList<Character>();

            for (int i = 0; i < cnt; i++) {
                if (queue.isEmpty())
                    return "";

                char c = queue.poll();
                sb.append(c);

                map.put(c, map.get(c) - 1);

                if (map.get(c) > 0) {
                    temp.add(c);
                }

                len--;
            }

            for (char c : temp)
                queue.offer(c);
        }

        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(rearrangeString("aaadbbcc", 2));
    }
}
