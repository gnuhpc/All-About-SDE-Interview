package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/5/4
 */
public class FindString1005 {
    public int findString(String[] words, String s) {
        if (words.length < 1 || s == null)
            return -1;

        int l = 0, r = words.length - 1;
        while (l <= r) {
            while (l < r && words[l].equals(""))
                l++;
            while (l < r && words[r].equals(""))
                r--;
            // 此时已经找到了左右边第一个不为空的索引，从而避免了不必要的边缘判断
            int mid = l + (r - l) / 2;
            while (words[mid].equals(""))
                mid++;//--yexing
            if (words[mid].equals(s))
                return mid;
            else if (words[mid].compareTo(s) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }
        return -1;
    }
}
