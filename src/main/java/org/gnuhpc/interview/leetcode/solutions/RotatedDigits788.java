package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/9/17
 */
public class RotatedDigits788 {
    Map<Integer, Integer> map = new HashMap<>();

    public int rotatedDigits(int N) {
        map.put(0, 0);
        map.put(1, 1);
        map.put(8, 8);
        map.put(2, 5);
        map.put(5, 2);
        map.put(6, 9);
        map.put(9, 6);

        int res = 0;
        for (int i = 0; i <= N; i++) {
            if (isGood(i)) res++;
        }

        return res;
    }

    private boolean isGood(int n) {
        String s = String.valueOf(n);
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == '3' || c == '4' || c == '7') return false;
            int num = map.get(c - '0');
            sb.append(num);
        }

        return !(new String(arr)).equals(sb.toString());
    }
}
