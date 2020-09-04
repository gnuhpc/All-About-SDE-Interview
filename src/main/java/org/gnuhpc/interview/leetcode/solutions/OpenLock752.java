package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2020/8/30
 */
public class OpenLock752 {
    public int openLock(String[] deadends, String target) {
        if (target == null) return -1;
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        int times = -1;
        queue.offer("0000");
        while (!queue.isEmpty()) {
            times++;
            int size = queue.size();
            for (int t = 0; t < size; t++) {
                String cur = queue.poll();
                if (set.contains(cur)) continue;
                if (cur.compareTo(target) == 0) return times;
                set.add(cur);
                for (int i = 0; i < 4; i++) {
                    for (int j = -1; j < 2; j += 2) {
                        char[] temp = cur.toCharArray();
                        temp[i] = (char) ((temp[i] - '0' + j + 10) % 10 + '0');
                        queue.offer(new String(temp));
                    }
                }
            }

        }
        return -1;
    }
}
