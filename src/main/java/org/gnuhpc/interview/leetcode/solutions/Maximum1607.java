package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2021/2/21
 */
public class Maximum1607 {
    public int maximum(int a, int b) {
        long al = a;
        long bl = b;
        // 如果 sign 为1，表示结果为负，b 大
        long sign = (((al - bl) >>> 63) & 1);
        int[] result = {a, b};
        return result[(int) sign];
    }

    public String mergeAlternately(String word1, String word2) {
        char[] res = new char[word1.length() + word2.length()];
        char[] array1 = word1.toCharArray();
        char[] array2 = word2.toCharArray();

        int j1 = 0, j2 = 0, i = 0;
        boolean isFirst = true;
        for (; i < res.length && j1 < array1.length && j2 < array2.length; i++) {
            if (isFirst) {
                res[i] = array1[j1++];
            } else {
                res[i] = array2[j2++];
            }
            isFirst = !isFirst;
        }

        if (j1 == array1.length) {
            while (j2 < array2.length) res[i++] = array2[j2++];
        } else {
            while (j1 < array1.length) res[i++] = array1[j1++];

        }

        return new String(res);
    }

    public int[] minOperations(String boxes) {
        int[] res = new int[boxes.length()];
        char[] arr = boxes.toCharArray();

        Set<Integer> posOne = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '1') posOne.add(i);
        }
        int oneCnt = posOne.size();
        for (int i = 0; i < res.length; i++) {
            int tmp = 0;
            for (int pos : posOne) {
                tmp += Math.abs(pos - i);
            }
            res[i] = tmp;
        }
        return res;
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        Deque<Integer> numsQ = new LinkedList<>();
        Deque<Integer> multipliersQ = new LinkedList<>();
        for (int num : nums) {
            numsQ.offer(num);
        }

        for (int multiplier : multipliers) {
            multipliersQ.offer(multiplier);
        }

        int res = 0;

        while (!multipliersQ.isEmpty()) {
            int firstNum = numsQ.peekFirst();
            int lastNum = numsQ.peekLast();

            if (firstNum * multipliersQ.peekFirst() > lastNum * multipliersQ.peekFirst()) {
                res += numsQ.pollFirst() * multipliersQ.pollFirst();
            } else {
                res += numsQ.pollLast() * multipliersQ.pollFirst();
            }
        }

        return res;
    }

    @Test
    public void test() {
        maximumScore(new int[]{-5, -3, -3, -2, 7, 1}, new int[]{-10, -5, 3, 4, 6});
    }

}
