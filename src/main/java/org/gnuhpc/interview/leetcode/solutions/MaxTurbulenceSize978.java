package org.gnuhpc.interview.leetcode.solutions;

public class MaxTurbulenceSize978 {
    public int maxTurbulenceSize(int[] arr){
        int ans = 1;
        int left = 0;
        for (int right = 1; right < arr.length; right++) {
            int c = Integer.compare(arr[right-1], arr[right]);
            if(right == arr.length - 1 || c*Integer.compare(arr[right], arr[right+1])!=-1){
                if(c!=0){
                    ans = Math.max(ans, right-left+1);
                }
                left = right;
            }
        }
        return ans;
    }
}
