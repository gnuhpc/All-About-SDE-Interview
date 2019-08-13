package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

//https://longwayjade.wordpress.com/2015/04/26/leetcode-recursion-dp-greedy-wildcard-matching/
public class IsMatch44 {
    /*
    Method 1: recursion ，这种更好理解, 推荐
     */

    public boolean isMatch(String s, String p) {
        return helper(s, p, 0, 0);

    }

    boolean helper(String s, String p, int sCursor, int pCursor) {
        // 递归返回条件
        // If pCursor reaches the end of p,
        // we need to check if sCursor also reaches the end of s. If so, match.
        if (pCursor == p.length()) return sCursor == s.length();

        if (p.charAt(pCursor) == '*') {
            while (pCursor < p.length() && p.charAt(pCursor) == '*')
                pCursor++;   // Move the index at p to a non-star char.
            while (sCursor < s.length()) {
                if (helper(s, p, sCursor, pCursor)) return true; // Find one match, return true.
                sCursor++; // Try the next one.
            }
            return helper(s, p, sCursor, pCursor);
        } else if (sCursor < s.length() && (p.charAt(pCursor) == '?' || s.charAt(sCursor) == p.charAt(pCursor))) {
            return helper(s, p, sCursor + 1, pCursor + 1);
        } else {
            return false;
        }
    }

    /*
    Method 2: iteration
     */

    /**
     * two pointers
     * remember the index of * and matched sequence
     * advance only pattern pointer when * is found
     * match the sequence after * in pattern with the rest of the string
     */
    public boolean isMatch2(String str, String pattern) {
        if (str == null && pattern == null) return true;
        if (str == null || pattern == null) return false;

        int s = 0, p = 0, match = 0, astroIdx = -1; // must be -1
        while (s < str.length()) {
            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) { // found ? or same chars
                s++; // move both pointers
                p++;
            } else if (p < pattern.length() && pattern.charAt(p) == '*') { // found *
                astroIdx = p; // save astroid index in pattern
                match = s; // save current index of string
                p++; // only move pattern pointer forward
            } else if (astroIdx != -1) { // 不匹配且上一个是*那么从str往下找
                p = astroIdx + 1; // move to * one char behind astroid
                match++; // move current index of string
                s = match;
            } else return false; // not ?, not same char, not *, don't match
        }
        // check remaining characters in pattern, can only be astroid
        while (p < pattern.length() && pattern.charAt(p) == '*') p++;
        return p == pattern.length(); // no remaining
    }


    @Test
    public void test() {
//        System.out.println(isMatch("aa","a"));
//        System.out.println(isMatch("aa","*"));
//        System.out.println(isMatch("cb","?a"));
//        System.out.println(isMatch("adceb","*a*b"));
//        System.out.println(isMatch("acdcb","a*c?b"));
//        System.out.println(isMatch("abefcdgiescdfimde","ab*cd?i*de"));
//        System.out.println(isMatch("aaaab","**b"));
//        System.out.println(isMatch("adceb","*a*b"));
//        System.out.println(isMatch("c","*?*"));
//        System.out.println(isMatch("b","*?*?"));
//        System.out.println(isMatch("b","*?*?*"));
//        System.out.println(isMatch("hi","*?"));
        System.out.println(isMatch2("aab", "*a"));
    }


}
