package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/24
 */
public class ModifyString1576 {
    public String modifyString(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '?') {
                arr[i] = 'a';
                while ((i < arr.length - 1 && arr[i] == arr[i + 1]) || (i > 0 && arr[i] == arr[i - 1]))
                    arr[i]++;
            }
        }

        return new String(arr);
    }
}
