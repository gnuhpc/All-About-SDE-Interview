package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.io.PrintStream;

public class Multiply43 {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return null;
        String big = num1.length() > num2.length() ? num1 : num1.compareTo(num2) > 0 ? num1 : num2;
        String small = num1.equals(big) ? num2 : num1;

        String[] strs = decompose(small);
        String res = "0";

        for (String str : strs) {
            res = add(res, doMultiply(str, big));
        }

        return res;
    }

    private String add(String num1, String num2) {
        String big = num1.length() > num2.length() ? num1 : num1.compareTo(num2) > 0 ? num1 : num2;
        String small = num1.equals(big) ? num2 : num1;

        int left = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = big.length() - 1, j = small.length() - 1; i >= 0; i--, j--) {
            int num = (big.charAt(i) - '0') + (j >= 0 ? (small.charAt(j) - '0') : 0) + left;
            left = num / 10;
            sb.append(num % 10);
        }

        if (left != 0) sb.append(left);

        return sb.reverse().toString();
    }

    //Num1 is 100 ,20 ,3
    private String doMultiply(String num1, String num2) {
        if (num1.length() == 1) {
            int multi = num1.charAt(0) - '0';

            String res = "0";
            for (int i = 0; i < multi; i++) {
                res = add(res, num2);
            }

            return res;
        }

        int zeroCount = num1.length() - 1;
        String base = num1.substring(0, 1);

        StringBuilder res = new StringBuilder(doMultiply(base, num2));
        for (int i = 0; i < zeroCount; i++) {
            res.append("0");
        }

        return res.toString();
    }

    private String[] decompose(String num) {
        int len = num.length();
        String[] res = new String[num.length()];


        for (int i = len - 1, j = 0; i >= 0; i--, j++) {
            res[i] = (num.charAt(i) - '0') + fillZero(j);
        }

        return res;
    }

    public String fillZero(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("0");
        }

        return sb.toString();
    }

    /*


     */

    public String multiply2(String num1, String num2) {
        String front = "";
        int len = num2.length() - 1;
        for (int i = num2.length() - 1; i >= 0; i--)
            front = addition(front, multiplication(num1, num2.charAt(i)), len - i);
        return front.charAt(0) == '0' ? "0" : front;
    }

    // 字符串乘法
    public static String multiplication(String front, char after) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = front.length() - 1; i >= 0; i--) {
            int temp = (front.charAt(i) - '0') * (after - '0') + carry;
            carry = temp / 10;
            sb.append(temp % 10);
        }
        return sb.append(carry > 0 ? carry : "").reverse().toString();
    }

    // 字符串加法
    public static String addition(String front, String after, int index) {
        int len = front.length() - 1, i = len - index, j = after.length() - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int k = len; k > len - index; k--)
            sb.append(k < 0 ? "0" : front.charAt(k));
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? front.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? after.charAt(j) - '0' : 0;
            int temp = n1 + n2 + carry;
            carry = temp / 10;
            sb.append(temp % 10);
            i--;
            j--;
        }
        return sb.append(carry > 0 ? 1 : "").reverse().toString();
    }

    @Test
    public void test() {
//        System.out.println(multiply("123","456"));
        System.out.println(multiply("896686", "8261335908"));
    }
}
