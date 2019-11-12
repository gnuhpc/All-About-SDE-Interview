package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

public class MinMutation433 {
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;

        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;

        char[] charSet = new char[]{'A', 'C', 'G', 'T'};

        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                String curr = queue.poll();

                char[] currArray = curr.toCharArray();
                for (int i = 0; i < currArray.length; i++) {
                    char old = currArray[i];
                    for (char c : charSet) {
                        currArray[i] = c;
                        String next = new String(currArray);
                        if (!bankSet.contains(next)||visited.contains(next)) {
                            continue;
                        }

                        if (next.equals(end)) return level;
                        visited.add(next);
                        queue.offer(next);
                    }
                    currArray[i] = old;
                }
            }
        }
        return -1;
    }


    @Test
    public void test() {
        System.out.println(minMutation("AAAACCCC", "CCCCCCCC", new String[]{"AAAACCCA", "AAACCCCA", "AACCCCCA", "AACCCCCC", "ACCCCCCC", "CCCCCCCC", "AAACCCCC", "AACCCCCC"}));
    }

}
