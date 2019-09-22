package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class StrStr28 {
    public int strStr(String haystack, String needle) {
        if (needle.length()>haystack.length()) return -1;
        if (needle.length() == 0) return 0;

        char[] s1 = haystack.toCharArray();
        char[] s2 = needle.toCharArray();

        //i的终止位置是一个优化的点
        for(int i = 0,j=0; i<=s1.length-s2.length;i++){
            if(s1[i] != s2[j]){
                continue;
            } else {
                int m = i;
                int n = 0;
                while(n<s2.length && m < s1.length && s1[m] == s2[n]){
                    m++;
                    n++;
                }

                if(n == s2.length){
                    return i;
                }
            }
        }

        return -1;
    }

    @Test
    public void test(){
        System.out.println(strStr("mississippi","issip"));
    }
}
