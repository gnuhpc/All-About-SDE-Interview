package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidPalindrome680 {
    //Method1: 递归不断缩小
    public boolean validPalindrome(String s) {
        return valid(s, 0, s.length()-1, 0);
    }

    boolean valid(String str, int i, int j, int flag){ //flag代表是否跳过
        char[] s = str.toCharArray();
        while(i < j){
            if(s[i] == s[j]){
                i++;
                j--;
            } else{
                if(flag == 1) return false; //如果已经跳过， 直接返回否
                else
                    return valid(str, i+1, j, 1) || valid(str, i, j-1, 1);
            }
        }
        return true;
    }

    //Method 2: Non-recursive method
    public boolean validPalindrome2(String s) {
        int l = -1, r = s.length();
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r))
                return isPalindromic(s, l, r+1) || isPalindromic(s, l-1, r);
        return true;
    }
    public boolean isPalindromic(String s, int l, int r) {
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return false;
        return true;
    }

    @Test
    public void test(){
        assertFalse(validPalindrome("abc"));
        assertTrue(validPalindrome("aba"));
        assertTrue(validPalindrome("abca"));
        assertTrue(validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }
}
