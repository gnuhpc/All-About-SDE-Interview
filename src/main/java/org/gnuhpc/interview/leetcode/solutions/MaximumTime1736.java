package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/4/3
 */
public class MaximumTime1736 {
    public String maximumTime(String time) {
        char[] arr = time.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (i == 0 && arr[i] == '?') {
                if (arr[1] < '4' || arr[1] == '?') arr[i] = '2';
                else arr[i] = '1';
            }
            if (i == 1 && arr[i] == '?') {
                if (arr[0] <= '1') arr[i] = '9';
                if (arr[0] == '2') arr[i] = '3';
            }
            if (i == 3 && arr[i] == '?') {
                arr[i] = '5';
            }

            if (i == 4 && arr[i] == '?') {
                arr[i] = '9';
            }
        }

        return new String(arr);

    }
}
