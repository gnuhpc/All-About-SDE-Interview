package org.gnuhpc.bigdata.leetcode;

public class ReverseString344 {
    public static void main(String[] args) {
        String str = ",a";
        System.out.println(reverseString(str));

    }

    public static String reverseString(String s) {
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length && i< charArray.length-1-i; i++) {
            swap(charArray,i,charArray.length-1-i);
        }

        return String.valueOf(charArray);
    }

    private static void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }
}
