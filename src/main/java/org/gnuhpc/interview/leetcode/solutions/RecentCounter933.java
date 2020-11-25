package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class RecentCounter933 {
    Queue<Integer> q;
    public RecentCounter933() {
        q = new LinkedList();
    }

    public int ping(int t) {
        q.offer(t);//入队
        while (q.peek() < t-3000)
            q.poll();
        return q.size();
    }
}
