package org.gnuhpc.interview.leetcode.solutions;

public class SortString1370 {
    public String sortString(String s) {
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuffer ret = new StringBuffer();
        while (ret.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (num[i] > 0) {
                    ret.append((char) (i + 'a'));
                    num[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (num[i] > 0) {
                    ret.append((char) (i + 'a'));
                    num[i]--;
                }
            }
        }
        return ret.toString();
    }
}
