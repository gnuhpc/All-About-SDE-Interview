package org.gnuhpc.interview.datastructure.queue;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class InterLeavingQueue {
    public void interLeavingQueue(Queue<Integer> q) {
        assert (q.size() % 2 == 0);

        Deque<Integer> s = new LinkedList<>();

        int half = q.size() / 2;

        for (int i = 0; i < half; i++) {
            s.push(q.remove());
        }

        while (!s.isEmpty()) {
            q.add(s.pop());
        }

        for (int i = 0; i < half; i++) {
            q.add(q.remove());
        }

        for (int i = 0; i < half; i++) {
            s.push(q.remove());
        }

        while (!s.isEmpty()) {
            q.add(s.pop());
            q.add(q.remove());
        }
    }

    @Test
    public void test() {
        Queue<Integer> q = new LinkedList<>();
        q.add(11);
        q.add(12);
        q.add(13);
        q.add(14);
        q.add(15);
        q.add(16);
        q.add(17);
        q.add(18);
        q.add(19);
        q.add(20);

        interLeavingQueue(q);

    }
}
