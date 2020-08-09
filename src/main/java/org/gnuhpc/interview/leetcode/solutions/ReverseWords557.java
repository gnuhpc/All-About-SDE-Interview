package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/5
 */
public class ReverseWords557 {
    char[] arr;

    public String reverseWords(String s) {
        arr = s.toCharArray();

        for (int i = 0, j = 0; j < arr.length; ) {
            if (arr[j] == ' ') {
                reverse(i, j - 1);
                i = j + 1;
                j = i;
            } else {
                j++;
            }

            if (j == arr.length) {
                reverse(i, j - 1);
            }
        }

        return new String(arr);
    }

    private void reverse(int i, int j) {
        char tmp = ' ';
        while (i < j) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
}
