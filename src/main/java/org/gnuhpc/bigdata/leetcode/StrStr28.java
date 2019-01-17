package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class StrStr28 {
    public int strStr(String haystack, String needle) {
        if (needle.length()>haystack.length()) return -1;
        if (needle.length() == 0) return 0;

        int res = -1;

        char[] s1 = haystack.toCharArray();
        char[] s2 = needle.toCharArray();

        int i=0,j=0;
        boolean isMatching = false;
        while (i<haystack.length() && j< needle.length()) {
            if (s1[i] == s2[j]){
                i++;
                j++;
                isMatching = true;
            } else {
                if (!isMatching) i++;
                else{
                    i = i-j+1;
                }
                j = 0;
                isMatching = false;
            }
        }

        if (j==needle.length()) res = i - j;

        return res;
    }

    @Test
    public void test(){
        System.out.println(strStr("mississippi","issip"));
    }
}
