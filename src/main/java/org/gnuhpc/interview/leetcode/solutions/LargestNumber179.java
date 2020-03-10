package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;
//https://www.programcreek.com/2014/02/leetcode-largest-number-java/

public class LargestNumber179 {
    public String largestNumber(int[] nums) {
        String[] h = new String[nums.length];
        for (int i = 0; i < nums.length; i++) h[i] = String.valueOf(nums[i]);
        Arrays.sort(h, (a, b) -> {
            if (a.charAt(0) != b.charAt(0)) {
                return b.charAt(0) - a.charAt(0);
            } //这一段略显多余
            String l1 = a + b;
            String l2 = b + a;
            return l2.compareTo(l1);
        });
        if (h[0].charAt(0) == '0') return "0";
        StringBuilder sb = new StringBuilder();
        for (String ky : h) sb.append(ky);
        return sb.toString();
    }


    @Test
    public void test() {
        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println(largestNumber(new int[]{1, 1, 1}));
        System.out.println(largestNumber(new int[]{8247, 824}));
        System.out.println(largestNumber(new int[]{128, 12}));
        System.out.println(largestNumber(new int[]{3, 30}));
    }
}
