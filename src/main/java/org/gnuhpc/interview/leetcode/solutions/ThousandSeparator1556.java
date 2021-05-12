package org.gnuhpc.interview.leetcode.solutions;

public class ThousandSeparator1556 {
    public String thousandSeparator(int n) {
        char[] arr = String.valueOf(n).toCharArray();

        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = arr.length-1; i >= 0; i--,j++) {
            if(j!=0 && j%3==0) {
                sb.append(".");
            }

            sb.append(arr[i]);
        }

        return sb.reverse().toString();
    }
}
