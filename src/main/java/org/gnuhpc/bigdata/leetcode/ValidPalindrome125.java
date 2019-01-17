package org.gnuhpc.bigdata.leetcode;

import scala.Char;

public class ValidPalindrome125 {
    public static void main(String[] args) {
        String str1 = "Sample man, a plan, a canal: Panama";
        String str2 = "race a car";
        String str3 = " ";
        String str4 = ",.";
        String str5 = ".";
        System.out.println(isPalindrome(str1));
        System.out.println(isPalindrome(str2));
        System.out.println(isPalindrome(str3));
        System.out.println(isPalindrome(str4));
        System.out.println(isPalindrome(str5));
    }

    public static boolean isPalindrome(String s) {
        if(s==null||s.length()==0) return true;

        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        for(int i = 0; i < s.length() && i!=s.length()-1-i ; i++){
            if(s.charAt(i) != s.charAt(s.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }

    private static boolean isLetterOrDigit(char c){
        return Character.isLetter(c) || Character.isDigit(c);
    }
}
