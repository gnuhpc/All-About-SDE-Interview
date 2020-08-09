package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 2020/8/5
 */
public class NextGreaterElement556 {
    public int nextGreaterElement(int n) {
        String num = new StringBuilder(String.valueOf(n)).reverse().toString();

        Deque<Integer> s = new LinkedList<>();

        char[] arr = num.toCharArray();
        int i = 0;

        int idx = -1;

        while (i < arr.length) {
            if (s.isEmpty() || arr[i] >= arr[s.peek()]) {
                s.push(i);
                i++;
            } else {

                while (!s.isEmpty()) {
                    idx = s.pop();
                    char c = arr[idx];
                    arr[idx] = arr[i];
                    arr[i] = c;
                    i = idx;
                }

                break;

            }
        }

        if (idx == -1) return -1;


        return Integer.valueOf(
                new StringBuilder(String.valueOf(arr))
                        .reverse().toString());
    }

    @Test
    public void test() {
        System.out.println(nextGreaterElement(230241));
    }
}
