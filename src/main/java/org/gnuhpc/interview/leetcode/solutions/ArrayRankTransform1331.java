package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Copyright gnuhpc 2021/1/23
 */
public class ArrayRankTransform1331 {
    public int[] arrayRankTransform(int[] arr) {
        int[] temp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(temp);
        Map<Integer, Integer> map = new HashMap<>();
        int n = temp.length;
        int rank = 1;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(temp[i])) {
                map.put(temp[i], rank);
                rank++;
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }

    public int[] arrayRankTransform2(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : arr) set.add(num);

        Map<Integer, Integer> map = new HashMap<>();
        int i = 1;
        while (!set.isEmpty()) map.put(set.pollFirst(), i++);

        for (int j = 0; j < arr.length; j++) {
            arr[j] = map.get(arr[j]);
        }

        return arr;
    }
}
