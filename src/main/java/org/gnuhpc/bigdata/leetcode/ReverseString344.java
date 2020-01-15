package org.gnuhpc.bigdata.leetcode;

public class ReverseString344 {

    public void reverseString(char[] s) {
        int left = 0, right = s.length-1;

        while (left<right){
            swap(s,left++,right--);
        }
    }

    private void swap(char[] arr, int i, int j) {
        if(i==j) return;
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
