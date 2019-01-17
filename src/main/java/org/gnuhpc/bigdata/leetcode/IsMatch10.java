package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class IsMatch10 {

    //https://longwayjade.wordpress.com/2015/04/24/leetcode-recursiondp-regular-expression-matching/
    /**
     * 递归方法:
     *  recursive method. Check if the first part is match or not, if yes, check the rest of the regular expression.
     *
     * Reduction:
     * If next char is not “*”, we just compare this current char, and reduce both the length of s and p by 1.
     * If next char is “*”:
     * If the current char is the same, reduce s by 1 and check again. Until the current char is different.  — Not accurate! Consider: “aaa” and “a*a”. So every time before we reduce p by 1, we have to check if they match. This check must to be placed before we reduce p by 1, since we could have the case: s = abc   p = .*abc
     * If the current char is different, reduce p by 2.
     * Base case: As reduction reduces length by 1 or 2 every time, we need to consider finally when length == 0 or length == 1.
     * If p.length() == 0, check if s.length() is also 0.
     * If p.length() == 1, check if s.length() is also 1 and the char match or not.
     * A detail: s.subString(l), could return null if l is the length of s.
     * A detail: always check s.length

     */
    public boolean isMatch(String s, String p) {
        if (p.length() == 0){
            return s.length() == 0;
        }
        if (p.length() == 1){
            return s.length() == 1 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        }

        // Now, we make sure that p has at least 2 chars.
        if (p.charAt(1) != '*'){
            /* 例如
            a|ab  ->  ab
            a|b       b
             */
            if (s.length() == 0) return false;
            else return  (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')&& isMatch(s.substring(1), p.substring(1));

        }else{ // p.charAt(1) == '*''
            while (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')){
                if (isMatch(s,p.substring(2))) return true;  // this has to be before s= s.substring(1), to deal with the case: s=abc  p = .*abc

                s = s.substring(1);


            }
            return isMatch(s, p.substring(2));
        }

    }

    /*
    双指针递归方法
     */

    public static boolean isMatch2(String s, String p) {
        if (".*".equals(p)) {
            return true;
        }
        return process(0, 0, s.toCharArray(), p.toCharArray());
    }

    public static boolean process(int i, int j, char[] s, char[] p) {
        if (j == p.length) {
            return i == s.length;
        }
        if (j == p.length - 1|| p[j + 1] != '*') {
            return i != s.length && (s[i] == p[j] || p[j] == '.') && process(i + 1, j + 1, s, p);
        }
        while (i != s.length && (s[i] == p[j] || p[j] == '.')) {
            if (process(i, j + 2, s, p)) {
                return true;
            }
            i++;
        }
        return process(i, j + 2, s, p);
    }


    @Test
    public void test(){
//        System.out.println(isMatch("aaab","a*b"));
//        System.out.println(isMatch("aab","a*b"));
        System.out.println(isMatch("aab","a*aab"));
    }
}
