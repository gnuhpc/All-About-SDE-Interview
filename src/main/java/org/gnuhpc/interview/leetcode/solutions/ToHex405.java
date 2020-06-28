package org.gnuhpc.interview.leetcode.solutions;

public class ToHex405 {
    class Solution {
        public String toHex(int num) {
            char[] hex = "0123456789abcdef".toCharArray();
            StringBuilder sb = new StringBuilder();
            while(num != 0){
                int end = num&15;
                sb.append(hex[end]);
                //无符号右移
                num >>>=4;
            }
            if(sb.length() == 0){
                return "0";
            }

            return sb.reverse().toString();
        }
    }
}
