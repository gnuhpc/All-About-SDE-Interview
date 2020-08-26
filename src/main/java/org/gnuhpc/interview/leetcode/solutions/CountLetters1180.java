package org.gnuhpc.interview.leetcode.solutions;

public class CountLetters1180 {
    public int countLetters(String S) {
        if(S.length() == 1) return 1;
        char[] arr = S.toCharArray();

        int left = 0, right = 0, res = 0;

        while(right < arr.length){
            if(arr[left] == arr[right]) right++;
            else {
                res+=(right - left)*(right - left+1)/2;
                left = right;
            }
        }
        if(right != left)
            res+=(right - left)*(right - left+1)/2;

        return res;
    }
}
