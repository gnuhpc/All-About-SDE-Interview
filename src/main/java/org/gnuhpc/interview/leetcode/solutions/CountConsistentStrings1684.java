package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/3/24
 */
public class CountConsistentStrings1684 {
    public int countConsistentStrings(String allowed, String[] words) {
        int ans = 0;
        //总共26个小写字母
        int[] attr = new int[26];
        for (int i = 0, len = allowed.length(); i < len; i++) {
            //1代表有这个字母
            attr[allowed.charAt(i) - 'a'] = 1;
        }
        for (String word : words) {
            boolean find = true;
            for (int i = 0, len = word.length(); i < len; i++) {
                if (attr[word.charAt(i) - 'a'] == 0) {
                    //无此字母
                    find = false;
                    break;
                }
            }
            if (find) {
                ans++;
            }
        }
        return ans;
    }
}
