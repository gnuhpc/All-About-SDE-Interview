package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MinimumAbsDifference1200 {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] < min) {
                min = arr[i] - arr[i - 1];
            }
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == min) {
                List<Integer> list = new LinkedList<>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                ans.add(list);
            }
        }
        return ans;
    }
}
