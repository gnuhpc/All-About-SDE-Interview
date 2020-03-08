package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class NumberToWords273 {
    Map<Integer, String> wordsMap = new HashMap<Integer, String>() {{
        put(0, "Zero");
        put(1, "One");
        put(2, "Two");
        put(3, "Three");
        put(4, "Four");
        put(5, "Five");
        put(6, "Six");
        put(7, "Seven");
        put(8, "Eight");
        put(9, "Nine");
        put(10, "Ten");
        put(11, "Eleven");
        put(12, "Twelve");
        put(13, "Thirteen");
        put(14, "Fourteen");
        put(15, "Fifteen");
        put(16, "Sixteen");
        put(17, "Seventeen");
        put(18, "Eighteen");
        put(19, "Nineteen");
        put(20, "Twenty");
        put(30, "Thirty");
        put(40, "Forty");
        put(50, "Fifty");
        put(60, "Sixty");
        put(70, "Seventy");
        put(80, "Eighty");
        put(90, "Ninety");
    }};

    Map<Integer, String> unitMap = new HashMap<Integer, String>() {{
        put(3, "Hundred");
        put(4, "Thousand");
        put(7, "Million");
        put(10, "Billion");
    }};

    public String numberToWords(int num) {

        if (wordsMap.containsKey(num)) {
            return wordsMap.get(num);
        }

        String n = String.valueOf(num);
        int len = n.length();

        if (len < 3) {
            int key = (n.charAt(0) - '0') * 10;
            return wordsMap.get(key) + " " + numberToWords(num - key);
        }

        if (len == 3 || len == 4 || len == 7 || len == 10) {
            int key = n.charAt(0) - '0';
            String unit = unitMap.get(len);
            int next = num - key * (int) Math.pow(10, len - 1);
            if (next == 0) {
                return wordsMap.get(key) + " " + unit;
            }
            else {
                return wordsMap.get(key) + " " + unit
                       + " " + numberToWords(next);
            }
        }


        if (len >= 5 && len <= 6) {
            return parse(n, 3);
        }

        if (len >= 8 && len <= 9) {
            return parse(n, 6);
        }

        return "";
    }

    private String parse(String n, int base) {
        int key = Integer.parseInt(n.substring(0, n.length() - base));
        String unit = unitMap.get(base + 1);
        int next = Integer.parseInt(n) - key * (int) Math.pow(10, base);
        if (next == 0) {
            return numberToWords(key) + " " + unit;
        }
        else {
            return numberToWords(key) + " " + unit + " " + numberToWords(next);
        }
    }

    @Test
    public void test() {
        System.out.println(numberToWords(0));
        System.out.println(numberToWords(5));
        System.out.println(numberToWords(67));
        System.out.println(numberToWords(100));
        System.out.println(numberToWords(197));
        System.out.println(numberToWords(2197));
        System.out.println(numberToWords(10000));
        System.out.println(numberToWords(16251));
        System.out.println(numberToWords(316251));
        System.out.println(numberToWords(1316251));
        System.out.println(numberToWords(21316251));
        System.out.println(numberToWords(1234567891));
    }

    //add by tina
    //"" is kept for index simplicity. TENS: 1st 2nd never used, keep for index simplicity
    private final String[] LESS_THAN_20 = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
            "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS         =
            {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS    = {"", "Thousand", "Million", "Billion"};

    public String numberToWords2(int num) {
        if (num == 0) return "Zero";
        int i = 0;//ptr to thousands
        String words = "";
        while (num > 0) {
            if (num % 1000 != 0) {
                //avoid special case 1,000,000 else return one million thousand
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            }
            num /= 1000;
            i++;
        }
        return words.trim();//remove extra space in the end.
    }

    private String helper(int num) {
        if (num == 0) {
            return "";
        }
        else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        }
        else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);//avid extra space by recur call
        }
        else {//100-999
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}
