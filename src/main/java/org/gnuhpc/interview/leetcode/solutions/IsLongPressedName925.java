package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/21
 */
public class IsLongPressedName925 {
    public boolean isLongPressedName(String name, String typed) {
        if (name.equals(typed)) return true;

        char[] nArr = name.toCharArray();
        char[] tArr = typed.toCharArray();
        int i1 = 0, i2 = 0, j1 = 0, j2 = 0;
        for (; i2 < nArr.length && j2 < tArr.length; ) {
            while (i2 < nArr.length && nArr[i1] == nArr[i2]) i2++;
            int len1 = i2 - i1 + 1;

            while (j2 < tArr.length && tArr[j1] == tArr[j2]) j2++;
            int len2 = j2 - j1 + 1;

            if (len1 > len2 || nArr[i1] != tArr[j1]) return false;
            else {
                i1 = i2;
                j1 = j2;
            }

        }

        return i1 == nArr.length && j1 == tArr.length;
    }
}
