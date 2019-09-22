package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;
import scala.Char;

public class ValidPalindrome125 {
    @Test
    public void test(){
        String str1 = "A man, a plan, a canal: Panama";
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

    public boolean isPalindrome(String s) {
        if(s==null||s.length()==0) return true;

        for (int i = 0, j = s.length()-1; i < j; ) {
            boolean left = isLetterOrDigit(s.charAt(i));
            boolean right = isLetterOrDigit(s.charAt(j));
            if (left && right) {
                if (!isSame(s.charAt(i),s.charAt(j))) return false;
                i++;
                j--;
            }

            else if (left && !right){
                j--;
            }

            else if (!left && right){
                i++;
            }

            else{
                i++;
                j--;
            }


        }


        return true;
    }

    private boolean isLetterOrDigit(char c){
        return Character.isLetter(c) || Character.isDigit(c);
    }

    private boolean isSame(char a, char b){
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }
}
