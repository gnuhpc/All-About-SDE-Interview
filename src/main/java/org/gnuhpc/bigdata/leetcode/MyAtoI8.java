package org.gnuhpc.bigdata.leetcode;

public class MyAtoI8 {
    public int myAtoi(String str) {
        int index = 0;
        long out = 0;
        boolean positive = true;
        while (index < str.length() && str.charAt(index) == ' '){
            ++index;
        }
        if (index < str.length() && str.charAt(index) == '-'){
            positive = false;
            ++index;
        }
        else if (index < str.length() && str.charAt(index) == '+'){
            ++index;
        }
        while (index < str.length() && Character.isDigit(str.charAt(index))){
            out = out * 10 + str.charAt(index) - '0';
            if (out > Integer.MAX_VALUE){
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            ++index;
        }
        return positive ? (int) out : (int) -out;
    }
}
