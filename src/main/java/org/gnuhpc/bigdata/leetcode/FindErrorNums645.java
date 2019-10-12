package org.gnuhpc.bigdata.leetcode;

public class FindErrorNums645 {
    public int[] findErrorNums(int[] nums) {
        if(nums== null | nums.length == 0){
            return null;
        }

        int[] res = new int[2];
        int[] arr = new int[nums.length+1];

        for(int n : nums){
            arr[n]++;
        }

        for(int i = 1; i<nums.length+1; i++){
            if(arr[i] == 2) {
                res[0] = i;
                if (res[1] != 0) break;
            } else if (arr[i] == 0){
                res[1] = i;
                if (res[0] != 0) break;
            } else{
                continue;
            }
        }

        return res;

    }
}
