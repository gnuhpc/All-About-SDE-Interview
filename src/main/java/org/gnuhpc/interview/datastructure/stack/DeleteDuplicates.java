package org.gnuhpc.interview.datastructure.stack;

import org.junit.Test;

import java.util.*;

public class DeleteDuplicates {
    public int[] deleteDuplicates(int[] array) {
        Deque<Integer> s = new LinkedList<>();

        int i = 0;
        while (i < array.length) {
            int e = array[i];
            if (!s.isEmpty() && s.peek() == e) {
                while (i < array.length && s.peek() == array[i]) {
                    i++;
                }
                s.pop();
            } else {
                s.push(e);
                i++;
            }
        }

        int[] result = new int[s.size()];
        int j = s.size() - 1;

        while (!s.isEmpty()) {
            result[j--] = s.pop();
        }

        return result;
    }

    @Test
    public void test() {
        int[] array = new int[]{1, 9, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5};

        for (int i : deleteDuplicates(array)) {
            System.out.println(i);
        }
    }
}
