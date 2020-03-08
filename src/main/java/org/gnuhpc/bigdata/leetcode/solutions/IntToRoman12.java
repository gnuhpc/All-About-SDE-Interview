package org.gnuhpc.bigdata.leetcode.solutions;

public class IntToRoman12 {
    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < nums.length; ++index) {
            while (num >= nums[index]) {
                result.append(romans[index]);
                num -= nums[index];
            }
        }
        return result.toString();
    }
}
